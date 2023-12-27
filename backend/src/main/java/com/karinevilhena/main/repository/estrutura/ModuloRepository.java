/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.estrutura;
import com.karinevilhena.main.entities.estrutura.Modulo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author MAURO PORTO
 */
public interface ModuloRepository extends JpaRepository<Modulo, Long> { 
    
    @Query("select distinct m from Modulo m join FETCH m.resource rs, IN (rs.roles) rol where m.id = ?1 and rs.parent is null and rol.id in (?2)")
    Optional<Modulo> getModuloResources(Long id, Long[] listaIdRoloes);
    
    @Query("select distinct m from Modulo m join FETCH m.resource rs where rs.parent is null ")
    List<Modulo> listaModuloResources();
         
    @Query("select distinct m from Modulo m join FETCH m.resource rs, IN (rs.roles) rol where rs.parent is null and rol.id in (?1) ")
    List<Modulo> listaModuloResources(Long[] listaIdRoloes);
}
