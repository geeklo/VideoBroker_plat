package com.ivr.plat.dao.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ivr.plat.dao.IPartnerLowerDao;
/**
 * 下游合作方数据实现层
 * @author liugeng
 *
 */
@Repository("partnerLowerDao")
public class PartnerLowerDaoImpl implements IPartnerLowerDao {
	private static Logger logger = Logger.getLogger(PartnerLowerDaoImpl.class);
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
}
