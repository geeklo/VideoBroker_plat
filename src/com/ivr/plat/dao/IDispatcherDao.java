package com.ivr.plat.dao;

import java.util.List;

import com.ivr.plat.vo.DispatcherReadyVo;
import com.ivr.plat.vo.DispatcherResultVo;

/**
 * 转发信息数据接口层
 * @author liugeng
 *
 */
public interface IDispatcherDao {
	/**查询所有符合预转发条件的列表*/
	public List<DispatcherReadyVo> getDispatcherReadyList();
	/**根据linkId查询出转发数量  0 未转发过  非 0 是转发过几次*/
	public int getDispatcherReadyByLinkId(String linkId);
	/**根据linkId更新同步数量*/
	public int updateDispatcherReadyDisCountByLinkId(String linkId, int dis_count, int status_code);
	/**根据linkId删除同步记录*/
	public int deleteDispatcherReadyByLinkId(String linkId);
	/**保存账单转发数据*/
	public int insertDispatcherResult(DispatcherResultVo dispatcherResultVo);
	/**保存账单预转发数据*/
	public int insertDispatcherReady(DispatcherReadyVo dispatcherReadyVo);
}
