/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.site;


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
public interface SiteDestaquesRepository extends JpaRepository<SiteDestaques, Long> { 

    @Query("select la from SiteDestaques la")
    public List<SiteDestaques> listaDestaques();
    

    @Query(value = "SELECT max(codigo) FROM SiteDestaques")
    public Integer maxCodigo();    

    
    @Query("select la from SiteDestaques la where la.ativo=true")
    public List<SiteDestaques> listaDestaquesAtivos();    
    
    @Query("select la from SiteDestaques la where url = ?1")
    public SiteDestaques getDestaqueByUrl(String url);    

    @Query("select la from SiteDestaques la where id = ?1")
    public SiteDestaques getDestaqueById(Long id);

}
