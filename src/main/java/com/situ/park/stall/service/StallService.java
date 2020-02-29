package com.situ.park.stall.service;

import java.util.List;

import com.situ.park.base.domain.PageData;
import com.situ.park.commons.LayResult;
import com.situ.park.stall.domain.Stall;

public interface StallService {

	Long StallSave(Stall stall, String createBy);
	
	PageData buildPageData(Integer pageNo, Stall searchStall);

	List<Stall> findByPage(Integer pageNo, Stall searchStall);
	
	Stall findOneById(Long rowId);

	Integer doUpdate(Stall stall, String createBy);

	Integer doDelete(Long rowId);
	
	List<Stall> findAll();

	String checkStallCode(String fieldId, String fieldValue);

	LayResult findStallByPage(Integer page, Integer limit, Stall searchStall);
	
}
