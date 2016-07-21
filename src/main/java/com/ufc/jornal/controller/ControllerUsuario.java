package com.ufc.jornal.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufc.jornal.criptografia.Criptografia;
import com.ufc.jornal.dao.PapelDao;
import com.ufc.jornal.dao.UsuarioDao;
import com.ufc.jornal.model.Papel;
import com.ufc.jornal.model.Usuario;
import com.ufc.util.AulaFileUtil;

@Transactional
@Controller
public class ControllerUsuario {

	@Autowired
	private UsuarioDao usuarioDAO;

	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private PapelDao papelDAO;

	@RequestMapping("/inserirUsuarioFormulario")
	public ModelAndView inserirUsuarioFormulario() {
		ModelAndView mv = new ModelAndView("CadastrarUsuario");
		mv.addObject(new Usuario());
		return mv;
	}
	
	@RequestMapping("/inserirUsuarioJornalistaFormulario")
	public ModelAndView inserirUsuarioJornalistaFormulario() {
		ModelAndView mv = new ModelAndView("CadastrarUsuarioJornalista");
		mv.addObject(new Usuario());
		return mv;
	}

	@RequestMapping("/inserirUsuario")
	public String inserirUsuario(@Validated Usuario usuario, Errors errors,RedirectAttributes attributes,
			@RequestParam(value = "imagem", required = false) MultipartFile imagem) throws NoSuchAlgorithmException {


		if (errors.hasErrors()) {
			
			System.out.println("tem erro");
			return "CadastrarUsuario";
		}

		String path = servletContext.getRealPath("/") + "static/images/" + usuario.getEmail() + ".png";
		AulaFileUtil.salvarImagem(path, imagem);
		
		usuario.setSenha(Criptografia.convertPasswordToMD5(usuario.getSenha()));
		
		usuario.setPapel(papelDAO.findOne(3L));
		
		usuarioDAO.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		
		return "redirect:/inserirUsuarioFormulario";
	}
	
	
	
	@RequestMapping("/inserirUsuarioJornalista")
	public String inserirUsuarioJornalista(@Validated Usuario usuario, Errors errors,RedirectAttributes attributes,
			@RequestParam(value = "imagem", required = false) MultipartFile imagem) throws NoSuchAlgorithmException {


		if (errors.hasErrors()) {
			
			System.out.println("tem erro");
			return "CadastrarUsuarioJornalista";
		}

		String path = servletContext.getRealPath("/") + "static/images/" + usuario.getEmail() + ".png";
		AulaFileUtil.salvarImagem(path, imagem);
		
		usuario.setSenha(Criptografia.convertPasswordToMD5(usuario.getSenha()));
		
		usuario.setPapel(papelDAO.findOne(1L));
		
		usuarioDAO.save(usuario);
		attributes.addFlashAttribute("mensagem", "Jornalista salvo com sucesso");
		
		return "redirect:/inserirUsuarioJornalistaFormulario";
	}
	
	
}
