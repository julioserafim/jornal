package com.ufc.jornal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.jornal.dao.PapelDao;
import com.ufc.jornal.model.Papel;

@Transactional
@Controller
public class ControllerPapel {
	
	@Autowired
	private PapelDao aDao;
	
	
	
	
	@RequestMapping("/inserirPapelFormulario") 
	public String inserirPapelFormulario() {
		return "CadastrarPapel";
	}
	
	
	@RequestMapping("/inserirPapel")
	public ModelAndView inserirPapel(@Valid Papel papel, BindingResult result){
		
		ModelAndView mv = new ModelAndView("CadastrarPapel");
		
		
		mv.addObject("mensagem", "Papel salvo com sucesso!");
		aDao.save(papel);
		return mv;
		
	}
}
