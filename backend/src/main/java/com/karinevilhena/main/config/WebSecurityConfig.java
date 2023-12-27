/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.karinevilhena.main.service.UsuarioService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UsuarioService usuarioService;    
    
    @Bean
    public PasswordEncoder passwordEncoder() {       
        BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
        return pass;
    }    
    
    
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;    
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {       
        auth
         .userDetailsService(usuarioService)
         .passwordEncoder(passwordEncoder());
    }        
    
    @Override
    public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/signin");
    }    

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationFilter();
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().cors().and().csrf().disable()
                
            .anonymous()
                .authorities("ROLE_ANONYMOUS")        
                .and()
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                .antMatchers("/appublic/**").permitAll()
                .antMatchers("/api/signin").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ;
            http
                .addFilterAfter(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
    
    	@Bean
	public RegistrationBean jwtAuthFilterRegister(JwtAuthenticationFilter filter) {
		FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>(filter);
		registrationBean.setEnabled(false);
                registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}
    
        
    
}