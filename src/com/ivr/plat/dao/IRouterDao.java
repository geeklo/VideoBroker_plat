package com.ivr.plat.dao;

import java.util.List;

import com.ivr.plat.vo.RouterVo;

/**
 * 路由信息数据接口层
 * @author liugeng
 *
 */
public interface IRouterDao {
	/**获得路由信息列表*/
	public List<RouterVo> getRouterVoList();
}
