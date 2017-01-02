package com.ivr.plat.cache.impl;

import java.util.AbstractMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivr.plat.cache.Cacheable;
import com.ivr.plat.dao.IRouterDao;
import com.ivr.plat.vo.RouterVo;

@Service("routerCache")
public class RouterCache implements Cacheable {
	private static Logger logger = Logger.getLogger(RouterCache.class);
	
	private static AbstractMap<String, RouterVo> ROUTERMAP = new ConcurrentHashMap<String, RouterVo>();
	@Autowired
	private IRouterDao routerDao;
	@Override
	public void run() {
		/**获得路由列表*/
		List<RouterVo> routerVoList = routerDao.getRouterVoList();
		AbstractMap<String, RouterVo> routerMap = new ConcurrentHashMap<String, RouterVo>();
		
		if(routerVoList != null && routerVoList.size() > 0){
			for(RouterVo routerVo : routerVoList){
				/**获得业务号码长号码*/
				String sp_number = routerVo.getSp_number();
				routerMap.put(sp_number,routerVo);
			}
		}
		
		ROUTERMAP = routerMap;
		logger.info(String.format("[RouterCache]成功加载配置[%d]条!", ROUTERMAP.size()));
		
	}

	@Override
	public Object getCache() {
		return ROUTERMAP;
	}

}
