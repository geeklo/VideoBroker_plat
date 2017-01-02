package com.ivr.plat.queue;

import com.ivr.plat.msg.HttpMsg;

public abstract interface ServiceIF<T extends HttpMsg> {
	public abstract boolean doProcess(T paramT);
}