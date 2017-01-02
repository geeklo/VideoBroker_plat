package com.ivr.plat.chain.receive;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ivr.plat.cache.Cacheable;
import com.ivr.plat.chain.ReceiveServiceIF;
import com.ivr.plat.vo.FilterVo;
import com.ivr.plat.vo.ReceiveVo;

@Service("addFilterService")
public class AddFilterService implements ReceiveServiceIF {
	private static Logger logger = Logger.getLogger(AddFilterService.class);
	
	@Autowired
	@Qualifier("filterCache")
	private Cacheable filterCache;
	
	@Override
	public int doProcess(ReceiveVo receiveVo) {
		logger.debug(String.format("过滤业务处理链 [%s][%s]开始", receiveVo.getMobile(), receiveVo.getSp_number()));
		
		ConcurrentHashMap<String, List<FilterVo>> filterVoListMap = (ConcurrentHashMap<String, List<FilterVo>>) filterCache.getCache();
		List<FilterVo> filterVoList = filterVoListMap.get(receiveVo.getSp_number());
		/**
		 * 定义过滤返回结果
		 * -1 	该用户被过滤
		 * 0	 未设置过滤信息
		 * 1	省份不参与过滤
		 * 2	省份参与过滤,在过滤时间外
		 * 3	省份参与过滤,在过滤时间内，未被选中
		 */
		int result = 0;
		if(filterVoList != null && filterVoList.size() > 0){
			/**列表不为空检测是否被过滤*/
			for(FilterVo filterVo : filterVoList){
				if("".equals(filterVo.getProvince()) || receiveVo.getProvince().equals(filterVo.getProvince())){
					result = doFilter(filterVo);
					break;
				}
				result = 1;
				continue;
			}
		}
		
		/**判断返回结果打印日志*/
		if(result != -1){
			String text = String.format("[%s]使用[%s]业务无过滤设置", receiveVo.getMobile(),receiveVo.getSp_number());
			if(result == 1){
				text = String.format("[%s]使用[%s]业务在[%s]不参与过滤", receiveVo.getMobile(),receiveVo.getSp_number(),receiveVo.getProvince());
			}else if(result == 2){
				text = String.format("[%s]使用[%s]业务在[%s]参与过滤,过滤时间外", receiveVo.getMobile(),receiveVo.getSp_number(),receiveVo.getProvince());
			}else if(result == 3){
				text = String.format("[%s]使用[%s]业务在[%s]参与过滤,在过滤时间内,未被选中", receiveVo.getMobile(),receiveVo.getSp_number(),receiveVo.getProvince());
			}
			
			logger.info(String.format("过滤处理链[未过滤],原因:%s", text));
			receiveVo.setIsfilter(0);
		}else{
			logger.info(String.format("过滤处理链[被过滤],原因:[%s]使用[%s]业务在[%s]参与过滤,在过滤时间内且被选中", receiveVo.getMobile(),receiveVo.getSp_number(),receiveVo.getProvince()));
			receiveVo.setIsfilter(1);
		}
		return 1;
	}
	
	private static int doFilter(FilterVo filterVo){
		boolean isInTime = isInTime(filterVo);
		if(!isInTime){
			//该省份参与过滤,过滤时间外
			return 2;
		}
		/**获得要过滤的百分比例*/
		int num = filterVo.getNum();
		
		/**获得随机数 随机数与百分比做对比*/
		int random = (int) (1 + Math.random() * 100);
		if(random > num){
			//该省份参与过滤,过滤时间内,未被选中
			return 3;
		}
		//过滤时间内，该省份参与过滤，被选中
		return -1;
	}
	
	private static boolean isInTime(FilterVo filterVo){
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String current = df.format(date);
		String start = filterVo.getStart_time() + ":00";
		String end = filterVo.getEnd_time() + ":00";
		if (start.compareTo(end) < 0) { 
			// 开始时间早于结束时间
			return (current.compareTo(start) >= 0 && current.compareTo(end) <= 0);
		} else { 
			// 开始时间晚于结束时间（凌晨）
			return (current.compareTo(start) >=0 || current.compareTo(end) <= 0);
		}
	}

}
