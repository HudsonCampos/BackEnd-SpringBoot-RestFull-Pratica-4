package com.curso.poo.service;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.curso.poo.config.DozerConverter;
import com.curso.poo.model.Bicicleta;
import com.curso.poo.model.vo.BicicletaVO;
import com.curso.poo.repository.BicicletaRepository;

@Service
public class BikeService{

	@Autowired
	BicicletaRepository bicicletaRepository;
	
	public BicicletaVO created(BicicletaVO bicicletaVO) {
		var entity = DozerConverter.parseObjetct(bicicletaVO, Bicicleta.class);		
		return DozerConverter.parseObjetct(bicicletaRepository.save(entity), BicicletaVO.class);
	}

	public BicicletaVO buscarBikeId(Integer id) {
		var entity = bicicletaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não localizado!"));
		return DozerConverter.parseObjetct(entity, BicicletaVO.class);
	}	
	
	@Transactional
	public BicicletaVO andarBike(Integer id) {		
		bicicletaRepository.andarBicicleta(id);
		var bike = bicicletaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id nã encontrado!"));	
		bike.setDataAlteracao(LocalDateTime.now());
		
		return DozerConverter.parseObjetct(bike, BicicletaVO.class);
		
		
	}

	public Page<BicicletaVO> buscarBikes(Pageable pageable) {
		var pageBikes = bicicletaRepository.findAll(pageable);
		return pageBikes.map(this::parsePageObject);		 
	}
	
	public Page<BicicletaVO> buscarBikePorCor(String cor, Pageable page){
		var pageObj = bicicletaRepository.buscarBikePorCor(cor, page);
		return pageObj.map(this::parsePageObject);
	}
	
	public BicicletaVO parsePageObject(Bicicleta entity) {
		return DozerConverter.parseObjetct(entity, BicicletaVO.class);
	}
	
	
	

}