/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.service;

import com.karinevilhena.main.entities.Pessoa;
import com.karinevilhena.main.repository.PessoaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MAURO PORTO
 */
@Service
public class PessoaService {
        
    @Autowired
    PessoaRepository pessoaRepository;    
    
    public List<Pessoa> getlistapessoa() {
        return pessoaRepository.getlistapessoa();
    }

    public Pessoa getCorretor(Long id_corretor) {
        return pessoaRepository.getCorretor(id_corretor);
    }
    
    public Pessoa getPessoa(Long id_pessoa) {
        return pessoaRepository.getPessoa(id_pessoa);
    }    

    
    public Pessoa findPessoa(Long id_pessoa) {
        return pessoaRepository.getOne(id_pessoa);
    }     
    
    public Pessoa savePessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
        return pessoa;
    }

    public Pessoa getAutorByRefId(String ref_id) {
        return pessoaRepository.getAutorByRefId(ref_id);
    }
    
}
