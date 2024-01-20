package com.fastfood.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.ImageDTO;

@Component
public class FileUploadUtil {

	public static List<String> saveFiles(MultipartFile[] files, HttpServletRequest request, long id) {
		ServletContext servletctx = request.getServletContext();
		String ctxFullPath = servletctx.getRealPath("\\");

		int numberOfPic = 1;
		String imgs = "/upload/product";
		List<String> result = new ArrayList<String>();
		for (MultipartFile multipartFile : files) {
			String currentFilePath = "";
			try {
				String fileName = "Product" + id + "-" + numberOfPic + ".png";
				File file = new File(ctxFullPath + SystemConstant.UPLOAD_PRODUCT_DIRECTORY, fileName);
				multipartFile.transferTo(file);
				currentFilePath += imgs;
				currentFilePath += "/" + fileName;
				result.add(currentFilePath);
			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
			numberOfPic++;

		}

		return result;
	}

	public static void removeExistingFiles(HttpServletRequest request, List<ImageDTO> listImage) {
		ServletContext servletctx = request.getServletContext();
		String ctxFullPath = servletctx.getRealPath("\\");
		
		for (ImageDTO image : listImage) {
			
			File file = new File(ctxFullPath + "\\WEB-INF\\" + replaceURL(image.getImageURL()));
			if (file.exists()) {
				if (file.delete()) {
					
				} else {
					
				}
			}else {
				
			}
		}
	}
	public static String replaceURL(String imageURL) {
		return imageURL.replace("/", "\\");
	}
	public static String replaceURL2(String imageURL) {
		return imageURL.replace("\\", "/");
	}

}
