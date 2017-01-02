package com.ivr.plat.cache.impl;

import java.util.AbstractMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivr.plat.cache.Cacheable;
import com.ivr.plat.dao.IMobileAreaDao;

/**
 * 手机号段缓存服务
 * @author liugeng
 * @date 2015-12-15
 *
 */
@Service("mobileAreaCache")
public class MobileAreaCache implements Cacheable {
	private static Logger logger = Logger.getLogger(MobileAreaCache.class);
	
	/**
	 * <pre>
	 * 	<title>初始化内存信息</title>
	 * 	<p>
	 * 		原则：速度快、内存消耗少。内存地址来存放手机号段，速度最快，但消耗内存大。
	 * 			 用手机前三位来划分内存，中间4位作为号段内存地址。
	 * 		1、取出数据库中的所有数据。
	 * 		2、划分存放数据的内存块，根据手机号码前三位划分
	 * 		3、在划分好内存块的情况下，根据中间四位存放号段属性信息
	 * 		MAP的内存划分如下
	 * 		 				    |   0001  |
	 * 		 			152		|   ……    |
	 * 							|   1095  |--- PhoneArea :手机号段属性信息   
	 * 		 		------------------------------------------------------
	 * 		 	  				|   0001  |
	 * 					131		|   ……    |
	 * 		 					|   9999  |
	 * 		
	 * 		String[][]的结构为String[10000][2]，第一维度对应号段表中的第4位到第7位，0000到9999。第二维对应省和市
	 * 	</p>
	 * </pre>
	 */
	private static AbstractMap<Integer, String[][]> MOBILEAREACACHE = new ConcurrentHashMap<Integer, String[][]>();
	@Autowired
	private IMobileAreaDao mobileAreaDao;

	@Override
	public void run() {
		/**从数据库获得所有号段信息列表 返回数组列表   0 号段前7位  1 省份  2 市*/
		List<String[]> mobileAreaList = mobileAreaDao.getMobileArea();
		
		/**定义Map信息来存放号段信息*/
		ConcurrentHashMap<Integer, String[][]> map = new ConcurrentHashMap<Integer, String[][]>();
		/**判断获得的列表是否为空*/
		if(mobileAreaList != null && mobileAreaList.size() > 0){
			for(String[] mobileArea : mobileAreaList){
				addMobileArea(map,mobileArea);
			}
		}
		
		MOBILEAREACACHE = map;
		logger.info(String.format("[MobileAreaCache]共加载数据[%d]条", MOBILEAREACACHE.size()));
	}
	
	private void addMobileArea(ConcurrentHashMap<Integer, String[][]> map,String[] mobileArea){
		String start = mobileArea[0];
		Integer startP3 = 0;
		Integer startL4 = 0;
		try{
			/**获得手机号段前三位*/
			startP3 = Integer.parseInt(start.substring(0,3));
			/**获得手机号段中间四位*/
			startL4 = Integer.parseInt(start.substring(3,7));
			
			if(startP3 < 100){
				logger.error("[MobileAreaCache]不合法的号段");
				return;
			}
		}catch (NumberFormatException e) {
			logger.error(String.format("号段转换异常,[MobileAreaCache]不合法的号段%s", ExceptionUtils.getFullStackTrace(e)));
		}
		
		String temp[][] = map.get(startP3);
		/**判断号段中是否已经存在  已经存在的往数组里添加   不存在的则新增数组信息*/
		if(temp == null){
			temp = new String[10000][2];
			temp[startL4][0] = mobileArea[1];
			temp[startL4][1] = mobileArea[2];
			map.put(startP3,temp);
		}else{
			temp[startL4][0] = mobileArea[1];
			temp[startL4][1] = mobileArea[2];
		}
	}
	
	@Override
	public Object getCache() {
		return MOBILEAREACACHE;
	}
}