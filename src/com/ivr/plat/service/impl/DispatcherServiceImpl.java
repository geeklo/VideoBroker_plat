package com.ivr.plat.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivr.plat.dao.IDispatcherDao;
import com.ivr.plat.service.IDispatcherService;
import com.ivr.plat.vo.DispatcherReadyVo;
import com.ivr.plat.vo.DispatcherResultVo;
/**
 * 转发信息服务实现层
 * @author liugeng
 *
 */
@Service("dispatcherService")
public class DispatcherServiceImpl implements IDispatcherService {
	private static Logger logger = Logger.getLogger(DispatcherServiceImpl.class);
	
	@Autowired
	private IDispatcherDao dispatcherDao;
	
	/**保存账单转发数据*/
	@Override
	public int insertDispatcherResult(DispatcherResultVo dispatcherResultVo) {
		return dispatcherDao.insertDispatcherResult(dispatcherResultVo);
	}
	
	/**保存账单预转发数据*/
	@Override
	public int insertDispatcherReady(DispatcherReadyVo dispatcherReadyVo) {
		return dispatcherDao.insertDispatcherReady(dispatcherReadyVo);
	}
	
}
