package com.ufc.jornal;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class JornalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JornalApplication.class, args);
	}
	
	@Bean
	public 	LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
