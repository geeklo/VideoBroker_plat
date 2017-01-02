package com.ivr.plat.chain.receive;

import java.util.List;

import org.apache.log4j.Logger;

import com.ivr.plat.chain.ReceiveServiceIF;
import com.ivr.plat.vo.ReceiveVo;

public class ReceiveChain implements ReceiveServiceIF {
	private static Logger logger = Logger.getLogger(ReceiveChain.class);
	
	public static final int RECEIVE_FINISHED = 0;		//标识不用继续验证
	public static final int RECEIVE_CONTINUE = 1;		//继续验证
	
	/**定义验证节点列表*/
	private List<ReceiveServiceIF> receiveChainNodes;
	@Override
	public int doProcess(ReceiveVo receiveVo) {
		if(receiveChainNodes != null && receiveChainNodes.size() > 0){
			for(ReceiveServiceIF receiveChainNode : receiveChainNodes){
				/**处理处理链  获得返回结果  RECEIVE_CONTINUE 通过继续验证 RECEIVE_FINISHED 未通过结束验证     */
				int result = receiveChainNode.doProcess(receiveVo);
				if(result == RECEIVE_FINISHED){
					/**验证未通过，不必进行下面的验证*/
					break;
				}else if(result == RECEIVE_CONTINUE){
					/**验证通过，可继续进行下面的验证*/
					continue;
				}
			}
		}else{
			logger.debug("验证节点列表为空,请定义列表!");
		}
		/**ReceiveChain本身的返回值是没有意义的*/
		return 0;
	}
	
	public List<ReceiveServiceIF> getReceiveChainNodes() {
		return receiveChainNodes;
	}
	public void setReceiveChainNodes(List<ReceiveServiceIF> receiveChainNodes) {
		this.receiveChainNodes = receiveChainNodes;
	}

}
