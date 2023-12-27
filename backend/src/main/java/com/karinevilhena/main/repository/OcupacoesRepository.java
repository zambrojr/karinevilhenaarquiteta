/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository;


import com.karinevilhena.main.entities.Ocupacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MAURO PORTO
 */
@Repository
public interface OcupacoesRepository extends JpaRepository<Ocupacao, Long> { 


    

}
