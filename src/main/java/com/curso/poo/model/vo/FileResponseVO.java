package com.curso.poo.model.vo;

import lombok.Data;

@Data
public class FileResponseVO {

	private String fileName;
	private String fileDiretorio;
	private String fileType;
	private Long fileSize;
	
	public FileResponseVO(String fileName, String fileDiretorio, String fileType, Long fileSize) {
		super();
		this.fileName = fileName;
		this.fileDiretorio = fileDiretorio;
		this.fileType = fileType;
		this.fileSize = fileSize;
	}
	
	
}
