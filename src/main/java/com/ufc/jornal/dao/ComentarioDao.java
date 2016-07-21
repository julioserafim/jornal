package com.ufc.jornal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.jornal.model.Comentario;

public interface ComentarioDao extends JpaRepository<Comentario, Long>{

}
