package com.ProjetoNarah.brewer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoNarah.brewer.model.Cidade;

public interface Cidades extends  JpaRepository<Cidade, Long>{
	
	public List<Cidade> findByEstadoCodigo(long codigoEstado);

}
