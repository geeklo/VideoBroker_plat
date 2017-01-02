package com.ivr.plat.service;

import com.ivr.plat.vo.PartnerUpperVo;

/**
 * 上游合作方服务接口层
 * @author liugeng
 *
 */
public interface IPartnerUpperService {
	/**根据上游信息ID获得上游信息ID*/
	public PartnerUpperVo getPartnerUpperVoById(int id);
}
