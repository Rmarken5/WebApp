package com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.business.FileUploadBO;
import com.model.GalleryImage;
import com.service.GalleryImageService;
import com.service.GalleryImageServiceImpl;

import antlr.StringUtils;



@Controller
public class MainController {

	@Autowired 
	private ServletContext servletContext;
	private final int maxNumOfImages = 20;

	@RequestMapping(value="gallery/{pageNum}", method = RequestMethod.GET)
	public ModelAndView showMainPage(@PathVariable("pageNum") String pageNum){
		GalleryImageService imageSrvc = new GalleryImageServiceImpl();
		FileUploadBO fileBO = new FileUploadBO();
		ModelAndView mv = new ModelAndView();
		List<String> imageNames =null;
		int startingImage = 0;
		long maxPages = 0;
		try{
			if(null != pageNum && org.apache.commons.lang3.StringUtils.isNumeric(pageNum)){
				startingImage = maxNumOfImages * Integer.parseInt(pageNum);
			}else{
				startingImage = 0;
			}
			imageNames = imageSrvc.getAllImageNamesByPagination(maxNumOfImages, startingImage);
			maxPages = fileBO.getNumOfPages(maxNumOfImages);
			if(imageNames != null && imageNames.size( ) > 0){
				mv.addObject("galleryImages",imageNames);
				mv.addObject("pageNum", Integer.parseInt(pageNum));
				mv.addObject("maxPages", maxPages);
				System.out.println(imageNames.get(0));
			}

			mv.setViewName("main");

		}catch(Exception e){
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value = "gallery/img/{imageName}",method = RequestMethod.GET)
	public void retrieveImage(@PathVariable("imageName") String imageName, HttpServletResponse response ) throws Exception{
		FileUploadBO fileBO = new FileUploadBO();
		System.out.println(imageName);
		OutputStream os = null;
		InputStream in = null;
		byte[] imageContents = null;
		try{
			imageContents = fileBO.getFileContentsByName(imageName);
			//image = fileBO.getFile(imageName);
			if(null != imageContents && imageContents.length > 0){
				/*bytesFromFile = fileBO.getBytesFromFile(image);
				if(null != bytesFromFile && bytesFromFile.length > 0){*/
				if(null != response){
					response.setHeader("Content-Type", "image/jpeg");
					response.setHeader("Content-Length", String.valueOf(imageContents.length));
					response.setHeader("Content-Disposition", "inline; filename=\"" + imageName + "\"");

					//in = new BufferedInputStream(new FileInputStream(image));
					os = new BufferedOutputStream(response.getOutputStream());
					/*byte[] buffer = new byte[8192];
					for (int length = 0; (length = in.read(buffer)) > 0;) {
						os.write(buffer, 0, length);
					}*/
					os.write(imageContents);
				}
				//}
			}
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}finally{
			if(null != in){in.close();}
			if(null != os){os.close();}
		}
	}
}
