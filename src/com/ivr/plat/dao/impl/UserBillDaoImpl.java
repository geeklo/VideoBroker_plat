package com.ivr.plat.dao.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ivr.plat.dao.IUserBillDao;
import com.ivr.plat.util.BaseUtil;
import com.ivr.plat.vo.ReceiveVo;
/**
 * 账单信息数据实现层
 * @author liugeng
 *
 */
@Repository("userBillDao")
public class UserBillDaoImpl implements IUserBillDao {
	private static Logger logger = Logger.getLogger(UserBillDaoImpl.class);
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**保存用户账单数据*/
	@Override
	public int insertUserBill(ReceiveVo receiveVo) {
		String sql = "insert into tb_ivr_user_bill(mobile,sp_number,province,city,time_begin,time_end,second,minute,fee_number,linkId,upper_id,gateway_id,lower_id,router_id,isfilter) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] param = new Object[]{receiveVo.getMobile(),receiveVo.getSp_number(),
				receiveVo.getProvince(),receiveVo.getCity(),
				receiveVo.getStart_time(),receiveVo.getEnd_time(),
				receiveVo.getSecond(),receiveVo.getMinute(),
				receiveVo.getFee_number(),receiveVo.getLinkid(),
				receiveVo.getUpper_id(),receiveVo.getGateway_id(),
				receiveVo.getLower_id(),receiveVo.getRouter_id(),
				receiveVo.getIsfilter()};
		logger.info(String.format("保存用户账单数据[%s]", BaseUtil.logSQL(sql, param)));
		int count = jdbcTemplate.update(sql, param);
		return count;
	}
}
