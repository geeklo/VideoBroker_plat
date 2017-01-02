package com.ivr.plat.chain.receive;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ivr.plat.cache.Cacheable;
import com.ivr.plat.chain.ReceiveServiceIF;
import com.ivr.plat.vo.ReceiveVo;

@Service("addAreaService")
public class AddAreaService implements ReceiveServiceIF {
	private static Logger logger = Logger.getLogger(AddAreaService.class);
	
	@Autowired
	@Qualifier("mobileAreaCache")
	private Cacheable mobileAreaCache;

	@SuppressWarnings("unchecked")
	@Override
	public int doProcess(ReceiveVo receiveVo) {
		ConcurrentHashMap<Integer, String[][]> mobileAreaMap = (ConcurrentHashMap<Integer, String[][]>) mobileAreaCache.getCache();
		/**获得主叫号码*/
		String mobile = receiveVo.getMobile();
		/**获得被叫号码*/
		String sp_number = receiveVo.getSp_number();
		
		String province = "未知";
		String city = "未知";
		
		/**对主叫号码进行号段处理  截取后11位  防止手机号码前面带+86之类*/
		if(mobile.length() > 11){
			mobile = mobile.substring(mobile.length() - 11, mobile.length());
		}
		
		/**根据主叫号码前7位获得主叫号码的省份  未获得的为未知省份*/
		if(mobile.length() > 7){
			String start = mobile.substring(0,7);
			Integer startP3 = 0;
			Integer startL4 = 0;
			try{
				startP3 = Integer.parseInt(start.substring(0,3));
				startL4 = Integer.parseInt(start.substring(3,7));
			}catch (NumberFormatException e) {
				logger.error(String.format("添加省市处理链[%s][%s][加入省市]数据转化异常:%s", mobile,sp_number,ExceptionUtils.getFullStackTrace(e)));
				receiveVo.setProvince(province); 
				receiveVo.setCity(city);
				return 1;
			}
			
			/**从号段缓存中根据手机号前三位获得后续数组*/
			String temp[][] = mobileAreaMap.get(startP3);
			if(temp == null){
				receiveVo.setProvince(province); 
				receiveVo.setCity(city);
				logger.info(String.format("添加省市处理链[%s][%s][加入省市]为空,[%s,%s]", mobile,sp_number,province,city));
				return 1;
			}
			
			/**根据手机号码后四位获得区域信息  0 省份  1 市*/
			String area[] = temp[startL4];
			province = area[0];
			city = area[1];
		}
		
		/**拼装用户的区域信息到基本数据VO*/
		receiveVo.setProvince(province);
		receiveVo.setCity(city);
		
		logger.info(String.format("添加省市处理链[%s][%s][加入省市][%s,%s]", mobile,sp_number,province,city));
		/**返回1代表正常状态*/
		return 1;
	}
	
}
