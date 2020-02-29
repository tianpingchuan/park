/**
 * @Company:中享思途   
 * @Title:Stall.java 
 * @Author:Administrator   
 * @Date:2020年1月14日 上午9:44:18     
 */
package com.situ.park.stall.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.park.base.domain.BaseClass;

/**
 * @ClassName:Stall
 * @Description:(车位信息表)
 */
@Alias("Stall")
public class Stall extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stallCode;// 车位号
	private Integer stallState;// 车位状态：1：有车；0：可用

	public String getStallCode() {
		return stallCode;
	}

	public Integer getStallState() {
		return stallState;
	}

	public void setStallCode(String stallCode) {
		this.stallCode = stallCode;
	}

	public void setStallState(Integer stallState) {
		this.stallState = stallState;
	}

	@Override
	public String toString() {
		return "Stall [stallCode=" + stallCode + ", stallState=" + stallState + "]";
	}

}
