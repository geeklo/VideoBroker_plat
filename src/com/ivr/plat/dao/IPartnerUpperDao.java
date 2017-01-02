package com.ivr.plat.dao;

import com.ivr.plat.vo.PartnerUpperVo;

/**
 * 上游合作方数据接口层
 * @author liugeng
 *
 */
public interface IPartnerUpperDao {
	/**根据上游信息ID获得上游信息ID*/
	public PartnerUpperVo getPartnerUpperVoById(int id);
}
