package com.ivr.plat.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ivr.plat.dao.IPartnerUpperDao;
import com.ivr.plat.service.IPartnerUpperService;
import com.ivr.plat.vo.PartnerUpperVo;
/**
 * 上游合作方服务实现层
 * @author liugeng
 *
 */
@Service("partnerUpperService")
public class PartnerUpperServiceImpl implements IPartnerUpperService {
	private static Logger logger = Logger.getLogger(PartnerUpperServiceImpl.class);
	
	@Autowired
	@Qualifier("partnerUpperDao")
	private IPartnerUpperDao partnerUpperDao;
	
	/**根据上游信息ID获得上游信息ID*/
	@Override
	public PartnerUpperVo getPartnerUpperVoById(int id) {
		return partnerUpperDao.getPartnerUpperVoById(id);
	}
}
