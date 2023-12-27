/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.site;
import com.karinevilhena.main.entities.site.SessoesAtivasSite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author PB003321
 */
public interface SessoesAtivasSiteRepository extends JpaRepository<SessoesAtivasSite, Long> { 

    @Query("select la from SessoesAtivasSite la where la.ativo=true order by la.ordem")
    public List<SessoesAtivasSite> getSessoesSite();


}
