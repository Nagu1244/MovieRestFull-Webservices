package com.learn.SpringSecurity;

import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled =true)
public class SpringSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePwd());
		
	}
	//Refer Java Techie tutorial
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//ADMIN:Nagu,Nagu1234@
		http.authorizeRequests().antMatchers("/movie/**","/admin/**","/director/**").authenticated().anyRequest().hasAnyRole("ADMIN").and().httpBasic();
		/*http.authorizeRequests().antMatchers("/movie/**","/movie/save").hasRole("ADMIN").and()
		.authorizeRequests().antMatchers("/movie/movieId/{id}").authenticated().anyRequest().permitAll()
		.and().httpBasic();*/
	}
	
	@Bean
	public BCryptPasswordEncoder encodePwd()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	//Refer java Techie for URL and Role Based Security.
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("Nagaraj").password("Nagu1234@").roles("USER").
		and().withUser("Nagu").password("Nagu1234@").roles("USER","ADMIN");
	}
       @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
    	http.authorizeRequests().antMatchers("/movie/{id}").hasRole("USER").and().
    	authorizeRequests().antMatchers("/movie/**").authenticated().anyRequest().hasRole("ADMIN")
    	.and().formLogin();
    } */
    /* @Bean
      public static NoOpPasswordEncoder encoder()
      {
    	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
      } */
	
}
