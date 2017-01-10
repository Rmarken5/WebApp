package com.controller;


import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.GalleryImage;
import com.service.GalleryImageService;
import com.service.GalleryImageServiceImpl;


@Controller
@RequestMapping("ProcessImage")
public class ImageProcessor {
      
       @RequestMapping(value = "imByNames", method = RequestMethod.POST)
       public void getImageFromDb(@RequestParam("imageName") String imageName,
                     HttpServletResponse response, HttpServletRequest request) throws Exception{
             
    	    GalleryImageService service = new GalleryImageServiceImpl();
             
              if(StringUtils.isNotBlank(imageName)){
                     GalleryImage image = service.getPictureByName(imageName);
                     ((ServletResponse) request).setContentType("image/jpeg, image/jpg, image/png, image/gif");
                    // response.getOutputStream().write(image.getPic()) ;
                  response.getOutputStream().close();
              }
             
       
       }
}