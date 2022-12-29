package com.company.crm.shared;

import java.util.Date;
import java.util.logging.Logger;

import org.dominokit.jackson.annotation.JSONMapper;

import com.fasterxml.jackson.annotation.JsonFormat;

@JSONMapper
public class PersonDto {

	private static Logger logger = Logger.getLogger(PersonDto.class.getName());

	private String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PersonEndpoint.DATE_FORMAT)
	private Date date;

	private PersonType personType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PersonType getPersonType() {
		logger.info("getPersonType: " + personType);
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

}
