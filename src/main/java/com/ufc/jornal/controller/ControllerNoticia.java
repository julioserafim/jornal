package com.ufc.jornal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.ufc.jornal.dao.ComentarioDao;
import com.ufc.jornal.dao.NoticiaDao;
import com.ufc.jornal.dao.SecaoDao;
import com.ufc.jornal.dao.UsuarioDao;
import com.ufc.jornal.model.Comentario;
import com.ufc.jornal.model.Noticia;
import com.ufc.jornal.model.Secao;
import com.ufc.jornal.model.Usuario;
import com.ufc.util.AulaFileUtil;

@Transactional
@Controller
public class ControllerNoticia {
	
	@Autowired
	private SecaoDao secaoDAO;
	
	@Autowired
	private NoticiaDao noticiaDAO;
	
	@Autowired
	private UsuarioDao usuarioDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ComentarioDao comentarioDAO;
	
	@RequestMapping("/inserirNoticiaFormulario")
	public ModelAndView inserirUsuarioFormulario() {
		ModelAndView mv = new ModelAndView("CadastrarNoticia");
		mv.addObject("todasSecoes", secaoDAO.findAll());
		mv.addObject(new Noticia());
		return mv;
	}
	
	@RequestMapping("/inserirNoticia")
	public String inserirNoticia(@Validated Noticia noticia, Errors errors, RedirectAttributes attributes,
			@RequestParam(value = "secao") Long codigoSecao,
			@RequestParam(value = "imagem", required = false) MultipartFile imagem, HttpSession session){
				
		noticia.setSecao(secaoDAO.findOne(codigoSecao));
		Usuario jornalista = (Usuario) session.getAttribute("jornalistaLogado");
		noticia.setUsuario(jornalista);
		String path = servletContext.getRealPath("/") + "static/images/" + noticia.getTitulo() + ".png";
		AulaFileUtil.salvarImagem(path, imagem);
		
		if(errors.hasErrors()){
			
			return "CadastrarNoticia";
		}
		
		
		noticiaDAO.save(noticia);
		attributes.addFlashAttribute("mensagem", "Noticia cadastrada com sucesso");
		
		return "redirect:/inserirNoticiaFormulario";
		
	}
	
	@RequestMapping("/listarNoticias")
	public ModelAndView listarNoticias(@Valid Noticia noticia, BindingResult result){
		List<Noticia> todasNoticias = noticiaDAO.findAll();
		ModelAndView mv = new ModelAndView("ListarNoticias");
		mv.addObject("noticias", todasNoticias);
		return mv;
		
	}
	
	@ModelAttribute("todasSecoes")
	public List<Secao> todasSecoes(){
		return secaoDAO.findAll();
	}
	
	@RequestMapping("/verNoticiaDaSecao")
	public ModelAndView verNoticiaDaSecao(Long codigo,HttpServletRequest request,HttpSession session){
		List<Comentario> comentariosDessaNoticia = new ArrayList<Comentario>();
		List<Comentario> todosComentarios = comentarioDAO.findAll();
		
		for(Comentario comentario: todosComentarios){
			if(comentario.getNoticia().getCodigo() == codigo){
				comentariosDessaNoticia.add(comentario);
			}
		}
		ModelAndView mv = new ModelAndView("NoticiaDaSecao");
		Noticia noticia = noticiaDAO.findOne(codigo);
		mv.addObject("secao", noticia.getSecao());
		mv.addObject("jornalista", noticia.getUsuario().getCodigo());
		mv.addObject("noticia", noticia);
		mv.addObject("comentarios", comentariosDessaNoticia);
		mv.addObject("msgComentario",new Comentario());
		
		if(request.getSession().getAttribute("jornalistaLogado") != null){
			Usuario jornalista = (Usuario) session.getAttribute("jornalistaLogado"); // Verifica se existe jornalista para apagar noticia
			if(jornalista.getCodigo() == noticia.getUsuario().getCodigo()){
				mv.addObject("excluirNoticiaJornalista","jornalistaLogado");
			}	
			
		}
		
		
		
		if(request.getSession().getAttribute("leitorLogado") != null){ // Verifica se existe leitor logado
			System.out.println("EXISTE LEITOR LOGADO");
			mv.addObject("mensagem", "leitorLogado");
		}
		
		if(request.getSession().getAttribute("editorLogado") != null){ // Verifica se existe editor logado
			mv.addObject("excluirNoticia", "editorLogado");
		}

		return mv;
	}
	
	@RequestMapping("/apagarNoticia")
	public ModelAndView apagarNoticia(@RequestParam(value = "noticia") Long codigoNoticia){
		ModelAndView mv = new ModelAndView("NoticiaApagadaSucesso");
		noticiaDAO.delete(codigoNoticia);
		return mv;
	}
	
	
}
