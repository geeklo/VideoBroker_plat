package com.ivr.plat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.ivr.plat.dao.IPartnerUpperDao;
import com.ivr.plat.util.BaseUtil;
import com.ivr.plat.vo.PartnerUpperVo;
/**
 * 上游合作方数据实现层
 * @author liugeng
 *
 */
@Repository("partnerUpperDao")
public class PartnerUpperDaoImpl implements IPartnerUpperDao {
	private static Logger logger = Logger.getLogger(PartnerUpperDaoImpl.class);
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**根据上游信息ID获得上游信息ID*/
	@SuppressWarnings("unchecked")
	@Override
	public PartnerUpperVo getPartnerUpperVoById(int id) {
		String sql = "select id, name, bindip from tb_ivr_partner_upper where id = ? and status = 1";
		Object[] param = new Object[]{id};
		logger.debug(String.format("根据上游信息ID获得上游信息[%s]", BaseUtil.logSQL(sql, param)));
		List<PartnerUpperVo> partnerUpperVoList = jdbcTemplate.query(sql, param, rowMapper);
		if(partnerUpperVoList != null && partnerUpperVoList.size() > 0){
			return partnerUpperVoList.get(0);
		}
		return null;
	}
	
	private ParameterizedRowMapper<PartnerUpperVo> rowMapper = new ParameterizedRowMapper<PartnerUpperVo>() {

		@Override
		public PartnerUpperVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PartnerUpperVo partnerUpperVo = new PartnerUpperVo();
			partnerUpperVo.setId(rs.getInt("id"));
			partnerUpperVo.setName(rs.getString("name"));
			partnerUpperVo.setBindip(rs.getString("bindip"));
			return partnerUpperVo;
		}
	};
}
