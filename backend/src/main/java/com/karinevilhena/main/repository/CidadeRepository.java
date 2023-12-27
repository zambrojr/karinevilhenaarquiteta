/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository;
import com.karinevilhena.main.entities.Cidade;
import com.karinevilhena.main.entities.Uf;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author MAURO PORTO
 */
public interface CidadeRepository extends JpaRepository<Cidade, Long> { 
    
    
    @Query("select c FROM Cidade c where c.uf.id_uf = ?1")
    List<Cidade> findAllCidadesFromUf(Long uf);         
    
    
}
