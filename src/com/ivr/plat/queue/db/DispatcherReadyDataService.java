package com.ivr.plat.queue.db;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivr.plat.queue.ServiceIF;
import com.ivr.plat.service.IDispatcherService;
import com.ivr.plat.vo.DispatcherReadyVo;

@Service("dispatcherReadyDataService")
public class DispatcherReadyDataService implements ServiceIF<DispatcherReadyVo> {
	private static Logger logger = Logger.getLogger(DispatcherReadyDataService.class);
	@Autowired
	private IDispatcherService dispatcherService;
	
	@Override
	public boolean doProcess(DispatcherReadyVo dispatcherReadyVo) {
		logger.debug(String.format("[%s][%s]预转发入库操作开始!", dispatcherReadyVo.getMobile(), dispatcherReadyVo.getSp_number()));
		dispatcherService.insertDispatcherReady(dispatcherReadyVo);
		logger.debug(String.format("[%s][%s]预转发入库操作结束!", dispatcherReadyVo.getMobile(), dispatcherReadyVo.getSp_number()));
		return true;
	}

}
