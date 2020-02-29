/**
 * @Company:中享思途   
 * @Title:RecordController.java 
 * @Author:Administrator   
 * @Date:2020年2月28日 下午3:48:43     
 */ 
package com.situ.park.record.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.park.commons.LayResult;
import com.situ.park.record.domain.Record;
import com.situ.park.record.service.RecordService;

/** 
 * @ClassName:RecordController 
 * @Description:(停车记录的controller层)  
 */
@RestController
@RequestMapping("/record")
public class RecordController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_RECORD_INDEX = "record/record_index";

	@Autowired
	private RecordService recordService;
	
	/**
	 * @Title: doAddRecord
	 * @Description:(执行新增功能)
	 * @param Record
	 * @return
	 */
	@PostMapping("/{rowId}")
	public Long doAddRecord(@PathVariable Long rowId) {
		return recordService.saveRecord(rowId);
	}
	
	/**
	 * @Title: doDeleteRecord
	 * @Description:(执行支付功能)
	 * @param Record
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Long doDeleteRecord(@PathVariable Long rowId) {
		return recordService.doDeleteRecord(rowId);
	}
	
	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_RECORD_INDEX);
		return modelAndView;
	}

	/**
	 * @Title: findRecordByPage
	 * @Description:(根据分页查询数量)
	 * @param page       页号
	 * @param limit      每页显示的数据数量
	 * @param searchRecord 查询的条件
	 * @return
	 */
	// http://localhost:8080/layoa/Record/1/10?RecordKind=&RecordName=
	// restful 匹配 http://localhost:8080/layoa/Record/1/10
	@GetMapping("/{page}/{limit}")
	public LayResult findRecordByPage(@PathVariable Integer page, @PathVariable Integer limit, Record searchRecord) {
		return recordService.findRecordByPage(page, limit, searchRecord);
	}
}
