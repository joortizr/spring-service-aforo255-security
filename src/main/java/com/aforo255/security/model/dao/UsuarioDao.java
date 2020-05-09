package com.aforo255.security.model.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.aforo255.security.model.entity.Usuario;

public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {
	
	public Usuario findByUsername(@Param("nombre") String username);

}
