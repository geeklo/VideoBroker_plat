package com.ivr.plat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.ivr.plat.dao.IMobileAreaDao;

@Repository("mobileAreaDao")
public class MobileAreaDaoImpl implements IMobileAreaDao {
	private static Logger logger = Logger.getLogger(MobileAreaDaoImpl.class);
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**获得手机区域列表*/
	@Override
	public List<String[]> getMobileArea() {
		String sql = "select `start`, province, city from tb_ivr_mobile_area";
		logger.info(String.format("获得手机区域列表[%s]", sql));
		List<String[]> list = jdbcTemplate.query(sql, rowMapper);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	/**数据转换  返回数组   0 号段前7位  1 省份  2 市*/
	private ParameterizedRowMapper<String[]> rowMapper = new ParameterizedRowMapper<String[]>() {
		@Override
		public String[] mapRow(ResultSet rs, int rowNum) throws SQLException {
			String[] location = new String[3];
			location[0] = rs.getString("start");
			location[1] = rs.getString("province");
			location[2] = rs.getString("city");
			return location;
		}
	};
}
