package com.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GalleryImage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3117648039575102338L;
	
	private long id;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
