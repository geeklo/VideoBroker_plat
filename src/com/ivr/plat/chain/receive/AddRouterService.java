package com.ivr.plat.chain.receive;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ivr.plat.cache.Cacheable;
import com.ivr.plat.chain.ReceiveServiceIF;
import com.ivr.plat.vo.ReceiveVo;
import com.ivr.plat.vo.RouterVo;

@Service("addRouterService")
public class AddRouterService implements ReceiveServiceIF {
	private static Logger logger = Logger.getLogger(AddRouterService.class);
	
	@Autowired
	@Qualifier("routerCache")
	private Cacheable routerCache;
	
	@SuppressWarnings("unchecked")
	@Override
	public int doProcess(ReceiveVo receiveVo) {
		/**获得被叫号码*/
		String sp_number = receiveVo.getSp_number();
		/**获得路由信息Map*/
		ConcurrentHashMap<String, RouterVo> routerMap = (ConcurrentHashMap<String, RouterVo>) routerCache.getCache();
		RouterVo routerVo = routerMap.get(sp_number);
		if(routerVo != null){
			int gateway_id  = routerVo.getGateway_id();
			if(receiveVo.getGateway_id() == gateway_id){
				receiveVo.setRouter_id(routerVo.getId());
				receiveVo.setLower_id(routerVo.getLower_id());
				logger.debug(String.format("路由信息处理链[%s][%s]设置路由信息成功[router_id=%d, lower_id=%d]", receiveVo.getMobile(), sp_number, routerVo.getId(), routerVo.getLower_id()));
				return 1;
			}else{
				logger.debug(String.format("路由信息处理链[%s][%s]路由配置信息[gateway_id=%d]与网关配置信息[gateway_id=%d]不匹配!", receiveVo.getMobile(), sp_number, gateway_id, receiveVo.getGateway_id()));
			}
		}
		logger.debug(String.format("路由信息处理链[%s][%s]获取路由信息失败!", receiveVo.getMobile(), sp_number));
		receiveVo.setRouter_id(0);
		receiveVo.setLower_id(0);
		/**返回0 代表不需要继续验证*/
		return 0;
	}

}
