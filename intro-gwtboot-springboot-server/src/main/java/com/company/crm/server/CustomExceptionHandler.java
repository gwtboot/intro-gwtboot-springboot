package com.company.crm.server;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.company.crm.shared.ErrorDto;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDto handleAuthorizationException(AccessDeniedException accessDeniedException) {
		// build a response body out of ex, and return that
		ErrorDto errorDto = new ErrorDto();
		errorDto.setDetail(accessDeniedException.getMessage());
		errorDto.setErrorcode("ERROR_NUMBER_2018");
		errorDto.setMessage("Error Authorization");
		errorDto.setStatus(HttpStatus.BAD_REQUEST.toString());

		return errorDto;
	}

}
