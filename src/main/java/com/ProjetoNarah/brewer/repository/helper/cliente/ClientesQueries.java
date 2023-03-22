package com.ProjetoNarah.brewer.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ProjetoNarah.brewer.model.Cliente;
import com.ProjetoNarah.brewer.repository.filter.ClienteFilter;

public interface ClientesQueries {
	
	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);

}
