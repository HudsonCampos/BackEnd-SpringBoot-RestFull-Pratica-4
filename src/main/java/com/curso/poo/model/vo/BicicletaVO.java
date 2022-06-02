package com.curso.poo.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
@Data
public class BicicletaVO extends RepresentationModel<BicicletaVO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idBicicleta;	
	
	@NotEmpty(message = "{cor.vazio}")
	private String cor;
	
	private String modelo;
	
	private String foto;
	private Boolean andando;
	@JsonFormat(pattern =  "dd/MM/yyyy HH:mm")
	private LocalDateTime dataCadastro;	
	@JsonFormat(pattern =  "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAlteracao;
	
}
