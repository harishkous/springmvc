package com.ithejas.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
//Specifies which package to scan
@ComponentScan("com.ithejas.controller")
//Enables Spring's annotations
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter{

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		 UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		 resolver.setPrefix("/WEB-INF/jsp/");
		 resolver.setViewClass(JstlView.class);
		 resolver.setSuffix(".jsp");
		 return resolver;
	}
}
