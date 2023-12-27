/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.service;

import com.karinevilhena.main.entities.Cidade;
import com.karinevilhena.main.entities.Ocupacao;
import com.karinevilhena.main.entities.Uf;
import com.karinevilhena.main.repository.CidadeRepository;
import com.karinevilhena.main.repository.OcupacoesRepository;
import com.karinevilhena.main.repository.UfRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MAURO PORTO
 */
@Service
public class UtilsService {
    
    @Autowired
    UfRepository ufRepository;        
    
    @Autowired
    CidadeRepository cidadeRepository;      
    
    @Autowired
    OcupacoesRepository ocupacoesRepository;    
    
    public List<Uf> findAllUfs() {
        return ufRepository.findAll();
    }

    public List<Cidade> findAllCidadesFromUf(Long uf) {
        return cidadeRepository.findAllCidadesFromUf(uf);
    }


    public List<Ocupacao> getAllOcupacoes() {
        return ocupacoesRepository.findAll();
    }

}
