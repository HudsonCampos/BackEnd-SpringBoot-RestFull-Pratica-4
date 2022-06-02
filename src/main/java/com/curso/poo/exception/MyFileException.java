package com.curso.poo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public MyFileException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MyFileException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
