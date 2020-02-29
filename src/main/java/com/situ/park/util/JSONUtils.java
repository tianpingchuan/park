package com.situ.park.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String buildValidationInfo(String fieldId, Boolean bool) {
		// [String,Boolean] ["roleName",true]
		// 通过使用ObjectMapper开始封装需要返回的校验结果
		ObjectMapper objectMapper = new ObjectMapper();
		List<Object> list = new ArrayList<Object>();
		list.add(fieldId);
		list.add(bool);
		try {
			// 将封装好的校验结果转换成json格式的字符串并响应回去
			return objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
