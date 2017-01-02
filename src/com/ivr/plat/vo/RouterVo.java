package com.ivr.plat.vo;

import java.io.Serializable;
/**
 * 路由信息VO
 * @author liugeng
 *
 */
public class RouterVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 993333753553560027L;
	private int id;				//路由信息ID
	private String name;		//路由信息名称
	private String detail;		//路由信息详情
	private int gateway_id;		//网关业务ID号
	private String sp_number;	//业务长号码
	private String sync_url;	//路由地址
	private int lower_id;		//下游合作方Id
	private int status;			//是否有效  0 无效 1 有效
	private String add_date;	//创建时间
	private int add_user;		//创建用户ID
	private String update_date; //更新时间
	private int update_user;	//更新用户ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getGateway_id() {
		return gateway_id;
	}
	public void setGateway_id(int gateway_id) {
		this.gateway_id = gateway_id;
	}
	public String getSp_number() {
		return sp_number;
	}
	public void setSp_number(String sp_number) {
		this.sp_number = sp_number;
	}
	public String getSync_url() {
		return sync_url;
	}
	public void setSync_url(String sync_url) {
		this.sync_url = sync_url;
	}
	public int getLower_id() {
		return lower_id;
	}
	public void setLower_id(int lower_id) {
		this.lower_id = lower_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}
	public int getAdd_user() {
		return add_user;
	}
	public void setAdd_user(int add_user) {
		this.add_user = add_user;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public int getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(int update_user) {
		this.update_user = update_user;
	}
}
