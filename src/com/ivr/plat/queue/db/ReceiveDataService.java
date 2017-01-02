package com.ivr.plat.queue.db;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivr.plat.queue.ServiceIF;
import com.ivr.plat.service.IUserBillService;
import com.ivr.plat.vo.ReceiveVo;

@Service("receiveDataService")
public class ReceiveDataService implements ServiceIF<ReceiveVo> {
	private static Logger logger = Logger.getLogger(ReceiveDataService.class);
	
	@Autowired
	private IUserBillService userBillService;
	
	@Override
	public boolean doProcess(ReceiveVo receiveVo) {
		logger.debug(String.format("[%s][%s]入库操作开始!", receiveVo.getMobile(), receiveVo.getSp_number()));
		userBillService.insertUserBill(receiveVo);
		logger.debug(String.format("[%s][%s]入库操作结束!", receiveVo.getMobile(), receiveVo.getSp_number()));
		return true;
	}
}
