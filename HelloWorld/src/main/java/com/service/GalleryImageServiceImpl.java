package com.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dao.GalleryImageDAO;
import com.dao.GalleryImageDAOImpl;
import com.model.GalleryImage;

public class GalleryImageServiceImpl implements GalleryImageService {
	GalleryImageDAO galleryDao = new GalleryImageDAOImpl();
	
	public GalleryImage[] getAllPictures() throws Exception {
		return galleryDao.getAllPictures();
	}
	public GalleryImage getPictureByName(String name) throws Exception{
		return galleryDao.getPictureByName(name);
	}
	public boolean uploadImage(MultipartFile picture) throws Exception{
		return galleryDao.uploadImage(picture);
	}
	public List<File> getAllPictureDirectories() throws Exception {
		
		return galleryDao.getAllPictureDirectories();
	}
	public byte[] getImageByName(GalleryImage image) throws Exception {
		return galleryDao.getImageByName(image);
	}
	public List<String> getAllImageNames() throws Exception {
		return galleryDao.getAllImageNames();
	}
	public List<String> getAllImageNamesByPagination(int numOfRows, int startingRow) throws Exception{
		return galleryDao.getAllImageNamesByPagination(numOfRows, startingRow);
	}
	public Long getCountOfAllPictures() throws Exception{
		return galleryDao.getCountOfAllPictures();
	}
}
