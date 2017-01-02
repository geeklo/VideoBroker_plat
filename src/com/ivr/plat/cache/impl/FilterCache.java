package com.ivr.plat.cache.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivr.plat.cache.Cacheable;
import com.ivr.plat.dao.IFilterDao;
import com.ivr.plat.vo.FilterVo;

@Service("filterCache")
public class FilterCache implements Cacheable {
	private static Logger logger = Logger.getLogger(FilterCache.class);
	
	public static AbstractMap<String, List<FilterVo>> FILTERLISTMAP = new ConcurrentHashMap<String, List<FilterVo>>();
	@Autowired
	private IFilterDao filterDao;
	
	@Override
	public void run() {
		/**获得所有的过滤配置信息列表*/
		List<FilterVo> filterVoList = filterDao.getFilterVoList();
		/**定义存放过滤配置信息的中间Map*/
		AbstractMap<String, List<FilterVo>> filterVoListMap = new ConcurrentHashMap<String, List<FilterVo>>();
		
		if(filterVoList != null && filterVoList.size() > 0){
			for(FilterVo filterVo : filterVoList){
				List<FilterVo> filterList = new ArrayList<FilterVo>();
				if(filterVoListMap.get(filterVo.getSp_number()) != null){
					filterVoListMap.get(filterVo.getSp_number()).add(filterVo);
				}else{
					filterList.add(filterVo);
					filterVoListMap.put(filterVo.getSp_number(), filterList);
				}
			}
		}
		
		FILTERLISTMAP = filterVoListMap;
		logger.info(String.format("[FilterCache]成功加载配置[%d]条!", FILTERLISTMAP.size()));
		
	}

	@Override
	public Object getCache() {
		return FILTERLISTMAP;
	}

}
