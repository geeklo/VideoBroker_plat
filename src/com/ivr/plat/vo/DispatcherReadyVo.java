package com.ivr.plat.vo;

import java.io.Serializable;

import com.ivr.plat.msg.HttpMsg;
/**
 * 预转发信息VO
 * @author liugeng
 *
 */
public class DispatcherReadyVo implements HttpMsg, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8713983767837462001L;
	private int id;					//主叫Id
	private String mobile;			//主叫号码
	private String sp_number;		//业务号码 长号码
	private String start_time;		//开始时间
	private String end_time;		//结束时间
	private int second;				//拨打时长（秒）
	private int minute;				//拨打时长（分）
	private String linkid;			//唯一标识
	private int lower_id;			//渠道ID
	private int router_id;			//路由ID
	private int status_code;		//同步状态
	private int dis_count;			//转发次数
	private String add_date;			//创建时间
	private String reserve;			//备用字段
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
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
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
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
}
