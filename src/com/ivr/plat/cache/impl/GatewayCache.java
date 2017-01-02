package com.ivr.plat.cache.impl;

import java.util.AbstractMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivr.plat.cache.Cacheable;
import com.ivr.plat.dao.IGatewayDao;
import com.ivr.plat.vo.GatewayVo;

@Service("gatewayCache")
public class GatewayCache implements Cacheable {
	private static Logger logger = Logger.getLogger(GatewayCache.class);
	
	private static AbstractMap<Integer, GatewayVo> GATEWAYMAP = new ConcurrentHashMap<Integer, GatewayVo>();
	@Autowired
	private IGatewayDao gatewayDao;
	
	@Override
	public void run() {
		/**获得路由列表*/
		List<GatewayVo> gatewayVoList = gatewayDao.getGatewayVoList();
		AbstractMap<Integer, GatewayVo> gatewayMap = new ConcurrentHashMap<Integer, GatewayVo>();
		
		if(gatewayVoList != null && gatewayVoList.size() > 0){
			for(GatewayVo gatewayVo : gatewayVoList){
				/**获得业务号码长号码*/
				int gateway_id = gatewayVo.getId();
				gatewayMap.put(gateway_id,gatewayVo);
			}
		}
		
		GATEWAYMAP = gatewayMap;
		logger.info(String.format("[GatewayCache]成功加载配置[%d]条!", GATEWAYMAP.size()));
	}

	@Override
	public Object getCache() {
		return GATEWAYMAP;
	}

}
