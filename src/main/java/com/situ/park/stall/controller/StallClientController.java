/**
 * @Company:中享思途   
 * @Title:StallClientController.java 
 * @Author:Administrator   
 * @Date:2020年2月27日 下午8:47:47     
 */ 
package com.situ.park.stall.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.park.commons.LayResult;
import com.situ.park.stall.domain.Stall;
import com.situ.park.stall.service.StallService;

/** 
 * @ClassName:StallClientController 
 * @Description:(客户页面看到的车位信息)  
 */
@RestController
@RequestMapping("/stallclient")
public class StallClientController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PAGE_STALL_INDEX = "stall/stall_client_index";
	private static final String PARK_INDEX = "stall/park_index";
	
	@Autowired
	private StallService stallService;
	
	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_STALL_INDEX);
		return modelAndView;
	}
	
	/**
	 * @Title: findRoleByPage
	 * @Description:(根据分页查询数量)
	 * @param page       页号
	 * @param limit      每页显示的数据数量
	 * @param searchRole 查询的条件
	 * @return
	 */
	// http://localhost:8080/layoa/role/1/10?roleKind=&roleName=
	// restful 匹配 http://localhost:8080/layoa/role/1/10
	@GetMapping("/{page}/{limit}")
	public LayResult findStallByPage(@PathVariable Integer page, @PathVariable Integer limit, Stall searchStall) {
		System.out.println(searchStall);
		return stallService.findStallByPage(page, limit, searchStall);
	}
	
	/**
	 * @Title: /form
	 * @Description:(进停车表单)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView goCar(ModelAndView modelAndView) {
		modelAndView.setViewName(PARK_INDEX);
		return modelAndView;
	}
}
