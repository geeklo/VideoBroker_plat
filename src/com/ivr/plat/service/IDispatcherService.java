package com.ivr.plat.service;

import com.ivr.plat.vo.DispatcherReadyVo;
import com.ivr.plat.vo.DispatcherResultVo;

/**
 * 转发信息服务接口层
 * @author liugeng
 *
 */
public interface IDispatcherService {
	/**保存账单转发数据*/
	public int insertDispatcherResult(DispatcherResultVo dispatcherResultVo);
	/**保存账单预转发数据*/
	public int insertDispatcherReady(DispatcherReadyVo dispatcherReadyVo);
}
