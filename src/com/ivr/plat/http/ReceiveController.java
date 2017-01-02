package com.ivr.plat.http;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ivr.plat.cache.Cacheable;
import com.ivr.plat.msg.HttpMsg;
import com.ivr.plat.util.SpringServiceUtil;
import com.ivr.plat.vo.GatewayVo;
import com.ivr.plat.vo.ReceiveVo;
/**
 * 接收SP数据入口
 * @author liugeng
 *
 */
@Component("receiveController")
public class ReceiveController implements Controller {
	private static Logger logger = Logger.getLogger(ReceiveController.class);
	
	@Autowired
	@Qualifier("serviceLocator")
	private SpringServiceUtil springServiceUtil;
	@Autowired
	@Qualifier("gatewayCache")
	private Cacheable gatewayCache;
	
	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		/**获得请求路径*/
		String servletPath = request.getServletPath();
		/**获得请求IP*/
		String ip = request.getRemoteAddr();
		logger.debug(String.format("获得请求路径和请求IP[controller=%s, IP=%s]", servletPath, ip));
		/**定义正则表达式*/
		Pattern pattern = Pattern.compile("^/receive[1-9][0-9]{3}\\.do$");
		Matcher matcher = pattern.matcher(servletPath);
		if(!matcher.matches()){
			writeResponse(out, "Request controller pattern error!");
			return null;
		}
		/**根据路径获得网关信息ID*/
		int index = servletPath.indexOf('.');
		int gatewayId = Integer.parseInt(servletPath.substring(8, index));
		
		/**根据网关信息ID获得网关信息*/
		ConcurrentHashMap<Integer,GatewayVo> gatewayMap = (ConcurrentHashMap<Integer, GatewayVo>) gatewayCache.getCache();
		GatewayVo gatewayVo = gatewayMap.get(gatewayId);
		if(gatewayVo != null){
			String bindip = gatewayVo.getBindip();
			if(!"*".equals(bindip) && !"127.0.0.1".equals(ip)){
				if(!StringUtils.contains(bindip, ip)){
					writeResponse(out, String.format("IP[%s] not bound", ip));
					return null;
				}
			}
			String mobile = request.getParameter(gatewayVo.getMobile());
			String sp_number = request.getParameter(gatewayVo.getLong_number());
			String start_time = request.getParameter(gatewayVo.getStime());
			String end_time = request.getParameter(gatewayVo.getEtime());
			
			Pattern date_pattern = Pattern.compile("[0-9]*");
			SimpleDateFormat s_format = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat d_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			logger.info(String.format("start_time:[%s], end_time:[%s]", start_time, end_time));
			if(date_pattern.matcher(start_time).matches() && date_pattern.matcher(end_time).matches()) {
				try {
					date = s_format.parse(start_time);
					start_time = d_format.format(date);
					
					date = s_format.parse(end_time);
					end_time = d_format.format(date);
					logger.info(String.format("修改时间格式：start_time:[%s], end_time:[%s]", start_time, end_time));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info(String.format("转化时间 错误"));
				}
			}
			
			String linkid = request.getParameter(gatewayVo.getLinkid());
			if(linkid == null || "".equals(linkid)){
				/**合作方不给同步唯一标识  系统自动生成*/
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				String nowDateStr = format.format(new Date());
				String randomValue = getRandom(6);
				linkid = nowDateStr + randomValue;
			}
			String minute = request.getParameter(gatewayVo.getMinute());
			if(minute == null || "".equals(minute)){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long diff_phase = format.parse(end_time).getTime() - format.parse(start_time).getTime();
				long diff_minute = diff_phase / (1000 * 60);
				long remainder = diff_phase % (1000 * 60);
				if(remainder > 0){
					diff_minute += 1;
				}
				minute = String.valueOf(diff_minute);
			}
			String second = request.getParameter(gatewayVo.getSecond());
			if(second == null || "".equals(second)){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long diff_phase = format.parse(end_time).getTime() - format.parse(start_time).getTime();
				second = String.valueOf(diff_phase / 1000);
			}
			
			if(gatewayVo.getSp_number().equals(sp_number)){
				ReceiveVo receiveVo = new ReceiveVo();
				receiveVo.setMobile(mobile);
				receiveVo.setSp_number(sp_number);
				receiveVo.setLinkid(linkid);
				receiveVo.setSecond(second);
				receiveVo.setMinute(minute);
				receiveVo.setStart_time(start_time);
				receiveVo.setEnd_time(end_time);
				receiveVo.setUpper_id(gatewayVo.getUpper_id());
				receiveVo.setGateway_id(gatewayVo.getId());
				if(gatewayVo.getFee_type() == 1){
					receiveVo.setFee_number(Integer.parseInt(minute) * gatewayVo.getFee_code());
				}else if(gatewayVo.getFee_type() == 2){
					receiveVo.setFee_number(gatewayVo.getFee_code());
				}else{
					writeResponse(out, "Fee_type not exist!");
					return null;
				}
				
				Queue<HttpMsg> receiveQueue = (Queue<HttpMsg>) springServiceUtil.getService("receiveQueue");
				receiveQueue.add(receiveVo);
				logger.debug(String.format("[%s][%s]放入处理队列成功!", mobile,sp_number));
				writeResponse(out, gatewayVo.getResponse());
			}else{
				writeResponse(out, "Sp_number does not match!");
			}
		}else{
			writeResponse(out, "Gateway not exist or Partner Upper not exist!");
		}
		return null;
	}
	
	private void writeResponse(PrintWriter out, String content){
		try{
			out.write(content);
		}catch(Exception e){
			if(out != null){
				out.write(ExceptionUtils.getFullStackTrace(e));
			}
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	
	private String getRandom(int length){
		String result = "";
		Random random = new Random();
		for(int i = 0; i < length; i++){
			/**生成 0 - 9 的随机数字*/
			int randomValue = random.nextInt(10);
			result += randomValue;
		}
		
		return result;
	}
}
