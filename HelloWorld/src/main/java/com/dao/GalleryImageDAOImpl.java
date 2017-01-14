package com.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.UserInfo;
import com.misc.QueryStrings;
import com.model.GalleryImage;
import com.model.MyUserInfo;

public class GalleryImageDAOImpl implements GalleryImageDAO {
	Configuration cg = new Configuration().configure();
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cg.getProperties()).build();;
	SessionFactory factory = cg.buildSessionFactory(serviceRegistry);
	Session ssn = factory.openSession();
	Transaction tn = ssn.beginTransaction();

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

	public void testConnectionForLinux() throws Exception {
		String host = "ryan@10.0.0.44";
		String user = null;
		JSch jsch = new JSch();
		UserInfo userInfo = null;
		String command = null;
		try {
			user = host.substring(0, host.indexOf('@') + 1);
			host = host.substring(host.indexOf('@') + 1);
			com.jcraft.jsch.Session session = jsch.getSession(user, host, 22);
			userInfo = new MyUserInfo();
			session.setUserInfo(userInfo);
			session.connect();

			command = "grep 'INFO' filepath";
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			channel.setInputStream(null);

			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();

			channel.connect();

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			throw e;
		}

	}
}
