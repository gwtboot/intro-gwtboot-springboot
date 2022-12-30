package com.company.crm.client;

import java.io.Serializable;

public class PersonDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String title;

	private String description;

	public PersonDto() {
	}

	public PersonDto(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
