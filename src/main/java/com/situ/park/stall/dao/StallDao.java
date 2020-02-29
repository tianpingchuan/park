package com.situ.park.stall.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.park.commons.Pagination;
import com.situ.park.stall.domain.Stall;

@Repository
public interface StallDao {

	Long save(Stall stall);

	List<Stall> findByPage(@Param("searchStall") Stall searchStall, @Param("firstResult") Integer firstResult,@Param("maxResults") Integer maxResults);

	Integer getCount(@Param("searchStall") Stall searchStall);
	
	Stall get(Long rowId);

	void update(Stall stall);

	void delete(Long rowId);
	
	List<Stall> findAll();

	Stall getByCode(String stallCode);

	List<Stall> findByPages(@Param("pagination") Pagination buildPagination,@Param("searchParam")  Stall searchParam);

	/** 
	 * @Title: updateByCode 
	 * @Description:(通过code修改车位状态)
	 * @param stall  
	 */ 
	void updateByCode(Stall stall);
	
}
