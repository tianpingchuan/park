/**
 * @Company:中享思途   
 * @Title:CarDao.java 
 * @Author:Administrator   
 * @Date:2020年2月27日 下午10:47:13     
 */ 
package com.situ.park.car.dao;

import org.springframework.stereotype.Repository;

import com.situ.park.base.dao.BaseDao;
import com.situ.park.car.domain.Car;

/** 
 * @ClassName:CarDao 
 * @Description:(车辆类的dao层)  
 */
@Repository
public interface CarDao extends BaseDao<Car> {

}
