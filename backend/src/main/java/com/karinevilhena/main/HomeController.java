/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main;

import com.karinevilhena.main.service.EstruturaService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MAURO PORTO
 */
@Controller
@RequestMapping("/intra")
public class HomeController  {
     
    
    @RequestMapping("/")
    //@Secured("ROLE_ADMIN")   
    public ModelAndView indexpage(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("home");        
        return model;
    }      
    
    @RequestMapping("*")
    //@Secured("ROLE_ADMIN")   
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("home");        
        return model;
    }      
    
    @RequestMapping("/**")
    //@Secured("ROLE_ADMIN")   
    public ModelAndView childrens(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("home");        
        return model;
    }      
    
    
    
}