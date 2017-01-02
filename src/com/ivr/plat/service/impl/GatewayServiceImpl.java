package com.ivr.plat.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ivr.plat.dao.IGatewayDao;
import com.ivr.plat.service.IGatewayService;
import com.ivr.plat.vo.GatewayVo;
/**
 * 网关信息服务实现层
 * @author liugeng
 *
 */
@Service("gatewayService")
public class GatewayServiceImpl implements IGatewayService {
	private static Logger logger = Logger.getLogger(GatewayServiceImpl.class);
	
	@Autowired
	@Qualifier("gatewayDao")
	private IGatewayDao gatewayDao;

	@Override
	public GatewayVo getGatewayVoById(int gatewayId) {
		return gatewayDao.getGatewayVoById(gatewayId);
	}
	
}
