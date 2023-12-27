/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.site;


import com.karinevilhena.main.entities.site.MenuTopo;
import com.karinevilhena.main.entities.site.SiteDestaques;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PB003321
 */
@Repository
public interface MenuTopoRepository extends JpaRepository<MenuTopo, Long> { 
   
    @Query("select la from MenuTopo la where la.ativo=true order by la.ordem")
    public List<MenuTopo> listaMenusAtivos();    

    
    
    @Query("select la from MenuTopo la where ref_url = ?1")
    public MenuTopo getMenuTopoByUrl(String url);
    
}
