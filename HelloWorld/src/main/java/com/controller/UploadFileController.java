package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.business.FileUploadBO;

@Controller
public class UploadFileController {
	private final String SUCCESSFUL_UPLOAD = "File uploaded successfully!";
	private final String FAILED_UPLOAD = "File upload failed! Please try again...";
	@RequestMapping(value = "uploadPic", method = RequestMethod.POST)
	public ModelAndView uploadImage(@RequestParam MultipartFile file) throws Exception{
		ModelAndView returnedValue =  new ModelAndView("uploadImage");
		FileUploadBO fileUploadBO = null;
		String message = null;
		try{
			if(null != file){
				fileUploadBO = new FileUploadBO();
				if(fileUploadBO.storeImage(file)){
					returnedValue.addObject("message", SUCCESSFUL_UPLOAD);
				}else{
					returnedValue.addObject("message", FAILED_UPLOAD);
				}
			}
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return returnedValue;
	}
}
