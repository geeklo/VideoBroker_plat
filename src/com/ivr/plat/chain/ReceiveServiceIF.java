package com.ivr.plat.chain;

import com.ivr.plat.vo.ReceiveVo;
/**
 * 业务处理链服务接口
 * @author liugeng
 *
 */
public abstract interface ReceiveServiceIF {
	public abstract int doProcess(ReceiveVo receiveVo);
}
