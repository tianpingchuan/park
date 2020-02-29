/**
 * @Company:中享思途   
 * @Title:CarService.java 
 * @Author:Administrator   
 * @Date:2020年2月27日 下午11:19:21     
 */ 
package com.situ.park.car.service;

import com.situ.park.car.domain.Car;
import com.situ.park.commons.LayResult;

/** 
 * @ClassName:CarService 
 * @Description:(车辆信息的service层)  
 */
public interface CarService {

	/** 
	 * @Title: saveCar 
	 * @Description:(保存车辆信息)
	 * @param car
	 * @return  
	 */ 
	Long saveCar(Car car);

	/** 
	 * @Title: findCarByPage 
	 * @Description:(分页查询车辆信息)
	 * @param page
	 * @param limit
	 * @param searchCar
	 * @return  
	 */ 
	LayResult findCarByPage(Integer page, Integer limit, Car searchCar);

}
