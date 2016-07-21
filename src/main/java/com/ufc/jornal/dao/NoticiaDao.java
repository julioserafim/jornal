package com.ufc.jornal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.jornal.model.Noticia;

public interface NoticiaDao extends JpaRepository<Noticia, Long>{

}
