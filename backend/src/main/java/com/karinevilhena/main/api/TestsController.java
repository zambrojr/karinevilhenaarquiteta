/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.api;


import com.karinevilhena.main.entities.Pessoa;
import com.karinevilhena.main.entities.estrutura.Menu;
import com.karinevilhena.main.entities.estrutura.Usuario;
import com.karinevilhena.main.repository.estrutura.MenuRepository;
import com.karinevilhena.main.service.PessoaService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.karinevilhena.main.service.UsuarioService;


/**
 *
 * @author MAURO PORTO
 */
@RestController
@RequestMapping("/api/test")
public class TestsController {



    
    @Autowired
    UsuarioService usuarioService;       
    
    
    @Autowired
    PessoaService pessoaService;          
    
    
    @Autowired
    MenuRepository menuRepository;         
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */      
    @GetMapping("/helloWorld")
    String helloWorld() {
      return "helloWorld";
    }       

    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */      
    @GetMapping("/menu")
    List<Menu> menu() {
      return menuRepository.listaMenus();
    }
    
    
        
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */      
    @GetMapping("/lista")
    List<Pessoa> lista() {
      return pessoaService.getlistapessoa();
    }    
    
    
}
