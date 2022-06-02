package com.curso.poo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.curso.poo.model.Bicicleta;

public interface BicicletaRepository extends JpaRepository<Bicicleta, Integer>{	
	
	@Modifying
	@Query("UPDATE Bicicleta b SET b.andando=true WHERE b.idBicicleta=:id")
	void andarBicicleta(@Param("id") Integer id);
	
	@Query("SELECT b FROM Bicicleta b WHERE b.cor LIKE LOWER(CONCAT('%', :cor,'%'))")
	Page<Bicicleta> buscarBikePorCor(@Param("cor") String cor, Pageable pagina);
	
	
}
