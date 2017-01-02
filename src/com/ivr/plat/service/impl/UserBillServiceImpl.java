package com.ivr.plat.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivr.plat.dao.IUserBillDao;
import com.ivr.plat.service.IUserBillService;
import com.ivr.plat.vo.ReceiveVo;
/**
 * 账单信息服务实现层
 * @author liugeng
 *
 */
@Service("userBillService")
public class UserBillServiceImpl implements IUserBillService {
	private static Logger logger = Logger.getLogger(UserBillServiceImpl.class);
	
	@Autowired
	private IUserBillDao userBillDao;
	/**保存用户账单数据*/
	@Override
	public int insertUserBill(ReceiveVo receiveVo) {
		return userBillDao.insertUserBill(receiveVo);
	}
}
