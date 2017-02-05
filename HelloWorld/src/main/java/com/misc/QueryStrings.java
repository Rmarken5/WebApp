package com.misc;

public class QueryStrings {
	public final static String GET_USER_BY_USER_NAME = "SELECT U FROM "+ com.model.User.class.getName()+" U WHERE USER_NAME =:userName AND DELETE_SW = 'N' ";
	public final static String GET_USER_LOGIN = "SELECT U FROM "+ com.model.User.class.getName()+" U WHERE USER_NAME = :userName AND PASSWORD = :password AND DELETE_SW = 'N' " ;
	public final static String GET_USER_BY_EMAIL = "SELECT U FROM "+ com.model.User.class.getName()+" U WHERE EMAIL = :email AND DELETE_SW = 'N' " ;
	public final static String GET_ALL_GALLERY_IMAGES = "SELECT GI FROM " + com.model.GalleryImage.class.getName()+ " GI";
	public final static String GET_IMAGES_BY_NAME = "SELECT GI FROM " + com.model.GalleryImage.class.getName()+  " GI WHERE NAME = :name";
	public final static String GET_ALL_GALLERY_IMAGE_DIRECTORY = "SELECT GI.directory as directory FROM " + com.model.GalleryImage.class.getName()+ " GI";
	public final static String STORAGE_DIRECTORY = "C:/Users/Ryan/OneDrive/Valley Jam/pics/";
	public final static String GET_ALL_IMAGE_NAMES = "SELECT GI.name FROM "+ com.model.GalleryImage.class.getName() +" GI ORDER BY ID ASC";
	public final static String GET_ALL_GALLERY_IMAGE_COUNT = "SELECT COUNT(*) FROM " + com.model.GalleryImage.class.getName()+ " GI";

}
