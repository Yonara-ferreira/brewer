package com.ProjetoNarah.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoNarah.brewer.model.Usuario;
import com.ProjetoNarah.brewer.repository.helper.usuario.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>,  UsuariosQueries {
	
	public Optional<Usuario> findByEmailIgnoreCase(String email);
}
