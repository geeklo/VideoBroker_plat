package com.ivr.plat.service;

import com.ivr.plat.vo.GatewayVo;

/**
 * 网关信息服务接口层
 * @author liugeng
 *
 */
public interface IGatewayService {
	/**根据网关信息ID 获得网关信息*/
	public GatewayVo getGatewayVoById(int gatewayId);
}
