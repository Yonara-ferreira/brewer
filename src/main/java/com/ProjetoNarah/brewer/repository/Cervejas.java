package com.ProjetoNarah.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoNarah.brewer.model.Cerveja;

@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long> {

		
		
}
