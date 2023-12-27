/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.estrutura;
import com.karinevilhena.main.entities.estrutura.Menu;
import com.karinevilhena.main.entities.estrutura.Modulo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author MAURO PORTO
 */
public interface MenuRepository extends JpaRepository<Menu, Long> { 
    
    @Query("select distinct m from Menu m left join fetch m.children c where m.ativo=true and m.parent is null order by m.ordem  ")
    List<Menu> listaMenus();
}
