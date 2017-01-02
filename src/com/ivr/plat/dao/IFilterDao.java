package com.ivr.plat.dao;

import java.util.List;

import com.ivr.plat.vo.FilterVo;

/**
 * 过滤信息数据接口层
 * @author liugeng
 *
 */
public interface IFilterDao {
	/**获得过滤列表*/
	public List<FilterVo> getFilterVoList();
}
