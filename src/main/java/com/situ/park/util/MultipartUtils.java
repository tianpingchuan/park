package com.situ.park.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Title:writeFile
 * @Description:(写出文件，返回文件的路径）
 * @author Administrator
 * @return 文件保存的路径
 *
 */
public class MultipartUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String writeFile(CommonsMultipartFile mulipartFile,String realPath) {
		if (mulipartFile != null) {
//			上传的文件名称
			String originalFilename = mulipartFile.getOriginalFilename();
//			文件的后缀
			String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
//			写出的文件地址#此路径应该写到数据库中
			String filePath = "assert/uploads/" + Calendar.getInstance().getTimeInMillis()+suffix;
//			要写出的文件
			File toFile = new File(realPath+filePath);
			try {
//				通过transferto直接将文件写出
				mulipartFile.transferTo(toFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			return filePath;
		}
		return null;
	}
}
