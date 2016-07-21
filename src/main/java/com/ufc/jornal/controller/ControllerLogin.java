package com.ufc.jornal.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.jornal.criptografia.Criptografia;
import com.ufc.jornal.dao.UsuarioDao;
import com.ufc.jornal.form.LoginForm;
import com.ufc.jornal.model.Usuario;

@Controller
public class ControllerLogin {

	@Autowired
	private UsuarioDao usuarioDAO;
	
	
	@RequestMapping("/realizarLogin")
	public String realizarLogin(LoginForm loginForm, HttpSession session) throws NoSuchAlgorithmException{
		
		Usuario usuario = usuarioDAO.findByLoginLike(loginForm.getLoginDigitado());
		String senhaCriptografada = Criptografia.convertPasswordToMD5(loginForm.getSenhaDigitada());
	
		if(usuario.getSenha().equals(senhaCriptografada)){ 
			
			if(usuario.getPapel().getCodigo() == 1){
				session.setAttribute("jornalistaLogado",usuario);
				return"redirect:/jornalistaHome";	
				
			}
			
			if(usuario.getPapel().getCodigo() == 2){
				session.setAttribute("leitorLogado",usuario);
				return"redirect:/verSecoes";
			}
			
			else{ // É editor
				session.setAttribute("editorLogado",usuario);
				return"redirect:/editorHome";	
			}
			
		}
		
		return"Home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return"redirect:/home";
	}
}
