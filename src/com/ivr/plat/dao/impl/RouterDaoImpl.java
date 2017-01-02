package com.ivr.plat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.ivr.plat.dao.IRouterDao;
import com.ivr.plat.vo.RouterVo;
/**
 * 路由信息数据实现层
 * @author liugeng
 *
 */
@Repository("routerDao")
public class RouterDaoImpl implements IRouterDao {
	private static Logger logger = Logger.getLogger(RouterDaoImpl.class);
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	/**获得路由信息列表*/
	@Override
	public List<RouterVo> getRouterVoList() {
		String sql = "select r.id, r.gateway_id, r.sp_number, r.sync_url, r.lower_id " +
					 "from tb_ivr_router r " +
					 "inner join tb_ivr_partner_lower pl on pl.id = r.lower_id and pl.status = 1 " +
					 "where r.status = 1";
		logger.debug(String.format("获得路由信息列表[%s]", sql));
		List<RouterVo> routerVoList = jdbcTemplate.query(sql, rowMapper);
		if(routerVoList != null && routerVoList.size() > 0){
			return routerVoList;
		}
		return null;
	}
	
	private ParameterizedRowMapper<RouterVo> rowMapper = new ParameterizedRowMapper<RouterVo>() {
		@Override
		public RouterVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			RouterVo routerVo = new RouterVo();
			routerVo.setId(rs.getInt("id"));
			routerVo.setGateway_id(rs.getInt("gateway_id"));
			routerVo.setSp_number(rs.getString("sp_number"));
			routerVo.setSync_url(rs.getString("sync_url"));
			routerVo.setLower_id(rs.getInt("lower_id"));
			return routerVo;
		}
	};
}
