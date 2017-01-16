package com.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.model.GalleryImage;

public interface GalleryImageService {
	public GalleryImage[] getAllPictures() throws Exception;
	public GalleryImage getPictureByName(String name) throws Exception;
	public boolean uploadImage(MultipartFile picutre) throws Exception;
	public List<File> getAllPictureDirectories() throws Exception;
	public InputStream getImageByName() throws Exception;
}
