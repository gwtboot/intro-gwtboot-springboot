package com.company.crm.shared;

import org.dominokit.jackson.annotation.JSONMapper;

@JSONMapper
public class ErrorDto {

	private String status;

	private String errorcode;

	private String message;

	private String detail;

	public String getMessage() {
		if (message == null || message.equals("")) {
			message = "Empty Message...";
		}
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
