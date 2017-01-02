package com.ivr.plat.dao;

import java.util.List;

import com.ivr.plat.vo.GatewayVo;

/**
 * 网关信息数据接口层
 * @author liugeng
 *
 */
public interface IGatewayDao {
	/**根据网关信息ID 获得网关信息*/
	public GatewayVo getGatewayVoById(int gatewayId);
	/**获得网关配置列表*/
	public List<GatewayVo> getGatewayVoList();
}
