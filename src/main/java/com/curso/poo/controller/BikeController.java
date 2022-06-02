package com.curso.poo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.poo.model.vo.BicicletaVO;
import com.curso.poo.service.BikeService;

@RestController
@RequestMapping(path = "/api/bicicleta/v1")
public class BikeController {
	
	@Autowired
	BikeService bikeService;
	
	@PostMapping(value = "/created", consumes = {"application/json", "application/xml"}, 
			produces = {"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.CREATED)
	public BicicletaVO created(@RequestBody @Valid BicicletaVO bicicletaVO) {
	
		var entityVo = bikeService.created(bicicletaVO);
		return entityVo;
	}
	
	@GetMapping(value = "/buscarBicicletas/{id}", produces = {"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public BicicletaVO buscarBikeId(@PathVariable("id") Integer id) {
		var entity = bikeService.buscarBikeId(id);
		return entity.add(linkTo(methodOn(BikeController.class).buscarBikeId(id)).withSelfRel());
	}
	
	@PatchMapping(value = "/andarBike/{id}")
	@ResponseStatus(HttpStatus.OK)
	public BicicletaVO andarBike(@PathVariable("id") Integer id) {
		return bikeService.andarBike(id);
	}
	
	@GetMapping(value = "/paginaBikes")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<BicicletaVO>> buscarBikes(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
										@RequestParam(value = "limite", defaultValue = "3") Integer limite,
										@RequestParam(value = "direcao", defaultValue = "asc") String direcao){
		
		var sortDirecao = "DESC".equalsIgnoreCase(direcao)?Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(pagina, limite, Sort.by(sortDirecao, "idBicicleta"));
		
		Page<BicicletaVO> pageBikes = bikeService.buscarBikes(pageable);
		
		pageBikes
			.stream()
				.forEach(bike -> bike
						.add(linkTo(methodOn(BikeController.class).buscarBikeId(bike.getIdBicicleta())).withSelfRel()));
		
		
		return ResponseEntity.ok(pageBikes);	
		
	}
	
	@GetMapping(value = "/buscarBikesCor/{cor}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<BicicletaVO>> buscarBikePorCor(
				@PathVariable("cor") String cor,
				@RequestParam(value = "pagina", defaultValue = "0") Integer page,
				@RequestParam(value = "limit", defaultValue = "2") Integer limit,
				@RequestParam(value = "direcao", defaultValue = "asc") String direcao
			){
		
		var ordemDirecao = "DESC".equalsIgnoreCase(direcao)?Direction.DESC:Direction.ASC;
		
		Pageable paginacao = PageRequest.of(page, limit, Sort.by(ordemDirecao, "cor"));
		
		Page<BicicletaVO> pageBike = bikeService.buscarBikePorCor(cor, paginacao);
		
		pageBike
			.stream()
			.forEach(b -> b.add(linkTo(methodOn(BikeController.class).buscarBikeId(b.getIdBicicleta())).withSelfRel()));
		
		return ResponseEntity.ok(pageBike);
	}
	

}
