package com.ithejas.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ithejas.service.LoginService;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private LoginService loginService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		auth.userDetailsService(loginService).passwordEncoder(encoder);
	}
	 @Override 
	protected void configure(HttpSecurity http) throws Exception {
		 
		 http.sessionManagement()
		 .invalidSessionUrl("/?time=0")
		 .maximumSessions(1)
		 .expiredUrl("/?time=1");
		 
		http.authorizeRequests()
				.antMatchers("/static/**")
				.permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/login.do")
				//.defaultSuccessUrl("/dashboard")
			//	.failureUrl("/login?err=1")
				.permitAll()
				.usernameParameter("username")
				.passwordParameter("password")
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/?logout=1")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.permitAll()
			.and()
				.httpBasic()
			.and()				
				.requiresChannel()
				.anyRequest()
				//.requires("any");
				.requiresSecure();
		
		http.exceptionHandling()
		.accessDeniedPage("/403");
		
		 
	 }
}
