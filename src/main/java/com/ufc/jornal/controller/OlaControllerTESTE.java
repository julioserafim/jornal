package com.ufc.jornal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufc.jornal.dao.PessoaDao;
import com.ufc.jornal.model.PessoaTESTE;

@Transactional
@Controller
public class OlaControllerTESTE {
	@Autowired
	private PessoaDao aDao;
	
	@RequestMapping("/olaMundo")
	public String ola(){
		return "OlaMundoTESTE";
	}
	
	@RequestMapping("/inserirPessoa")
	public String inserirAluno(@Valid PessoaTESTE pessoa, BindingResult result) {
		

		aDao.save(pessoa);

		return "paginaModeloTESTE";
	}
}
