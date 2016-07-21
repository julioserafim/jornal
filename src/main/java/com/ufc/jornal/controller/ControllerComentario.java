package com.ufc.jornal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufc.jornal.dao.ComentarioDao;
import com.ufc.jornal.dao.NoticiaDao;
import com.ufc.jornal.dao.UsuarioDao;
import com.ufc.jornal.model.Comentario;
import com.ufc.jornal.model.Usuario;

@Transactional
@Controller
public class ControllerComentario {
	@Autowired
	private UsuarioDao usuarioDAO;
	
	@Autowired
	private NoticiaDao noticiaDAO;
	
	@Autowired
	private ComentarioDao comentarioDAO;
	
	
	@RequestMapping("/inserirComentario")
	public String inserirComentario(@Validated Comentario comentario,Errors errors,
			@RequestParam(value = "noticia") Long codigoNoticia, HttpSession session,RedirectAttributes attributes){
		
		Usuario leitor = (Usuario) session.getAttribute("leitorLogado");
		comentario.setAutor(leitor);
		comentario.setNoticia(noticiaDAO.findOne(codigoNoticia));
		
		if(errors.hasErrors()){
			return "NoticiaDaSecao";
		}
		
		attributes.addFlashAttribute("mensagem", "Comentário feito com sucesso");
		comentarioDAO.save(comentario);
		return"redirect:/verSecoes";
	}
	
}
