package com.situ.park.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @图片上传
 * @author Administrator
 *
 */
public class UploadUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 要写出的文件路径的头目录
	 */
//	public static final String TO_FILE = "assert/uploads/";
	public static final String TO_FILE = "store/filestore/";

	/**
	 * @需要传进一个request和一个CommonsMultipartFile类型的数据
	 * @param uploadFile
	 * @param request
	 * @return 得到一个封装好的文件地址
	 */
	public static String buildUpload(CommonsMultipartFile uploadFile,HttpServletRequest request) {

		CommonsMultipartFile userFile = uploadFile;
		String originalFilename = userFile.getOriginalFilename();

		// 文件的后缀
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		// 写出的文件地址#此路径应该写到数据库中
		String filePath = TO_FILE + Calendar.getInstance().getTimeInMillis() + suffix;
		// 项目的根路径
//		String realPath = request.getServletContext().getRealPath("/");
		String realPath = ConfigUtils.TO_BASE_FILE;
		//要写出的文件
		File toFile = new File(realPath + filePath);
		try {
			//通过 transferTo 直接将文件写出
			userFile.transferTo(toFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return filePath;
	}
}
