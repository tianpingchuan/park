/**
 * @Company:中享思途   
 * @Title:StallServiceImpl.java 
 * @Author:Administrator   
 * @Date:2020年1月14日 上午10:19:47     
 */
package com.situ.park.stall.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.park.base.domain.PageData;
import com.situ.park.commons.LayResult;
import com.situ.park.stall.dao.StallDao;
import com.situ.park.stall.domain.Stall;
import com.situ.park.stall.service.StallService;
import com.situ.park.util.DAOUtils;
import com.situ.park.util.JSONUtils;
import com.situ.park.util.PageUtils;

/**
 * @ClassName:StallServiceImpl
 * @Description:(StallService类的实现类)
 */
@Service
public class StallServiceImpl implements Serializable, StallService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private StallDao stallDao;

	/**
	 * @Title: StallSave
	 * @Description:(保存车位实例)
	 * @param stall
	 * @return
	 */
	@Override
	public Long StallSave(Stall stall, String createBy) {
		stall.setStallState(0);
		stall.setActiveFlag(1);
		stall.setCreateBy(createBy);
		stall.setCreateDate(new Date());
		return stallDao.save(stall);
	}

	/**
	 * @Title: buildPageData
	 * @Description:(分页)
	 * @param pageNo
	 * @param searchStall
	 * @return
	 */
	@Override
	public PageData buildPageData(Integer pageNo, Stall searchStall) {
		// 查询出数据总数
		Integer dataCount = stallDao.getCount(searchStall);
		return PageUtils.buildPageData(dataCount, pageNo);
	}

	/**
	 * @Title: findByPage
	 * @Description:(通过分页查询数据)
	 * @param pageNo
	 * @param searchStall
	 * @return
	 */
	@Override
	public List<Stall> findByPage(Integer pageNo, Stall searchStall) {
		// limit查询数据开始的下标
		Integer firstResult = (pageNo - 1) * PageUtils.PAGE_ROWS;
		// limit查询数据 要显示的条数
		Integer maxResults = PageUtils.PAGE_ROWS;
		return stallDao.findByPage(searchStall, firstResult, maxResults);
	}

	/**
	 * @Title: findOneById
	 * @Description:(通过Id查询数据)
	 * @param rowId
	 * @return
	 */
	@Override
	public Stall findOneById(Long rowId) {
		return stallDao.get(rowId);
	}

	/**
	 * @Title: doUpdate
	 * @Description:(修改数据)
	 * @param stall
	 * @param createBy
	 * @return
	 */
	@Override
	public Integer doUpdate(Stall stall, String createBy) {
		stall.setUpdateBy(createBy);
		stall.setUpdateDate(new Date());
		stallDao.update(stall);
		return 1;
	}

	/**
	 * @Title: doDelete
	 * @Description:(删除数据)
	 * @param rowId
	 * @return
	 */
	@Override
	public Integer doDelete(Long rowId) {
		stallDao.delete(rowId);
		return 1;
	}

	/**
	 * @Title: findAll
	 * @Description:(查询全部实例)
	 * @return
	 */
	@Override
	public List<Stall> findAll() {
		return stallDao.findAll();
	}

	@Override
	public String checkStallCode(String fieldId, String fieldValue) {
		Stall stall = stallDao.getByCode(fieldValue);
		Boolean bool = stall != null ? false : true;
		return JSONUtils.buildValidationInfo(fieldId, bool);
	}

	@Override
	public LayResult findStallByPage(Integer page, Integer limit, Stall searchStall) {
		// 通过反射机制将类的实例中的""重新赋值为null,方便MyBatis中多条件查询SQL语句的拼写
		Stall searchParam = DAOUtils.buildSearchParam(searchStall);
		// 查询出符合条件的一共有多少条记录。
		Integer dataCount = stallDao.getCount(searchParam);
		// 根据分页的请求信息查询出数量列表。
		List<Stall> StallList = stallDao.findByPages(DAOUtils.buildPagination(page, limit), searchParam);
		return new LayResult(0, "", dataCount, StallList);
	}

}
