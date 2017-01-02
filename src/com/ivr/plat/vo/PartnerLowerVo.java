package com.ivr.plat.vo;

import java.io.Serializable;
/**
 * 下游合作方信息VO
 * @author liugeng
 *
 */
public class PartnerLowerVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 230350382575295619L;
	private int id;				//下游合作方ID
	private String name;		//下游合作方名称
	private String detail;		//下游合作方详情
	private int user_id;		//下游合作方用户ID
	private int status;			//下游合作方状态  0 无效  1 有效
	private String add_date;	//下游合作方添加日期
	private int add_user;		//下游合作方添加用户ID
	private String update_date; //下游合作方更新日期
	private int update_user;	//下游合作方更新用户ID
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
