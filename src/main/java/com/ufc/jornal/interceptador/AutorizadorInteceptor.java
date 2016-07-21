package com.ufc.jornal.interceptador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AutorizadorInteceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String uri = request.getRequestURI();
		if(uri.endsWith("home") || uri.endsWith("olaMundo") || uri.endsWith("realizarLogin") 
				|| uri.endsWith("inserirUsuarioFormulario") || uri.endsWith("verSecoes") 
				|| uri.endsWith("verClassificados") || uri.endsWith("verManchetesDaSecao")
				|| uri.endsWith("verNoticiaDaSecao") || uri.endsWith("inserirUsuario")){
			return true;
		}
		if(request.getSession().getAttribute("leitorLogado") != null){
			return true;
		}
		
		if(request.getSession().getAttribute("jornalistaLogado") != null){
			return true;
		}
		
		if(request.getSession().getAttribute("editorLogado") != null){
			return true;
		}
		
		response.sendRedirect("home");
		return false;
	}
	
}
