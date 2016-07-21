package com.ufc.jornal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufc.jornal.model.PessoaTESTE;

@Repository
public interface PessoaDao extends JpaRepository<PessoaTESTE,Long>{
	
}
