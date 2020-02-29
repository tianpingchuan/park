/**
 * @Company:中享思途   
 * @Title:CarServiceImpl.java 
 * @Author:Administrator   
 * @Date:2020年2月27日 下午11:20:29     
 */
package com.situ.park.car.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.park.car.dao.CarDao;
import com.situ.park.car.domain.Car;
import com.situ.park.car.service.CarService;
import com.situ.park.commons.LayResult;
import com.situ.park.stall.dao.StallDao;
import com.situ.park.stall.domain.Stall;
import com.situ.park.util.DAOUtils;

/**
 * @ClassName:CarServiceImpl
 * @Description:(车辆信息的逻辑层的实现类)
 */
@Service
public class CarServiceImpl implements Serializable, CarService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CarDao carDao;
	@Autowired
	private StallDao stallDao;

	/**
	 * @Title: saveCar
	 * @Description:(保存车辆信息)
	 * @param car
	 * @return
	 */
	@Override
	public Long saveCar(Car car) {
//		将对应的车位号设置成有车停入状态
		Stall stall = new Stall();
		stall.setStallCode(car.getStallCode());
		stall.setStallState(1);
		stallDao.updateByCode(stall);
//		保存车辆信息
		car.setParkTime(new Date());
		car.setIfTakes(0);
		car.setActiveFlag(1);
		car.setCreateBy("SYS");
		car.setCreateDate(new Date());
		carDao.save(car);
		return car.getRowId();
	}

	/**
	 * @Title: findCarByPage
	 * @Description:(分页查询车辆信息)
	 * @param page
	 * @param limit
	 * @param searchCar
	 * @return
	 */
	@Override
	public LayResult findCarByPage(Integer page, Integer limit, Car searchCar) {
//		将是否取车赋值成未取车
		searchCar.setIfTakes(0);
		// 通过反射机制将类的实例中的""重新赋值为null,方便MyBatis中多条件查询SQL语句的拼写
		Car searchParam = DAOUtils.buildSearchParam(searchCar);
		// 查询出符合条件的一共有多少条记录。
		Integer dataCount = carDao.getCount(searchParam);
		// 根据分页的请求信息查询出数量列表。
		List<Car> CarList = carDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		return new LayResult(0, "", dataCount, CarList);
	}

}
