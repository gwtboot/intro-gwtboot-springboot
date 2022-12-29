package com.company.crm.shared;

public enum PersonType {

	COOL("cool"), BORING("boring");

	private String type;

	private PersonType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
