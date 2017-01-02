package com.ivr.plat.queue.dispatcher;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ivr.plat.cache.Cacheable;
import com.ivr.plat.dao.IDispatcherDao;
import com.ivr.plat.msg.HttpMsg;
import com.ivr.plat.queue.ServiceIF;
import com.ivr.plat.send.Sender;
import com.ivr.plat.util.SpringServiceUtil;
import com.ivr.plat.vo.DispatcherReadyVo;
import com.ivr.plat.vo.DispatcherResultVo;
import com.ivr.plat.vo.ReceiveVo;
import com.ivr.plat.vo.RouterVo;

@Service("dispatchService")
public class DispatchService implements ServiceIF<ReceiveVo> {
	private static Logger logger = Logger.getLogger(DispatchService.class);
	
	@Autowired
	@Qualifier("httpStatusSender")
	private Sender httpStatusSender;
	@Autowired
	@Qualifier("routerCache")
	private Cacheable routerCache;
	@Autowired
	@Qualifier("serviceLocator")
	private SpringServiceUtil springServiceUtil;
	@Autowired
	private IDispatcherDao dispatcherDao;
	
	@Override
	public boolean doProcess(ReceiveVo receiveVo) {
		logger.debug(String.format("[%s][%s]开始转发处理", receiveVo.getMobile(), receiveVo.getSp_number()));
		ConcurrentHashMap<String, RouterVo> routerMap = (ConcurrentHashMap<String, RouterVo>) routerCache.getCache();
		RouterVo routerVo = routerMap.get(receiveVo.getSp_number());
		if(routerVo != null){
			/**根据长号码获得URL地址*/
			String sync_url = routerVo.getSync_url();
			/**获得参数*/
			String method = getMethod(receiveVo);
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("url", sync_url+"?"+method);
			hm.put("method", "get");
			logger.info(String.format("[%s][%s][HTTP转发]URL[%s]", receiveVo.getMobile(),receiveVo.getSp_number(),sync_url+"?"+method));
			
			long start_time = System.currentTimeMillis();
			try {
				Integer result = (Integer) httpStatusSender.sendObject(hm);
				logger.info(String.format("[%s][%s][HTTP转发]HTTP发送完成,返回["+result+"],耗时["+(System.currentTimeMillis() - start_time)+"]ms", receiveVo.getMobile(),receiveVo.getSp_number()));
				
				/**同步成功*/
				if(result == HttpStatus.SC_OK){
					/**入成功库*/
					DispatcherResultVo dispatcherResultVo = new DispatcherResultVo();
					dispatcherResultVo.setMobile(receiveVo.getMobile());
					dispatcherResultVo.setLinkid(receiveVo.getLinkid());
					dispatcherResultVo.setSync_url(sync_url);
					dispatcherResultVo.setParameter(method);
					dispatcherResultVo.setType("get");
					dispatcherResultVo.setStatus_code(HttpStatus.SC_OK);
					dispatcherResultVo.setLower_id(receiveVo.getLower_id());
					dispatcherResultVo.setRouter_id(receiveVo.getRouter_id());
					dispatcherResultVo.setDis_count(1);
					
					/**放入入库队列和转发队列*/
					Queue<HttpMsg> dbQueue = (Queue<HttpMsg>) springServiceUtil.getService("dbQueue");
					dbQueue.add(dispatcherResultVo);
					logger.debug(String.format("[%s][%s]放入转发入库队列完成", receiveVo.getMobile(), receiveVo.getSp_number()));
					
					dispatcherDao.deleteDispatcherReadyByLinkId(receiveVo.getLinkid());
					logger.info(String.format("[%s][%s][HTTP转发]保存转发数据成功!", receiveVo.getMobile(),receiveVo.getSp_number()));
				}else{
					updateDispatcherReady(receiveVo,result);
					logger.info(String.format("[%s][%s][HTTP转发]保存预转发数据成功!", receiveVo.getMobile(),receiveVo.getSp_number()));
				}
			} catch (Throwable e) {
				logger.error(String.format("[%s][%s][HTTP转发]HTTP发送失败!%s", receiveVo.getMobile(),receiveVo.getSp_number(),e.getMessage()));
				updateDispatcherReady(receiveVo, 504);
				logger.info(String.format("[%s][%s][HTTP转发]保存预转发数据成功!", receiveVo.getMobile(),receiveVo.getSp_number()));
			}
			
		}else{
			logger.info(String.format("[%s][%s][HTTP转发]长号码未匹配到路由信息!", receiveVo.getMobile(),receiveVo.getSp_number()));
			updateDispatcherReady(receiveVo,419);
			logger.info(String.format("[%s][%s][HTTP转发]保存预转发数据成功!", receiveVo.getMobile(),receiveVo.getSp_number()));
		}
		return false;
	}
	
	private void updateDispatcherReady(ReceiveVo receiveVo, int status_code){
		/**根据linkId查询表是否存在  存在+1 不存在 新增*/
		int dis_count = dispatcherDao.getDispatcherReadyByLinkId(receiveVo.getLinkid());
		if(dis_count == 0){
			/**入预备库*/
			DispatcherReadyVo dispatcherReadyVo = new DispatcherReadyVo();
			dispatcherReadyVo.setMobile(receiveVo.getMobile());
			dispatcherReadyVo.setSp_number(receiveVo.getSp_number());
			dispatcherReadyVo.setStart_time(receiveVo.getStart_time());
			dispatcherReadyVo.setEnd_time(receiveVo.getEnd_time());
			dispatcherReadyVo.setSecond(Integer.parseInt(receiveVo.getSecond()));
			dispatcherReadyVo.setMinute(Integer.parseInt(receiveVo.getMinute()));
			dispatcherReadyVo.setLinkid(receiveVo.getLinkid());
			dispatcherReadyVo.setLower_id(receiveVo.getLower_id());
			dispatcherReadyVo.setRouter_id(receiveVo.getRouter_id());
			dispatcherReadyVo.setStatus_code(status_code);
			dispatcherReadyVo.setDis_count(1);
			
			/**放入入库队列和转发队列*/
			Queue<HttpMsg> dbQueue = (Queue<HttpMsg>) springServiceUtil.getService("dbQueue");
			dbQueue.add(dispatcherReadyVo);
			logger.debug(String.format("[%s][%s]放入预转发入库队列完成", receiveVo.getMobile(), receiveVo.getSp_number()));
			
		}else{
			/**根据linkId更新转发数量*/
			dispatcherDao.updateDispatcherReadyDisCountByLinkId(receiveVo.getLinkid(), (dis_count + 1), status_code);
		}
	}
	
	private String getMethod(ReceiveVo receiveVo){
		StringBuffer method = new StringBuffer();
		try {
			method.append("mobile=" + receiveVo.getMobile());
			method.append("&longnumber=" + receiveVo.getSp_number());
			method.append("&linkid=" + receiveVo.getLinkid());
			method.append("&minute=" + receiveVo.getMinute());
			method.append("&stime=" + URLEncoder.encode(receiveVo.getStart_time(), "GBK"));
			method.append("&etime=" + URLEncoder.encode(receiveVo.getEnd_time(), "GBK"));
			
			return method.toString();
		} catch (Exception e) {
			logger.info(String.format("[%s][%s][HTTP转发]参数处理异常%s", receiveVo.getMobile(),receiveVo.getSp_number(),e.getMessage()));
		}
		return "";
	}

}
