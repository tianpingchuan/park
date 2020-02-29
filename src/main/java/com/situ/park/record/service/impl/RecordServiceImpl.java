/**
 * @Company:中享思途   
 * @Title:RecordServiceImpl.java 
 * @Author:Administrator   
 * @Date:2020年2月28日 下午3:53:52     
 */ 
package com.situ.park.record.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.park.car.dao.CarDao;
import com.situ.park.car.domain.Car;
import com.situ.park.commons.LayResult;
import com.situ.park.record.dao.RecordDao;
import com.situ.park.record.domain.Record;
import com.situ.park.record.service.RecordService;
import com.situ.park.stall.dao.StallDao;
import com.situ.park.stall.domain.Stall;
import com.situ.park.util.DAOUtils;

/** 
 * @ClassName:RecordServiceImpl 
 * @Description:(停车记录的Service层的实现层)  
 */
@Service
public class RecordServiceImpl implements Serializable, RecordService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RecordDao recordDao;
	@Autowired
	private CarDao carDao;
	@Autowired
	private StallDao stallDao;

	/** 
	 * @Title: saveRecord 
	 * @Description:(保存停车记录)
	 * @param record
	 * @return  
	 */
	@Override
	public Long saveRecord(Long rowId) {
		Car car = carDao.get(rowId);
		Record record = new Record();
		record.setUserId(car.getUserId());
		record.setStallCode(car.getStallCode());
		record.setCarNum(car.getCarNum());
		record.setCarId(car.getRowId());
		record.setParkTime(car.getParkTime());
		

		Date parkTime = record.getParkTime();

		long time1 = parkTime.getTime();
		Date takeTime = new Date();
		long time2 = takeTime.getTime();
		Long time = time2-time1;
		Long hours = time / (1000 * 60 * 60);
		Integer pay = Integer.parseInt(hours.toString());
		Long a = hours*1000*60*60;
//		判断超过整点的小时数+1
		if(a < time) {
			pay+=1;
		}
//		每小时收2元
		record.setShouldPay(pay*2);
		
		record.setIfPay(0);
		record.setTakeTime(takeTime);
		record.setActiveFlag(1);
		record.setCreateBy("sys");
		record.setCreateDate(new Date());
//		将相关停车的记录设置成已经取车
		car.setIfTakes(1);
		carDao.update(car);
		
		Stall stall = stallDao.getByCode(car.getStallCode());
		stall.setStallState(0);
//		将相关车位设置成可用
		stallDao.update(stall);
		
		recordDao.save(record);
		return 1L;
	}

	/** 
	 * @Title: findRecordByPage 
	 * @Description:(查找停车记录)
	 * @param page
	 * @param limit
	 * @param searchRecord
	 * @return  
	 */  
	@Override
	public LayResult findRecordByPage(Integer page, Integer limit, Record searchRecord) {
		// 通过反射机制将类的实例中的""重新赋值为null,方便MyBatis中多条件查询SQL语句的拼写
		Record searchParam = DAOUtils.buildSearchParam(searchRecord);
		// 查询出符合条件的一共有多少条记录。
		Integer dataCount = recordDao.getCount(searchParam);
		// 根据分页的请求信息查询出数量列表。
		List<Record> RecordList = recordDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		return new LayResult(0, "", dataCount, RecordList);
	}

	/** 
	 * @Title: doDeleteRecord 
	 * @Description:(执行支付功能)
	 * @param rowId
	 * @return  
	 */  
	@Override
	public Long doDeleteRecord(Long rowId) {
//		先查询出记录实例
		Record record = recordDao.get(rowId);
		record.setIfPay(1);
		recordDao.update(record);
		return 1L;
	}

}
