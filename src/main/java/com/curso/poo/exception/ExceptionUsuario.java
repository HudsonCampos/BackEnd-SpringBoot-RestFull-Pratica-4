package com.curso.poo.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.curso.poo.erros.ApiErros;

@RestControllerAdvice
public class ExceptionUsuario {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErros exceptionSql(DataIntegrityViolationException sqlExce) {
		String msgErro = sqlExce.getMessage();
		
		return new ApiErros(msgErro);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.OK)
	public ApiErros exceptionMsg(ResponseStatusException ex) {
		String msgErro = ex.getMessage();
		
		return new ApiErros(msgErro);
	}
}
