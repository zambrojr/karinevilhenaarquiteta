/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main;

import com.karinevilhena.main.repository.PessoaRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MAURO PORTO
 */
@Controller
@RequestMapping("/cadastro")
public class CadastroController  {
 
    
    @Autowired
    PessoaRepository pessoaRepository;      
    

    @RequestMapping("")
    @Secured("ROLE_ADMIN")   
    public ModelAndView mainCadastro(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
 
        ModelAndView model = new ModelAndView("intra");
        model.addObject("nameModule", "Cadastro");
        return model;
    }   

    

    @RequestMapping("/pessoa")
    @Secured("ROLE_ADMIN")   
    public ModelAndView cadastropessoa(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
 
        ModelAndView model = new ModelAndView("intra");        
        return model;
    }     
        
    
    @RequestMapping("/funcionario")
    @Secured("ROLE_ADMIN")   
    public ModelAndView cadastrofuncionario(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
 
        ModelAndView model = new ModelAndView("intra");        
        return model;
    }       
    
    @RequestMapping("/cartorio")
    @Secured("ROLE_ADMIN")   
    public ModelAndView cadastrocartorio(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
 
        ModelAndView model = new ModelAndView("intra");        
        return model;
    }       
        
    @RequestMapping("/clausulacontrato")
    @Secured("ROLE_ADMIN")   
    public ModelAndView clausulacontrato(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
 
        ModelAndView model = new ModelAndView("intra");        
        return model;
    }            
    
    
    
    public String toString() {
        return "fsd";
    }    
    
}
