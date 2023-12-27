package com.karinevilhena.main.api;


import com.karinevilhena.main.entities.Cidade;
import com.karinevilhena.main.entities.Ocupacao;
import com.karinevilhena.main.entities.Uf;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.karinevilhena.main.service.UtilsService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MAURO PORTO
 */
@RestController
@RequestMapping("/api/utils")
public class UtilsController {
    

    @Autowired
    UtilsService utilsService;

    
    @GetMapping("/loadUfs")
    List<Uf> loadUfs() {
      List<Uf> list = utilsService.findAllUfs();
      return list;
    } 
    
    
    @GetMapping("/loadCidades")
    List<Cidade> loadCidades(Long uf) {
      List<Cidade> list = utilsService.findAllCidadesFromUf(uf);
      return list;
    }     
    
    
    @GetMapping("/loadOcupacoes")
    List<Ocupacao> loadOcupacoes() {
      List<Ocupacao> list = utilsService.getAllOcupacoes();
      return list;
    }         
    
   
    
}
