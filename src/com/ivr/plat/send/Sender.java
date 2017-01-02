package com.ivr.plat.send;

import java.io.Serializable;

public interface Sender<T extends Object, K extends Serializable> {

	public T getDestination();

	public void setDestination(T destination);

	public Object sendObject(K msg) throws Throwable;

	public Object sendObject(T destination, K msg) throws Throwable;

	public Object sendText(String msg) throws Throwable;

	public Object sendText(T destination, String msg) throws Throwable;

}