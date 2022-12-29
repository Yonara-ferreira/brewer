package com.ProjetoNarah.brewer.repository.helper.estilo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ProjetoNarah.brewer.model.Estilo;
import com.ProjetoNarah.brewer.repository.filter.EstiloFilter;

public interface EstilosQueries {

	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable);
}
