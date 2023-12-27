/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.api;


import com.karinevilhena.main.entities.estrutura.Usuario;
import com.karinevilhena.main.entities.estrutura.Modulo;
import com.karinevilhena.main.repository.estrutura.ModuloRepository;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.karinevilhena.main.service.EstruturaService;


/**
 *
 * @author MAURO PORTO
 */
@RestController
@RequestMapping("/api/estrutura")
public class EstruturaController {

    @Autowired
    EstruturaService estruturaService;
    
    @Autowired
    ModuloRepository moduloRepository;         
    
    @PostMapping("/selectmodule")
    public Optional<Modulo> selecModulte(HttpServletRequest request, @RequestBody Modulo modulo) {    
        request.getSession().setAttribute("moduloAtivo", modulo.getId());   
        Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return estruturaService.getModuloResources(modulo.getId(), user.getListaIdRoloes());
    }    

    @GetMapping("/getmoduloatual")
    Optional<Modulo> getModuloAtual(HttpServletRequest request) {
      Long idModulo    =   (Long)request.getSession().getAttribute("moduloAtivo");
      
      if(idModulo == null) return null;
      else {
          Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          return estruturaService.getModuloResources(idModulo, user.getListaIdRoloes());
      }
    }     
  
    
    @GetMapping("/getmodulosResources")
    List<Modulo> getModulosResources() {
      Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return estruturaService.listaModuloResources(user.getListaIdRoloes());
    }      
    
    
}
