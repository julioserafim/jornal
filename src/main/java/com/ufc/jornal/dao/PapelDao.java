package com.ufc.jornal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.jornal.model.Papel;

public interface PapelDao extends JpaRepository<Papel, Long>{
	//public Papel recuperar(Papel papel);
	//public Papel recuperarPorId(Long id);
}
