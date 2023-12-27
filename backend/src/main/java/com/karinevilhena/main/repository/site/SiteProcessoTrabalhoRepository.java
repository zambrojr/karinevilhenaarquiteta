/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.site;


import com.karinevilhena.main.entities.site.SiteDestaques;
import com.karinevilhena.main.entities.site.SiteProcessoTrabalho;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PB003321
 */
@Repository
public interface SiteProcessoTrabalhoRepository extends JpaRepository<SiteProcessoTrabalho, Long> { 

    @Query("select la from SiteProcessoTrabalho la order by la.ordem")
    public List<SiteProcessoTrabalho> listaProcessosTrabalho();
    

   


}
