package com.fastfood.utils;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;

@Component
public class FileUploadUtil {

	public static ApiResponse saveFiles(MultipartFile[] files, HttpServletRequest request, long id) {
		ServletContext servletctx = request.getServletContext();
		String ctxFullPath = servletctx.getRealPath("\\");
		int numberOfPic = 1;
		for (MultipartFile multipartFile : files) {
			try {
				String fileName = "Product" + id + "_" + numberOfPic+".png";
				System.out.println(ctxFullPath + SystemConstant.UPLOAD_PRODUCT_DIRECTORY);
				File file = new File(ctxFullPath + SystemConstant.UPLOAD_PRODUCT_DIRECTORY, fileName);
				multipartFile.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
				return new ApiResponse(Boolean.FALSE, MessageUtil.UPLOAD_SUCCESS, HttpStatus.NO_CONTENT);

			}
			numberOfPic++;

		}

		return new ApiResponse(Boolean.TRUE, MessageUtil.UPLOAD_FAILURE, HttpStatus.OK);
	}
}
