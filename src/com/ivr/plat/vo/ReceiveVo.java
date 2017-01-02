package com.ivr.plat.vo;

import java.io.Serializable;

import com.ivr.plat.msg.HttpMsg;

public class ReceiveVo implements HttpMsg, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3875315988738865525L;
	private String mobile;		//手机号码
	private String sp_number;	//长号码
	private String linkid;		//唯一标识
	private String second;		//秒
	private String minute;		//分
	private String start_time;	//开始时间
	private String end_time;	//结束时间
	private String province;	//省份
	private String city;		//城市
	private int fee_number;		//计费金额
	private int upper_id;		//上游合作方ID
	private int gateway_id;		//网关信息ID
	private int lower_id;		//下游合作方Id
	private int router_id;		//路由ID
	private int isfilter;		//是否过滤  0未过滤  1 过滤
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSp_number() {
		return sp_number;
	}
	public void setSp_number(String sp_number) {
		this.sp_number = sp_number;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getFee_number() {
		return fee_number;
	}
	public void setFee_number(int fee_number) {
		this.fee_number = fee_number;
	}
	public int getUpper_id() {
		return upper_id;
	}
	public void setUpper_id(int upper_id) {
		this.upper_id = upper_id;
	}
	public int getGateway_id() {
		return gateway_id;
	}
	public void setGateway_id(int gateway_id) {
		this.gateway_id = gateway_id;
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
	public int getIsfilter() {
		return isfilter;
	}
	public void setIsfilter(int isfilter) {
		this.isfilter = isfilter;
	}
}
