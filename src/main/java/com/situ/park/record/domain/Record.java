/**
 * @Company:中享思途   
 * @Title:Record.java 
 * @Author:Administrator   
 * @Date:2020年2月27日 下午10:31:34     
 */
package com.situ.park.record.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.situ.park.base.domain.BaseClass;

/**
 * @ClassName:Record
 * @Description:(停车记录类)
 */
@Alias("Record")
public class Record extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;// 停车人的主键
	private Long carId;// 车辆的主键
	private String stallCode;// 车位号
	private String carNum;// 车牌号
	private Date parkTime;// 停入时间
	private Date takeTime;// 取车时间
	private Integer shouldPay;// 应支付费用
	private Integer ifPay;// 是否支付1:支付；0：未支付

	public Long getUserId() {
		return userId;
	}

	public Long getCarId() {
		return carId;
	}

	public String getStallCode() {
		return stallCode;
	}

	public String getCarNum() {
		return carNum;
	}

	public Date getParkTime() {
		return parkTime;
	}

	public Date getTakeTime() {
		return takeTime;
	}

	public Integer getShouldPay() {
		return shouldPay;
	}

	public Integer getIfPay() {
		return ifPay;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public void setStallCode(String stallCode) {
		this.stallCode = stallCode;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public void setParkTime(Date parkTime) {
		this.parkTime = parkTime;
	}

	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}

	public void setShouldPay(Integer shouldPay) {
		this.shouldPay = shouldPay;
	}

	public void setIfPay(Integer ifPay) {
		this.ifPay = ifPay;
	}

	@Override
	public String toString() {
		return "Record [userId=" + userId + ", carId=" + carId + ", stallCode=" + stallCode + ", carNum=" + carNum
				+ ", parkTime=" + parkTime + ", takeTime=" + takeTime + ", shouldPay=" + shouldPay + ", ifPay=" + ifPay
				+ "]";
	}

}
