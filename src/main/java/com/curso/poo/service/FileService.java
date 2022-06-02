package com.curso.poo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.curso.poo.config.FileConfig;
import com.curso.poo.exception.FileException;
import com.curso.poo.exception.MyFileException;


@Service
public class FileService {

	private Path pathUri;
	
	public FileService(FileConfig fileConfig) {
		this.pathUri = Paths.get(fileConfig.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.pathUri);
		} catch (Exception e) {
			new FileException("Não foi possivel criar diretorio!",e);
		}
	}
	
	public String uploadFile(MultipartFile fileName) {
		String fileNameReturn = StringUtils.cleanPath(fileName.getOriginalFilename());
		
		try {
			if(fileNameReturn.contains("..")) {
				new MyFileException("Arquivo invalido!");
			}
			Path pathReturn = this.pathUri.resolve(fileNameReturn);
			Files.copy(fileName.getInputStream(), pathReturn, StandardCopyOption.REPLACE_EXISTING);
			
			return fileNameReturn;
			
		} catch (Exception e) {
			throw new FileException("Upload não realizado!", e);
		}
	}
	
	public Resource downloadFile(String fileName) {
		
		try {
			
			Path fileNameUri = this.pathUri.resolve(fileName).normalize();			
			Resource resource = new UrlResource(fileNameUri.toUri());
			
			if (resource.exists()) {
				return resource;
				
			}else {
				throw new FileException("O arquivo " + fileName + " não foi localizado!");
			}
			
		} catch (Exception e) {
			throw new MyFileException("Impossivel download!", e);
		}
		
	}
}
