package com.ufc.jornal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.jornal.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long>,UsuarioDaoEnhaced{
}
