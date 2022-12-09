package com.ProjetoNarah.brewer.repository.helper.cerveja;

import java.util.List;

import com.ProjetoNarah.brewer.model.Cerveja;
import com.ProjetoNarah.brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {
	
	public List<Cerveja> filtar(CervejaFilter filtro);
	
}
