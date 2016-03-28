package com.ithejas.spring.config;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.mongodb.MongoClient;


@Configuration
//Specifies which package to scan
@ComponentScan("com.ithejas")
//Enables Spring's annotations
@EnableWebMvc
//@AnnotationDrivenConfig
//@ImportResource("classpath:security-applicationContext.xml")
@Import({ securityConfig.class })
@PropertySource("classpath:ApplicationVB.properties")
@EnableMongoRepositories
public class Config extends WebMvcConfigurerAdapter{
	 
	
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		 UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		 resolver.setPrefix("/WEB-INF/views/");
		 resolver.setViewClass(JstlView.class);
		 resolver.setSuffix(".jsp");
		 //resolver.setExposedContextBeanNames("appProperties");
		 return resolver;
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/styles/**").addResourceLocations("/WEB-INF/resources/css/");
	}
	
	@Bean
	public static PropertiesFactoryBean  appProperties() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("ApplicationVB.properties"));
		return propertiesFactoryBean;
		 
	}
	@Bean
	public static PropertyPlaceholderConfigurer properties(){
	   PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
	   Resource[] resources = new ClassPathResource[ ]
	      { new ClassPathResource( "ApplicationVB.properties" ) };
	   ppc.setLocations( resources );
	   ppc.setIgnoreUnresolvablePlaceholders( true );
	   return ppc;
	}
	 
	@Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
/*	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient("127.0.0.1"), "VB2015DB");
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
				
		return mongoTemplate;
		
	}*/
	 
	@Bean
	public Datastore datastore(){
		Morphia morphia = new Morphia();
		return morphia.createDatastore(new MongoClient(), "testDB");
	}
}
