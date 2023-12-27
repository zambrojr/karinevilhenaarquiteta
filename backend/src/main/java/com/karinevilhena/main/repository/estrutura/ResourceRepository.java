/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.estrutura;
import com.karinevilhena.main.entities.estrutura.Resource;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author MAURO PORTO
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> { 

    @Query("select rs from Resource rs")
    List<Resource> listaResources();   
    
     
    
    
}
