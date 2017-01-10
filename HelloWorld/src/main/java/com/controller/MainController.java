package com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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



@Controller
public class MainController {

	@Autowired 
	private ServletContext servletContext;


	@RequestMapping(value="gallery", method = RequestMethod.GET)
	public ModelAndView showMainPage(){
		GalleryImageService imageSrvc = new GalleryImageServiceImpl();
		ModelAndView mv = new ModelAndView();
		GalleryImage[] galleryImageArray = null;
		List<File> directories =null;
		try{
			directories = imageSrvc.getAllPictureDirectories();
			if(directories != null && directories.size( ) > 0){
				mv.addObject("galleryImages",directories);

				System.out.println(directories.get(0).getAbsolutePath());
				directories.get(0).getName();
			}

			mv.setViewName("main");

		}catch(Exception e){
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value = "img/{imageName}",method = RequestMethod.GET)
	public void retrieveImage(@PathVariable("imageName") String imageName, HttpServletResponse response ) throws Exception{
		File image = null;
		byte[] bytesFromFile = null;
		FileUploadBO fileBO = new FileUploadBO();
		System.out.println(imageName);
		OutputStream os = null;
		InputStream in = null;
		try{

			image = fileBO.getFile(imageName);
			if(null != image){
				/*bytesFromFile = fileBO.getBytesFromFile(image);
				if(null != bytesFromFile && bytesFromFile.length > 0){*/
				if(null != response){
					response.setHeader("Content-Type", servletContext.getMimeType(image.getName()));
					response.setHeader("Content-Length", String.valueOf(image.length()));
					response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");

					in = new BufferedInputStream(new FileInputStream(image));
					os = new BufferedOutputStream(response.getOutputStream());
					byte[] buffer = new byte[8192];
					for (int length = 0; (length = in.read(buffer)) > 0;) {
						os.write(buffer, 0, length);
					}
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
