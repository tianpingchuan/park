/**
 * @Company:中享思途   
 * @Title:RecordService.java 
 * @Author:Administrator   
 * @Date:2020年2月28日 下午3:52:17     
 */ 
package com.situ.park.record.service;

import com.situ.park.commons.LayResult;
import com.situ.park.record.domain.Record;

/** 
 * @ClassName:RecordService 
 * @Description:(停车记录的Service层)  
 */
public interface RecordService {

	/** 
	 * @Title: saveRecord 
	 * @Description:(保存停车记录)
	 * @param record
	 * @return  
	 */ 
	Long saveRecord(Long rowId);

	/** 
	 * @Title: findRecordByPage 
	 * @Description:(查找停车记录)
	 * @param page
	 * @param limit
	 * @param searchRecord
	 * @return  
	 */ 
	LayResult findRecordByPage(Integer page, Integer limit, Record searchRecord);

	/** 
	 * @Title: doDeleteRecord 
	 * @Description:(执行支付功能)
	 * @param rowId
	 * @return  
	 */ 
	Long doDeleteRecord(Long rowId);

}
