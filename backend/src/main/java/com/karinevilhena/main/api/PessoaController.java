/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.api;

import com.karinevilhena.main.entities.Pessoa;
import com.karinevilhena.main.entities.estrutura.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.karinevilhena.main.service.PessoaService;

//import com.tgf.main.entities.Lote;

/**
 *
 * @author MAURO PORTO
 */
@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;
    
    @GetMapping("/getlistapessoa")
    List<Pessoa> getlistapessoa() {
      return pessoaService.getlistapessoa();
    }
    
    @GetMapping("/getCorretor")
    Pessoa getCorretor(Long id_corretor) {
      return pessoaService.getCorretor(id_corretor);
    }
    
    
    @GetMapping("/get")
    Pessoa get(Long id_pessoa) {
      return pessoaService.getPessoa(id_pessoa);
    }    

    
    @PostMapping("/save")
    Pessoa savePessoa(@RequestBody Pessoa pessoa) {
        //Long id_reserva = json.get("id_reserva").asLong();
        Pessoa retorno = pessoaService.savePessoa(pessoa);
        
        
        Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setPessoa(retorno);

        
        return retorno;
    }      
    
    
}
