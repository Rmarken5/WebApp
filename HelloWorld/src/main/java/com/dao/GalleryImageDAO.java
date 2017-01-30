package com.dao;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.model.GalleryImage;

public interface GalleryImageDAO {
	public GalleryImage[] getAllPictures() throws Exception;
	public GalleryImage getPictureByName(String name) throws Exception;
	public boolean uploadImage(MultipartFile picture) throws Exception;
	public List<File> getAllPictureDirectories() throws Exception;
	public byte[] getImageByName(GalleryImage image) throws Exception;
	public List<String> getAllImageNames() throws Exception;
	public List<String> getAllImageNamesByPagination(int numOfRows, int startingRow) throws Exception;
	public Long getCountOfAllPictures() throws Exception;
	
}
