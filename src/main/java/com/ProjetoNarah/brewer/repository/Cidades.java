package com.ProjetoNarah.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoNarah.brewer.model.Cidade;
import com.ProjetoNarah.brewer.model.Estado;
import com.ProjetoNarah.brewer.repository.helper.cidade.CidadesQueries;

public interface Cidades extends  JpaRepository<Cidade, Long>, CidadesQueries{
	
	public List<Cidade> findByEstadoCodigo(long codigoEstado);

	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);

}
