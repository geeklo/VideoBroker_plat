package com.ivr.plat.vo;

import java.io.Serializable;

/**
 * 网关信息VO
 * @author liugeng
 *
 */
public class GatewayVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9000805406082813873L;
	private int id;				//网关信息ID
	private String name;		//网关名称
	private String detail;		//网关详情
	private String sp_number;	//网关长号码
	private int fee_type;		//计费类型  1 按时计费  2 按条计费
	private int fee_code;		//计费金额(分)
	private int upper_id;		//上游合作方ID
	private String bindip;		//上游合作方绑定IP
	private String mobile;		//参数名--手机号码
	private String long_number;	//参数名--长号码
	private String linkid;		//参数名--唯一标识
	private String minute;		//参数名--分
	private String second;		//参数名--秒
	private String stime;		//参数名--开始时间
	private String etime;		//参数名--结束时间
	private String response;	//长号码接收成功返回值
	private int status;			//状态  0 无效 1 有效
	private String add_date;	//网关信息添加日期
	private int add_user;		//网关信息添加用户ID
	private String update_date; //网关信息更新日期
	private int update_user;	//网关信息更新用户ID
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
	public String getSp_number() {
		return sp_number;
	}
	public void setSp_number(String sp_number) {
		this.sp_number = sp_number;
	}
	public int getFee_type() {
		return fee_type;
	}
	public void setFee_type(int fee_type) {
		this.fee_type = fee_type;
	}
	public int getFee_code() {
		return fee_code;
	}
	public void setFee_code(int fee_code) {
		this.fee_code = fee_code;
	}
	public int getUpper_id() {
		return upper_id;
	}
	public void setUpper_id(int upper_id) {
		this.upper_id = upper_id;
	}
	public String getBindip() {
		return bindip;
	}
	public void setBindip(String bindip) {
		this.bindip = bindip;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLong_number() {
		return long_number;
	}
	public void setLong_number(String long_number) {
		this.long_number = long_number;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
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
