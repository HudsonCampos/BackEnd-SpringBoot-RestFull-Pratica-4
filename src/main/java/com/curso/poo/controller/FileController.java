package com.curso.poo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.poo.exception.MyFileException;
import com.curso.poo.model.vo.FileResponseVO;
import com.curso.poo.service.FileService;

@RestController
@RequestMapping(path = "/api/v1/file")
public class FileController {

	@Autowired
	FileService fileService;
	
	@PostMapping(value = "/uploadFile")
	@ResponseStatus(HttpStatus.CREATED)
	public FileResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileService.uploadFile(file);
		
		String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/v1/file/download/")
				.path(fileName)
				.toUriString();
		
		return new FileResponseVO(fileUri, fileName, file.getContentType(), file.getSize());
	}
	
	@PostMapping(value = "/uploadFiles")
	@ResponseStatus(HttpStatus.CREATED)
	public List<FileResponseVO> uploadFiles(@RequestParam("files") MultipartFile[] files){
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file))
				.collect(Collectors.toList());
	}
	
	
	@GetMapping(value = "/download/{fileName:.+}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request ){
		Resource resource = fileService.downloadFile(fileName);
		
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());				
		} catch (Exception e) {
			throw new MyFileException("Arquivo nao localizado", e);
		}
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + resource.getFilename() + "\"")
				.body(resource);	
		
	}
}
