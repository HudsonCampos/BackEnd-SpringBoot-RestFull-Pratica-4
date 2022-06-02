package com.curso.poo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
@Data
@Table(name = "t_bicicleta")
public class Bicicleta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_bicicleta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBicicleta;
	
	@Column(name = "cor", nullable = false, length = 255)	
	private String cor;
	
	@Column(name = "modelo", nullable = false, length = 255)
	private String modelo;
	
	@Column(name = "andando", nullable = false)
	private Boolean andando;
	
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name = "data_alteracao")
	private LocalDateTime dataAlteracao;
	
	@PrePersist
	void prePersist() {
		setDataCadastro(LocalDateTime.now());
		setDataAlteracao(LocalDateTime.now());
		
	}
	
}
