package com.ivr.plat.service;

import com.ivr.plat.vo.ReceiveVo;

/**
 * 账单信息服务接口层
 * @author liugeng
 *
 */
public interface IUserBillService {
	/**保存用户账单数据*/
	public int insertUserBill(ReceiveVo receiveVo);
}
