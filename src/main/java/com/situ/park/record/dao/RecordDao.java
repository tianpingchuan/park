/**
 * @Company:中享思途   
 * @Title:RecordDao.java 
 * @Author:Administrator   
 * @Date:2020年2月28日 下午12:08:19     
 */ 
package com.situ.park.record.dao;

import org.springframework.stereotype.Repository;

import com.situ.park.base.dao.BaseDao;
import com.situ.park.record.domain.Record;

/** 
 * @ClassName:RecordDao 
 * @Description:(停车记录类的Dao层)  
 */
@Repository
public interface RecordDao extends BaseDao<Record> {

}
