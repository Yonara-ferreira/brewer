package com.ProjetoNarah.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProjetoNarah.brewer.model.Cerveja;
import com.ProjetoNarah.brewer.repository.Cervejas;

@Service
public class CadastroCervejaService {
	
	@Autowired
	private Cervejas cervejas;
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejas.save(cerveja);
	}
	
}
