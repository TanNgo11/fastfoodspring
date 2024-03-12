package com.fastfood.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IProductService;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private IProductService ProductService;

    @GetMapping("/productImages/{id}")
    public ResponseEntity<List<ResponseEntity<byte[]>>> getProductImage(@PathVariable long id, HttpServletRequest request) throws IOException {
    	
		ProductDTO productDTO = ProductService.findById(id);
		List<String> images = new ArrayList<>();
		productDTO.getListImage().forEach(t->images.add(replaceURL(t.getImageURL())));
		
		List<ResponseEntity<byte[]>> responses = new ArrayList<>();

        for (String fileName : images) {
            ResponseEntity<byte[]> responseEntity = serveFile(request, fileName);
            responses.add(responseEntity);
        }

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
    
    private ResponseEntity<byte[]> serveFile( HttpServletRequest request, String fileName) {
        try {
        	ServletContext servletctx = request.getServletContext();
    		String ctxFullPath = servletctx.getRealPath("\\");

            Path path = Paths.get(ctxFullPath + "\\WEB-INF\\" + fileName);
            byte[] fileContent = Files.readAllBytes(path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
	public static String replaceURL(String imageURL) {
		return imageURL.replace("/", "\\");
	}
}