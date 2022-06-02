package com.curso.poo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
