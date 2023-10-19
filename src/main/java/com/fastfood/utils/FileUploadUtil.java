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

	public static String saveFiles(MultipartFile[] files, HttpServletRequest request, long id) {
		ServletContext servletctx = request.getServletContext();
		String ctxFullPath = servletctx.getRealPath("\\");
		int numberOfPic = 1;
		String imgs = "";
		for (MultipartFile multipartFile : files) {
			try {
				String fileName = "Product" + id + "_" + numberOfPic + ".png";
				System.out.println(ctxFullPath + SystemConstant.UPLOAD_PRODUCT_DIRECTORY);
				File file = new File(ctxFullPath + SystemConstant.UPLOAD_PRODUCT_DIRECTORY, fileName);
				multipartFile.transferTo(file);
				imgs += fileName + "_";
			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
			numberOfPic++;

		}
		System.out.println("hehe");

		return imgs;
	}
}
