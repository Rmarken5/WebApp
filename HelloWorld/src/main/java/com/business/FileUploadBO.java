package com.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dao.GalleryImageDAO;
import com.dao.GalleryImageDAOImpl;
import com.model.GalleryImage;
import com.service.GalleryImageService;
import com.service.GalleryImageServiceImpl;

public class FileUploadBO {

	Configuration cg = new Configuration().configure();
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cg.getProperties()).build();;
	SessionFactory factory = cg.buildSessionFactory(serviceRegistry);
	Session ssn = factory.openSession();
	Transaction tn = ssn.beginTransaction();

	public boolean storeImage(MultipartFile file) throws Exception {
		String fileName = null;
		GalleryImage fetchImage = null;
		GalleryImageService service = new GalleryImageServiceImpl();
		OutputStream fileOut = null;
		File targetFile = null;
		try {
			if (null != file) {
				fileName = file.getName();
				if (null != fileName && !"".equals(fileName)) {
					fetchImage = (GalleryImage) service.getPictureByName(fileName);
					if (null == fetchImage) {
						service.uploadImage(file);

					}
				}
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if (null != fileOut) {
				fileOut.close();
			}
		}
	}

	public File getFile(String fileName) throws Exception {
		File image = null;
		GalleryImageService service = new GalleryImageServiceImpl();
		GalleryImage imageObject = null;
		try {
			if (null != fileName && !"".equals(fileName)) {
				imageObject = service.getPictureByName(fileName);
				if (null != imageObject && null != imageObject.getDirectory() && null != imageObject.getName()) {
					image = new File(imageObject.getDirectory() + "\\" + imageObject.getName() + ".jpg");
					if (null != image && image.exists()) {
						return image;
					}
				}
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	public byte[] getFileContentsByName(String imageName) throws Exception {
		GalleryImageService service = null;
		GalleryImage image = null;
		try {
			if (null != imageName) {
				service = new GalleryImageServiceImpl();
				image = service.getPictureByName(imageName);
				return service.getImageByName(image);
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	public byte[] getBytesFromFile(File inputFile) throws Exception {

		try {
			if (null != inputFile) {
				return Files.readAllBytes(inputFile.toPath());
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	public long getNumOfPages(int imagesPerPage) throws Exception {
		long numberOfImages;
		long numPages = 0;
		GalleryImageService service = null;
		try {
			service = new GalleryImageServiceImpl();
			numberOfImages = service.getCountOfAllPictures();
			if (numberOfImages > 0) {
				numPages = numberOfImages / imagesPerPage;
			}
			return numPages;
		} catch (Exception e) {
			throw e;
		}
	}
}
