package com.ivr.plat.queue.receive;

import java.util.Queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ivr.plat.chain.ReceiveServiceIF;
import com.ivr.plat.msg.HttpMsg;
import com.ivr.plat.queue.ServiceIF;
import com.ivr.plat.util.SpringServiceUtil;
import com.ivr.plat.vo.ReceiveVo;
/**
 * 业务处理链
 * @author liugeng
 *
 */
@Service("receiveService")
public class ReceiveService implements ServiceIF<ReceiveVo> {
	private static Logger logger = Logger.getLogger(ReceiveService.class);
	
	@Autowired
	@Qualifier("receiveChain")
	private ReceiveServiceIF receiveChain;
	@Autowired
	@Qualifier("serviceLocator")
	private SpringServiceUtil springServiceUtil;

	@Override
	public boolean doProcess(ReceiveVo receiveVo) {
		logger.debug(String.format("[%s][%s]处理业务开始", receiveVo.getMobile(), receiveVo.getSp_number()));
		/**
		 * 处理链处理业务具体如下：
		 * 1.补充省市
		 * 2.获得下游和路由信息
		 * 3.过滤业务
		 */
		receiveChain.doProcess(receiveVo);
		logger.debug(String.format("[%s][%s]链处理完成", receiveVo.getMobile(), receiveVo.getSp_number()));
		
		/**放入入库队列和转发队列*/
		Queue<HttpMsg> dbQueue = (Queue<HttpMsg>) springServiceUtil.getService("dbQueue");
		dbQueue.add(receiveVo);
		logger.debug(String.format("[%s][%s]放入入库队列完成", receiveVo.getMobile(), receiveVo.getSp_number()));
		
		/**判断用户是否被过滤*/
		if(receiveVo.getIsfilter() == 0){
			Queue<HttpMsg> dispatchQueue = (Queue<HttpMsg>) springServiceUtil.getService("dispatchQueue");
			dispatchQueue.add(receiveVo);
			logger.debug(String.format("[%s][%s]放入转发队列完成", receiveVo.getMobile(), receiveVo.getSp_number()));
		}else{
			logger.debug(String.format("[%s][%s]被过滤, 不需要放入转发队列!", receiveVo.getMobile(), receiveVo.getSp_number()));
		}
		
		return true;
	}

}
