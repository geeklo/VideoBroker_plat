package com.ivr.plat.dao;

import com.ivr.plat.vo.ReceiveVo;

/**
 * 账单信息数据接口层
 * @author liugeng
 *
 */
public interface IUserBillDao {
	/**保存用户账单数据*/
	public int insertUserBill(ReceiveVo receiveVo);
}
