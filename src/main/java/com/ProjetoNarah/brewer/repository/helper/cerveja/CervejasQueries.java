package com.ProjetoNarah.brewer.repository.helper.cerveja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ProjetoNarah.brewer.model.Cerveja;
import com.ProjetoNarah.brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {
	
	public Page<Cerveja> filtar(CervejaFilter filtro, Pageable pageable);
	
	
	
}
