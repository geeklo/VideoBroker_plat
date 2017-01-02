package com.ivr.plat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.ivr.plat.dao.IFilterDao;
import com.ivr.plat.vo.FilterVo;
/**
 * 过滤信息数据实现层
 * @author liugeng
 *
 */
@Repository("filterDao")
public class FilterDaoImpl implements IFilterDao {
	private static Logger logger = Logger.getLogger(FilterDaoImpl.class);
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	/**获得过滤列表*/
	@Override
	public List<FilterVo> getFilterVoList() {
		String sql = "select id, num, sp_number, province, start_time, end_time from tb_ivr_filter where status = 1 order by id";
		logger.debug(String.format("获得过滤列表[%s]", sql));
		List<FilterVo> filterVoList = jdbcTemplate.query(sql, rowMapper);
		if(filterVoList != null && filterVoList.size() > 0){
			return filterVoList;
		}
		return null;
	}
	
	private ParameterizedRowMapper<FilterVo> rowMapper = new ParameterizedRowMapper<FilterVo>() {
		@Override
		public FilterVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			FilterVo filterVo = new FilterVo();
			filterVo.setId(rs.getInt("id"));
			filterVo.setNum(rs.getInt("num"));
			filterVo.setSp_number(rs.getString("sp_number"));
			filterVo.setProvince(rs.getString("province"));
			filterVo.setStart_time(rs.getString("start_time"));
			filterVo.setEnd_time(rs.getString("end_time"));
			return filterVo;
		}
	};
}
