package com.ivr.plat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.ivr.plat.dao.IDispatcherDao;
import com.ivr.plat.util.BaseUtil;
import com.ivr.plat.vo.DispatcherReadyVo;
import com.ivr.plat.vo.DispatcherResultVo;
/**
 * 转发信息数据服务层
 * @author liugeng
 *
 */
@Repository("dispatcherDao")
public class DispatcherDaoImpl implements IDispatcherDao {
	private static Logger logger = Logger.getLogger(DispatcherDaoImpl.class);
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**查询所有符合预转发条件的列表*/
	@Override
	public List<DispatcherReadyVo> getDispatcherReadyList() {
		String sql = "select mobile, sp_number, start_time, end_time, second, minute, linkid, lower_id, router_id, status_code, dis_count from tb_ivr_dispatcher_ready where dis_count < 5";
		logger.info(String.format("查询所有符合预转发条件的列表[%s]", sql));
		List<DispatcherReadyVo> dispatcherReadyVoList = jdbcTemplate.query(sql, tempMapper);
		if(dispatcherReadyVoList != null && dispatcherReadyVoList.size() > 0){
			return dispatcherReadyVoList;
		}
		
		return null;
	}
	private ParameterizedRowMapper<DispatcherReadyVo> tempMapper = new ParameterizedRowMapper<DispatcherReadyVo>() {
		@Override
		public DispatcherReadyVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DispatcherReadyVo dispatcherReadyVo = new DispatcherReadyVo();
			dispatcherReadyVo.setMobile(rs.getString("mobile"));
			dispatcherReadyVo.setSp_number(rs.getString("sp_number"));
			dispatcherReadyVo.setStart_time(String.valueOf(rs.getTimestamp("start_time")));
			dispatcherReadyVo.setEnd_time(String.valueOf(rs.getTimestamp("end_time")));
			dispatcherReadyVo.setSecond(rs.getInt("second"));
			dispatcherReadyVo.setMinute(rs.getInt("minute"));
			dispatcherReadyVo.setLinkid(rs.getString("linkid"));
			dispatcherReadyVo.setLower_id(rs.getInt("lower_id"));
			dispatcherReadyVo.setRouter_id(rs.getInt("router_id"));
			dispatcherReadyVo.setStatus_code(rs.getInt("status_code"));
			dispatcherReadyVo.setDis_count(rs.getInt("dis_count"));
			return dispatcherReadyVo;
		}
	};
	
	/**根据linkId查询出转发数量  0 未转发过  非 0 是转发过几次*/
	@SuppressWarnings("unchecked")
	@Override
	public int getDispatcherReadyByLinkId(String linkId) {
		String sql = "select dis_count from tb_ivr_dispatcher_ready where linkId = ? order by dis_count desc";
		Object[] param = new Object[]{linkId};
		
		logger.info(String.format("根据linkId查询出转发数量[%s]", BaseUtil.logSQL(sql, param)));
		
		List<DispatcherReadyVo> dispatcherReadyVoList = jdbcTemplate.query(sql, param, rowMapper);
		if(dispatcherReadyVoList != null && dispatcherReadyVoList.size() > 0){
			return dispatcherReadyVoList.get(0).getDis_count();
		}
		return 0;
	}
	
	private ParameterizedRowMapper<DispatcherReadyVo> rowMapper = new ParameterizedRowMapper<DispatcherReadyVo>() {
		@Override
		public DispatcherReadyVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DispatcherReadyVo dispatcherReadyVo = new DispatcherReadyVo();
			dispatcherReadyVo.setDis_count(rs.getInt("dis_count"));
			return dispatcherReadyVo;
		}
	};
	/**根据linkId更新同步数量*/
	@Override
	public int updateDispatcherReadyDisCountByLinkId(String linkId, int dis_count, int status_code) {
		String sql = "update tb_ivr_dispatcher_ready set dis_count = ?, status_code = ? where linkId = ?";
		Object[] param = new Object[]{dis_count, status_code, linkId};
		logger.info(String.format("根据linkId更新同步次数[%s]", BaseUtil.logSQL(sql, param)));
		
		int count = jdbcTemplate.update(sql, param);
		return count;
	}
	
	/**根据linkId删除同步记录*/
	@Override
	public int deleteDispatcherReadyByLinkId(String linkId) {
		String sql = "delete from tb_ivr_dispatcher_ready where linkId = ?";
		Object[] param = new Object[]{linkId};
		logger.info(String.format("根据linkId删除同步记录[%s]", BaseUtil.logSQL(sql, param)));
		
		int count = jdbcTemplate.update(sql, param);
		return count;
	}
	/**保存账单转发数据*/
	@Override
	public int insertDispatcherResult(DispatcherResultVo dispatcherResultVo) {
		String sql = "insert into tb_ivr_dispatcher_result(mobile,linkid,sync_url,parameter,type,lower_id,router_id,status_code,dis_count) values(?,?,?,?,?,?,?,?,?)";
		Object[] param = new Object[]{dispatcherResultVo.getMobile(),dispatcherResultVo.getLinkid(),
									dispatcherResultVo.getSync_url(),dispatcherResultVo.getParameter(),
									dispatcherResultVo.getType(),dispatcherResultVo.getLower_id(),
									dispatcherResultVo.getRouter_id(),dispatcherResultVo.getStatus_code(),
									dispatcherResultVo.getDis_count()};
		logger.info(String.format("保存账单转发数据[%s]", BaseUtil.logSQL(sql, param)));
		int count = jdbcTemplate.update(sql, param);
		return count;
	}
	/**保存账单转发数据*/
	@Override
	public int insertDispatcherReady(DispatcherReadyVo dispatcherReadyVo) {
		String sql = "insert into tb_ivr_dispatcher_ready(mobile,sp_number,start_time,end_time,second,minute,linkId,lower_id,router_id,status_code,dis_count) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] param = new Object[]{dispatcherReadyVo.getMobile(),dispatcherReadyVo.getSp_number(),
									dispatcherReadyVo.getStart_time(),dispatcherReadyVo.getEnd_time(),
									dispatcherReadyVo.getSecond(),dispatcherReadyVo.getMinute(),
									dispatcherReadyVo.getLinkid(),dispatcherReadyVo.getLower_id(),
									dispatcherReadyVo.getRouter_id(),dispatcherReadyVo.getStatus_code(),
									dispatcherReadyVo.getDis_count()};
		logger.info(String.format("保存账单转发数据[%s]", BaseUtil.logSQL(sql, param)));
		int count = jdbcTemplate.update(sql, param);
		return count;
	}
}
