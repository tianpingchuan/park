/**
 * @Company:中享思途   
 * @Title:CarController.java 
 * @Author:Administrator   
 * @Date:2020年2月27日 下午11:17:32     
 */ 
package com.situ.park.car.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.park.car.domain.Car;
import com.situ.park.car.service.CarService;
import com.situ.park.commons.LayResult;

/** 
 * @ClassName:CarController 
 * @Description:(汽车类的Controller层)  
 */
@RestController
@RequestMapping("/car")
public class CarController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_CAR_INDEX = "car/car_index";
	
	@Autowired
	private CarService carService;

	/**
	 * @Title: doAddCar
	 * @Description:(执行新增功能)
	 * @param Car
	 * @return
	 */
	@PostMapping
	public Long doAddCar(Car car) {
		return carService.saveCar(car);
	}
	
	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_CAR_INDEX);
		return modelAndView;
	}

	/**
	 * @Title: findCarByPage
	 * @Description:(根据分页查询数量)
	 * @param page       页号
	 * @param limit      每页显示的数据数量
	 * @param searchCar 查询的条件
	 * @return
	 */
	// http://localhost:8080/layoa/Car/1/10?CarKind=&CarName=
	// restful 匹配 http://localhost:8080/layoa/Car/1/10
	@GetMapping("/{page}/{limit}")
	public LayResult findCarByPage(@PathVariable Integer page, @PathVariable Integer limit, Car searchCar) {
		return carService.findCarByPage(page, limit, searchCar);
	}
}
