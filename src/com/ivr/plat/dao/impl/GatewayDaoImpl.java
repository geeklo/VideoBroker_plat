package com.ivr.plat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.ivr.plat.dao.IGatewayDao;
import com.ivr.plat.util.BaseUtil;
import com.ivr.plat.vo.GatewayVo;
/**
 * 网关信息数据实现层
 * @author liugeng
 *
 */
@Repository("gatewayDao")
public class GatewayDaoImpl implements IGatewayDao {
	private static Logger logger = Logger.getLogger(GatewayDaoImpl.class);
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**根据网关信息ID 获得网关信息*/
	@Override
	public GatewayVo getGatewayVoById(int gatewayId) {
		String sql = "select g.id, g.sp_number, g.fee_type, g.fee_code, g.upper_id, pu.bindip, g.mobile, g.long_number, g.linkid, g.minute, g.second, g.stime, g.etime, g.response " +
					 "from tb_ivr_gateway g " +
					 "inner join tb_ivr_partner_upper pu on pu.id = g.upper_id and pu.status = 1 " +
					 "where g.id = ? and g.status = 1";
		Object[] param = new Object[]{gatewayId};
		logger.debug(String.format("根据网关信息ID获得网关信息[%s]", BaseUtil.logSQL(sql, param)));
		List<GatewayVo> gatewayVoList = jdbcTemplate.query(sql, param, rowMapper);
		if(gatewayVoList != null && gatewayVoList.size() > 0){
			return gatewayVoList.get(0);
		}
		return null;
	}
	/**获得网关配置列表*/
	@Override
	public List<GatewayVo> getGatewayVoList() {
		String sql = "select g.id, g.sp_number, g.fee_type, g.fee_code, g.upper_id, pu.bindip, g.mobile, g.long_number, g.linkid, g.minute, g.second, g.stime, g.etime, g.response " +
				 "from tb_ivr_gateway g " +
				 "inner join tb_ivr_partner_upper pu on pu.id = g.upper_id and pu.status = 1 " +
				 "where g.status = 1";
		logger.debug(String.format("获得网关配置列表[%s]", sql));
		List<GatewayVo> gatewayVoList = jdbcTemplate.query(sql, rowMapper);
		if(gatewayVoList != null && gatewayVoList.size() > 0){
			return gatewayVoList;
		}
		return null;
	}
	
	private ParameterizedRowMapper<GatewayVo> rowMapper = new ParameterizedRowMapper<GatewayVo>() {

		@Override
		public GatewayVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			GatewayVo gatewayVo = new GatewayVo();
			gatewayVo.setId(rs.getInt("id"));
			gatewayVo.setSp_number(rs.getString("sp_number"));
			gatewayVo.setFee_type(rs.getInt("fee_type"));
			gatewayVo.setFee_code(rs.getInt("fee_code"));
			gatewayVo.setUpper_id(rs.getInt("upper_id"));
			gatewayVo.setBindip(rs.getString("bindip"));
			gatewayVo.setMobile(rs.getString("mobile"));
			gatewayVo.setLong_number(rs.getString("long_number"));
			gatewayVo.setLinkid(rs.getString("linkid"));
			gatewayVo.setMinute(rs.getString("minute"));
			gatewayVo.setSecond(rs.getString("second"));
			gatewayVo.setStime(rs.getString("stime"));
			gatewayVo.setEtime(rs.getString("etime"));
			gatewayVo.setResponse(rs.getString("response"));
			return gatewayVo;
		}
	};

}
