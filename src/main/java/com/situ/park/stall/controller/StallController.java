/**
 * @Company:中享思途   
 * @Title:StallController.java 
 * @Author:Administrator   
 * @Date:2020年1月14日 下午2:45:34     
 */ 
package com.situ.park.stall.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.situ.park.stall.domain.Stall;
import com.situ.park.stall.service.StallService;
import com.situ.park.util.ContextUtils;
import com.situ.park.util.PageUtils;

/** 
 * @ClassName:StallController 
 * @Description:(车位的controller层)  
 */
@Controller
@RequestMapping("/stall")
public class StallController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = Logger.getLogger(StallController.class);
	
	private static final String PAGE_INDEX_CART = "stall/stall_index";
	private static final String PAGE_LIST_CART = "stall/stall_list";

	@Autowired
	private StallService stallService;
	
	/**
	 * @进车位管理首页
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_INDEX_CART);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	/**
	 * @车位新增
	 * @param Cart
	 * @return
	 */
	public Long doAddCart(Stall stall) {
		String createBy = ContextUtils.getCreateBy();
		return stallService.StallSave(stall, createBy);
	}
	
	/**
	 * @查询所有的记录
	 * @param pageNo 分页的页号
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/find/{pageNo}")
	public ModelAndView findAllCart(@PathVariable Integer pageNo,Stall searchStall, ModelAndView modelAndView) {
		LOG.debug("这是页面提交的查询条件"+searchStall);		
//		解决将提交问题为''设置成null，解决mapper-xml中的查询不用写！=''问题
		searchStall = PageUtils.buildSearchParam(searchStall);
		// 要展示的列表数据
		modelAndView.addObject("stallList", stallService.findByPage(pageNo,searchStall));
		// 分页信息
		modelAndView.addObject("pageData", stallService.buildPageData(pageNo,searchStall));
		modelAndView.setViewName(PAGE_LIST_CART);
		return modelAndView;
	}
	
	/**
	 * @进修改
	 * @param rowId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/goupdate/{rowId}")
	public Stall goupdate(@PathVariable("rowId")Long rowId) {
		return stallService.findOneById(rowId);
	}
	
	@ResponseBody
	@RequestMapping("/doupdate")
	/**
	 * 执行用户修改
	 * @param role
	 * @return
	 */
	public Integer doUpdate(Stall stall) {
		String createBy = ContextUtils.getCreateBy();
		return stallService.doUpdate(stall,createBy);
	}
	
	/**
	 * 执行删除
	 * @param rowId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dodelete/{rowId}")
	public Integer doDelete(@PathVariable("rowId")Long rowId) {
		return stallService.doDelete(rowId);
	}
	
	/**
	 * @判断name是否被使用fieldId=roleName&fieldValue=%E5%BC%A0%E4%B8%89&_=1576485199991
	 */
	@ResponseBody
	@RequestMapping("/checkStallCode")
	public String checkStallCode(String fieldId,String fieldValue) {
		return stallService.checkStallCode(fieldId,fieldValue);
	}

}
