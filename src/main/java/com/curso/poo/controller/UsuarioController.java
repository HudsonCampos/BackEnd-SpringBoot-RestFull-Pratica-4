package com.curso.poo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.poo.model.vo.UsuarioVO;
import com.curso.poo.service.UsuarioService;

@RestController
@RequestMapping(path = "/api/usuario/v1")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping(value = "/created")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioVO created(@RequestBody UsuarioVO usuarioVO) {
		return usuarioService.created(usuarioVO);
	}

}
