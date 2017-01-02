package com.ivr.plat.vo;

import java.io.Serializable;

/**
 * 上游合作方信息VO
 * @author liugeng
 *
 */
public class PartnerUpperVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7440622438690017201L;
	private int id;				//上游合作方ID
	private String name;		//上游合作方名称
	private String detail;		//上游合作方详情
	private String bindip;		//上游合作方绑定IP
	private int status;			//上游合作方状态  0 无效  1 有效
	private String add_date;	//上游合作方添加日期
	private int add_user;		//上游合作方添加用户ID
	private String update_date; //上游合作方更新日期
	private int update_user;	//上游合作方更新用户ID
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
	public String getBindip() {
		return bindip;
	}
	public void setBindip(String bindip) {
		this.bindip = bindip;
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
