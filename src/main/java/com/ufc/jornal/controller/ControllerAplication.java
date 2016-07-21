package com.ufc.jornal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.jornal.dao.UsuarioDao;
import com.ufc.jornal.model.Usuario;

@Transactional
@Controller
public class ControllerAplication {

	
	@RequestMapping("/home")
	public String mostrarHome(){
		return "Home";
	}
	
	@RequestMapping("/editorHome")
	public String mostrarHomeEditor(){
		return "EditorHome";
	}
	
	@RequestMapping("/jornalistaHome")
	public String mostrarHomeJornalista(){
		return "JornalistaHome";
	}
}
