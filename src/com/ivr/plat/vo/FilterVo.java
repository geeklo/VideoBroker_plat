package com.ivr.plat.vo;

import java.io.Serializable;
/**
 * 过滤信息VO
 * @author liugeng
 *
 */
public class FilterVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8178927980116901200L;
	private int id;				//过滤信息ID
	private int lower_id;		//下游合作方ID
	private String sp_number;	//业务长号码
	private String province;	//省份
	private int num;			//限量比例 采用百分比
	private String start_time;	//开始时间
	private String end_time;	//结束时间
	private int status;			//状态标志是否有效  0 无效 1 有效
	private String add_date;	//当前时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLower_id() {
		return lower_id;
	}
	public void setLower_id(int lower_id) {
		this.lower_id = lower_id;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
}
