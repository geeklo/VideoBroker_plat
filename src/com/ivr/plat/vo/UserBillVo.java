package com.ivr.plat.vo;

import java.io.Serializable;
/**
 * 账单信息VO
 * @author liugeng
 *
 */
public class UserBillVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2631318395472278973L;
	private int id;					//账单表ID
	private String mobile;			//主叫号码
	private String sp_number;		//业务号码长号码
	private String province;		//省份
	private String city;			//城市
	private String time_begin;		//开始时间
	private String time_end;		//结束时间
	private int second;				//拨打时长（秒）
	private int minute;				//拨打时长（分）
	private int fee_number;			//计费金额
	private String linkid;			//唯一标识
	private int upper_id;			//上游合作方ID
	private int gateway_id;			//网关信息ID
	private int lower_id;			//下游合作方Id
	private int router_id;			//路由ID
	private int isfilter;			//是否过滤  0未过滤  1 过滤
	private String add_date;		//入库时间
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
	public String getSp_number() {
		return sp_number;
	}
	public void setSp_number(String sp_number) {
		this.sp_number = sp_number;
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
	public String getTime_begin() {
		return time_begin;
	}
	public void setTime_begin(String time_begin) {
		this.time_begin = time_begin;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getFee_number() {
		return fee_number;
	}
	public void setFee_number(int fee_number) {
		this.fee_number = fee_number;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
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
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}
}
