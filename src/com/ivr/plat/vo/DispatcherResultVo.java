package com.ivr.plat.vo;

import java.io.Serializable;

import com.ivr.plat.msg.HttpMsg;

/**
 * 转发结果信息VO
 * @author liugeng
 *
 */
public class DispatcherResultVo implements HttpMsg, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3092856951068775216L;
	private int id;				
	private String mobile;		//手机号码
	private String linkid;		//唯一标识
	private String sync_url;	//同步地址
	private String parameter;	//同步参数
	private String type;		//同步类型
	private int lower_id;		//下游合作方ID
	private int router_id;		//路由信息ID
	private int status_code;	//同步状态
	private int dis_count;		//同步次数
	private String add_date;	//创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getSync_url() {
		return sync_url;
	}
	public void setSync_url(String sync_url) {
		this.sync_url = sync_url;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLower_id() {
		return lower_id;
	}
	public void setLower_id(int lower_id) {
		this.lower_id = lower_id;
	}
	public int getRouter_id() {
		return router_id;
	}
	public void setRouter_id(int router_id) {
		this.router_id = router_id;
	}
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public int getDis_count() {
		return dis_count;
	}
	public void setDis_count(int dis_count) {
		this.dis_count = dis_count;
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}
}
