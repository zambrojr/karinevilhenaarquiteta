/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.components;

import com.karinevilhena.main.repository.site.MenuTopoRepository;
import com.karinevilhena.main.service.EstruturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author mauro
 */
@ControllerAdvice
public class LayoutControllerAdvice {

    
    @Autowired 
    EstruturaService estruturaService;    
    
 
    
    
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("listaMenus", estruturaService.listaMenus()); 
        model.addAttribute("listaMenusTopo", estruturaService.listaMenusTopoAtivos()); 
    }
    
    
    
}