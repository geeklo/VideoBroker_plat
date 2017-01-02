package com.ivr.plat.cache.impl;

import java.util.List;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ivr.plat.cache.Cacheable;
import com.ivr.plat.dao.IDispatcherDao;
import com.ivr.plat.msg.HttpMsg;
import com.ivr.plat.util.SpringServiceUtil;
import com.ivr.plat.vo.DispatcherReadyVo;
import com.ivr.plat.vo.ReceiveVo;

@Service("dispatcherTaskCache")
public class DispatcherTaskCache implements Cacheable {
	private static Logger logger = Logger.getLogger(DispatcherTaskCache.class);
	
	@Autowired
	private IDispatcherDao dispatcherDao;
	@Autowired
	@Qualifier("serviceLocator")
	private SpringServiceUtil springServiceUtil;
	
	@Override
	public void run() {
		/**定时任务从预备转发表去所有可以转发的数据*/
		List<DispatcherReadyVo> dispatcherReadyVoList = dispatcherDao.getDispatcherReadyList();
		if(dispatcherReadyVoList != null && dispatcherReadyVoList.size() > 0){
			for(DispatcherReadyVo dispatcherReadyVo : dispatcherReadyVoList){
				logger.info(String.format("获得预转发记录数[%d]", dispatcherReadyVoList.size()));
				ReceiveVo receiveVo = new ReceiveVo();
				receiveVo.setMobile(dispatcherReadyVo.getMobile());
				receiveVo.setSp_number(dispatcherReadyVo.getSp_number());
				receiveVo.setStart_time(dispatcherReadyVo.getStart_time());
				receiveVo.setEnd_time(dispatcherReadyVo.getEnd_time());
				receiveVo.setSecond(String.valueOf(dispatcherReadyVo.getSecond()));
				receiveVo.setMinute(String.valueOf(dispatcherReadyVo.getMinute()));
				receiveVo.setLinkid(dispatcherReadyVo.getLinkid());
				receiveVo.setLower_id(dispatcherReadyVo.getLower_id());
				receiveVo.setRouter_id(dispatcherReadyVo.getRouter_id());
				
				Queue<HttpMsg> dispatchQueue = (Queue<HttpMsg>) springServiceUtil.getService("dispatchQueue");
				dispatchQueue.add(receiveVo);
			}
		}else{
			logger.info("预转发记录数为0!");
		}
		
	}

	@Override
	public Object getCache() {
		return null;
	}

}
