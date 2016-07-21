package com.ufc.jornal.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufc.jornal.dao.ClassificadoDao;
import com.ufc.jornal.dao.UsuarioDao;
import com.ufc.jornal.model.Classificado;
import com.ufc.jornal.model.Usuario;

@Transactional
@Controller
public class ControllerClassificado {

	@Autowired
	private ClassificadoDao classificadoDAO;

	@Autowired
	private UsuarioDao usuarioDAO;

	@RequestMapping("/inserirClassificadoFormulario")
	public ModelAndView inserirClassificadoFormulario() {
		ModelAndView mv = new ModelAndView("CadastrarClassificado");
		mv.addObject(new Classificado());
		return mv;

	}

	@RequestMapping("/inserirClassificado")
	public String inserirClassificado(@Validated Classificado classificado, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return "CadastrarClassificado";
		}
		
		classificadoDAO.save(classificado);
		attributes.addFlashAttribute("mensagem", "Classificado Salvo com sucesso!");
		return "redirect:/inserirClassificadoFormulario";
	}

	@RequestMapping("/verClassificados")
	public ModelAndView mostrarClassificados(Classificado classificado,HttpSession session ) {
		
		
		ModelAndView mv = new ModelAndView("VisualizarClassificados");
		mv.addObject("classificados", classificadoDAO.findAll());
		if(session.getAttribute("leitorLogado") != null){
			mv.addObject("mensagem", "leitorLogado");
		}
		
		return mv;
	}
	
	
	@RequestMapping("/inserirOfertaFormulario")
	public ModelAndView inserirOfertaFormulario(Long codigo){
		ModelAndView mv = new ModelAndView("InserirOfertaFormulario");
		Classificado classificado = classificadoDAO.findOne(codigo);
		mv.addObject("classificado",classificado);
		return mv;
	}
	
	
	@RequestMapping("/inserirOferta")
	public String inserirOfertaClassificado(RedirectAttributes attributes,
			HttpSession session, @RequestParam(value = "classificado") Long codigoClassificado,
			@RequestParam(value = "oferta") Double oferta){
		Classificado classificado = classificadoDAO.findOne(codigoClassificado);
		
		Double maiorOferta;
		
		if(oferta == null){
			maiorOferta = classificado.getPrecoInicial();
		}
		
		else{
			maiorOferta = oferta;
		}
		
		Usuario leitor = (Usuario) session.getAttribute("leitorLogado");
		
		if(oferta < classificado.getPrecoInicial() || oferta < maiorOferta){
			System.out.println("SUHAUSUASHUSUHASHUAHUSAHUSA");
			System.out.println(oferta);
			System.out.println(classificado.getPrecoInicial());
			return "redirect:/verClassificados";
		}
		
		
		
		else{
			Date now = new Date();
			classificado.setAutorMelhorOferta(leitor);
			classificado.setMelhorOferta(oferta);	
			classificado.setDataOferta(now);
			maiorOferta = oferta;
			attributes.addFlashAttribute("mensagem", "Lance ofertado com sucesso");
			classificadoDAO.save(classificado);
			return "redirect:/verClassificados";
		}
	
		
		/*if(errors.hasErrors()){
			return "VizualizarClassificados";
		}*/
		
	}

}
