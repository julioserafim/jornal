package com.ufc.jornal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ufc.jornal.interceptador.AutorizadorInteceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	@Autowired
	private AutorizadorInteceptor autorizadorInteceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(autorizadorInteceptor);
	}
}
