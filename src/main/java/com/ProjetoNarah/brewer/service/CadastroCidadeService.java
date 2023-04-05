package com.ProjetoNarah.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProjetoNarah.brewer.model.Cidade;
import com.ProjetoNarah.brewer.repository.Cidades;
import com.ProjetoNarah.brewer.service.exception.NomeCidadeJaCadastradaException;

@Service
public class CadastroCidadeService {

	@Autowired
	private Cidades cidades;
	
	@Transactional
	public void salvar(Cidade cidade) {
		Optional<Cidade> cidadeExistente = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		
		if(cidadeExistente.isPresent()) {
			throw new NomeCidadeJaCadastradaException("Nome da cidade ja cadastrada");
		}
		
		cidades.save(cidade);
	}
	
}
