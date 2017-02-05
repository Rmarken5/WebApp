package com.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.UserInfo;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.UserInfo;
import com.misc.QueryStrings;
import com.model.GalleryImage;
import com.model.MyUserInfo;
import com.util.ApplicationProperties;

public class GalleryImageDAOImpl implements GalleryImageDAO {
	Configuration cg = new Configuration().configure();
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cg.getProperties()).build();;
	SessionFactory factory = cg.buildSessionFactory(serviceRegistry);
	Session ssn = factory.openSession();
	Transaction tn = ssn.beginTransaction();
	@Autowired
	private Environment env;

	public GalleryImage[] getAllPictures() throws Exception {
		List<GalleryImage> results = null;
		GalleryImage[] asList = null;
		StringBuilder queryString = new StringBuilder(QueryStrings.GET_ALL_GALLERY_IMAGES);
		try {
			results = (ArrayList<GalleryImage>) ssn.createQuery(queryString.toString()).list();
			if (results != null && !results.isEmpty()) {
				asList = (GalleryImage[]) results.toArray();
				return asList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			tn.rollback();
		} finally {
			ssn.clear();
		}
		return null;
	}

	public List<File> getAllPictureDirectories() throws Exception {
		List<GalleryImage> gallery = null;
		List<File> galleryResults = new ArrayList<File>();
		Iterator<GalleryImage> iter = null;
		GalleryImage image = null;

		try {
			gallery = (List<GalleryImage>) ssn.createQuery(QueryStrings.GET_ALL_GALLERY_IMAGES).list();
			if (gallery != null && !gallery.isEmpty()) {
				iter = gallery.iterator();
				while (iter.hasNext()) {
					image = iter.next();
					if (image != null) {
						File temp = new File(image.getDirectory() + "\\" + image.getName());
						galleryResults.add(temp);
					}
				}
				return galleryResults;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public GalleryImage getPictureByName(String name) throws Exception {
		GalleryImage image = null;
		StringBuilder query = new StringBuilder(QueryStrings.GET_IMAGES_BY_NAME);
		try {
			image = (GalleryImage) ssn.createQuery(query.toString()).setParameter("name", name).uniqueResult();
			if (null != image) {
				return image;
			}
		} catch (Exception e) {
			throw e;
		}

		return null;

	}

	public boolean uploadImage(MultipartFile picture) throws Exception {
		File targetFile = null;
		OutputStream fileOut = null;
		GalleryImage fetchImage = null;
		String fileName = null;
		try {
			if (null != picture) {
				fileName = picture.getName();
				targetFile = new File(QueryStrings.STORAGE_DIRECTORY + fileName);
				fileOut = new FileOutputStream(targetFile);
				fileOut.write(picture.getBytes());
				fetchImage = new GalleryImage();
				fetchImage.setDateTaken(new Timestamp(new Date().getTime()));
				fetchImage.setDirectory(QueryStrings.STORAGE_DIRECTORY + fileName);
				fetchImage.setName(fileName);
				return true;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			fileOut.close();
		}
		return false;
	}

	public byte[] getImageByName(GalleryImage image) throws Exception {
		String host = null;
		String user = null;
		JSch jsch = new JSch();
		UserInfo userInfo = null;
		String dir = null;
		ChannelSftp channelSftp = null;
		InputStream inst = null;
		Channel channel = null;
		com.jcraft.jsch.Session session = null;
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		byte[] bytes = new byte[16384];
		int aByte = 0;

		try {
			// host = env.getProperty("ap.host");
			host = "pegasus@10.0.0.44";
			user = host.substring(0, host.indexOf('@'));
			host = host.substring(host.indexOf('@') + 1);
			dir = image.getDirectory();
			session = jsch.getSession(user, host, 22);
			userInfo = new MyUserInfo();
			session.setPassword("Zeppelin32!");
			// session.setUserInfo(userInfo);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(dir);
			System.out.println(channelSftp.pwd());
			System.out.println(channelSftp.ls(dir + "/" + image.getName() + ".jpg"));
			System.out.println(channelSftp.ls(channelSftp.pwd()));
			Vector<LsEntry> files = channelSftp.ls(image.getName() + ".jpg");
			System.out.println(String.format("Found %d files in %s Path", files.size(), dir));
			for (ChannelSftp.LsEntry file : files) {
				if (file.getAttrs().isDir()) {
					continue;
				} else {
					System.out.printf("Reading File %s", file.getFilename());
					inst = channelSftp.get(file.getFilename());
					break;
				}
			}
			while ((aByte = inst.read(bytes, 0, bytes.length)) != -1) {
				buffer.write(bytes, 0, aByte);
			}
			buffer.flush();
			return buffer.toByteArray();

		} catch (Exception e) {
			throw e;
		} finally {
			if (null != channel) {
				channel.disconnect();
			}
			if (null != session) {
				session.disconnect();
			}
		}

	}

	public List<String> getAllImageNames() throws Exception {

		try {
			@SuppressWarnings("unchecked")
			List<String> images = (List<String>) ssn.createQuery(QueryStrings.GET_ALL_IMAGE_NAMES).list();
			return images;
		} catch (Exception e) {
			throw e;
		}

	}

	@SuppressWarnings("unchecked")
	public List<String> getAllImageNamesByPagination(int numOfRows, int startingRow) throws Exception {
		try {
			Query query = ssn.createQuery(QueryStrings.GET_ALL_IMAGE_NAMES);
			query.setFirstResult(startingRow);
			query.setFetchSize(numOfRows);
			query.setMaxResults(numOfRows);
			
			List<String> images = (List<String>)query.list();
					
			return images;
		} catch (Exception e) {
			throw e;
		}
	}

	public Long getCountOfAllPictures() throws Exception {
		try {
			return (Long) ssn.createQuery(QueryStrings.GET_ALL_GALLERY_IMAGE_COUNT).uniqueResult();
		} catch (Exception e) {
			throw e;
		}
	}
}
