package com.ufc.jornal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufc.jornal.dao.ComentarioDao;
import com.ufc.jornal.dao.NoticiaDao;
import com.ufc.jornal.dao.SecaoDao;
import com.ufc.jornal.model.Comentario;
import com.ufc.jornal.model.Noticia;
import com.ufc.jornal.model.Secao;

@Transactional
@Controller
public class ControllerSecao {
	@Autowired
	private SecaoDao secaoDAO;
	
	@Autowired
	private NoticiaDao noticiaDAO;
	
	
	@RequestMapping("/inserirSecaoFormulario")
	public ModelAndView inserirSecaoFormulario(@Valid Secao secao, BindingResult result){
		ModelAndView mv = new ModelAndView("CadastrarSecao");
		mv.addObject(new Secao());
		return mv;
	}
	
	@RequestMapping("/inserirSecao")
	public String inserirSecao(@Valid Secao secao, Errors errors,RedirectAttributes attributes){
		if(errors.hasErrors()){
			return "CadastrarSecao";
		}
		secaoDAO.save(secao);
		attributes.addFlashAttribute("mensagem", "Seção Cadastrada com sucesso");
		return "redirect:/inserirSecaoFormulario";
		
	}
	
	@RequestMapping("/verSecoes")
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("VerSecoes");
		mv.addObject("secoes",secaoDAO.findAll());
		return mv;
	}
	
	@RequestMapping("/verManchetesDaSecao")
	public ModelAndView verManchetesDaSessao(Long codigo){
		List<Noticia> noticiasManchetes = new ArrayList<Noticia>();
		List<Noticia> todasNoticias = noticiaDAO.findAll();
	
		for(Noticia noticia: todasNoticias){
			if(noticia.getSecao().getCodigo() == codigo){
				noticiasManchetes.add(noticia);
			}
		}
		
		ModelAndView mv = new ModelAndView("ManchetesDaSecao");
		mv.addObject("noticias", noticiasManchetes);
		return mv;
	}
	
	
}
