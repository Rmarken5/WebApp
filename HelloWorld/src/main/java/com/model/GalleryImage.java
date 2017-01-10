package com.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class GalleryImage {

	private String name;
	private String directory;
	private Timestamp dateTaken;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getDateTaken() {
		return dateTaken;
	}
	public void setDateTaken(Timestamp dateTaken) {
		this.dateTaken = dateTaken;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	
	
	
}
