package com.curso.poo.erros;


import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class ApiErros {

	private List<String> erros;
	
	public ApiErros(List<String> msg) {
		this.erros = msg;
	}
	
	public ApiErros(String msgs) {
		this.erros = Arrays.asList(msgs);
	}
}
