/**
 * @Company:中享思途   
 * @Title:Car.java 
 * @Author:Administrator   
 * @Date:2020年2月27日 下午10:36:27     
 */
package com.situ.park.car.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.situ.park.base.domain.BaseClass;

/**
 * @ClassName:Car
 * @Description:(停车车辆类)
 */
@Alias("Car")
public class Car extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;// 用户Id
	private String userName;// 用户名称
	private String stallCode;// 车位号
	private String carNum;// 车牌号
	private Date parkTime;// 停车时间
	private Integer ifTakes;// 是否取车0:未取车；1：已取车

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
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

	public Integer getIfTakes() {
		return ifTakes;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void setIfTakes(Integer ifTakes) {
		this.ifTakes = ifTakes;
	}

	@Override
	public String toString() {
		return "Car [userId=" + userId + ", userName=" + userName + ", stallCode=" + stallCode + ", carNum=" + carNum
				+ ", parkTime=" + parkTime + ", ifTakes=" + ifTakes + "]";
	}

}
