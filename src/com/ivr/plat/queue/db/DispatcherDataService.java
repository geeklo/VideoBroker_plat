package com.ivr.plat.queue.db;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivr.plat.queue.ServiceIF;
import com.ivr.plat.service.IDispatcherService;
import com.ivr.plat.vo.DispatcherResultVo;

@Service("dispatcherDataService")
public class DispatcherDataService implements ServiceIF<DispatcherResultVo> {
	private static Logger logger = Logger.getLogger(DispatcherDataService.class);
	
	@Autowired
	private IDispatcherService dispatcherService;
	
	@Override
	public boolean doProcess(DispatcherResultVo dispatcherResultVo) {
		logger.debug(String.format("[%s]转发入库操作开始!", dispatcherResultVo.getMobile()));
		dispatcherService.insertDispatcherResult(dispatcherResultVo);
		logger.debug(String.format("[%s]转发入库操作结束!", dispatcherResultVo.getMobile()));
		return true;
	}

}
