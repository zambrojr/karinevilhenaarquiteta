/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.site;



import com.karinevilhena.main.entities.site.SiteEspecialidades;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PB003321
 */
@Repository
public interface SiteEspecialidadesRepository extends JpaRepository<SiteEspecialidades, Long> { 

    @Query("select la from SiteEspecialidades la")
    public List<SiteEspecialidades> listaEspecialidades();

    @Query("select la from SiteEspecialidades la where url = ?1")
    public SiteEspecialidades getEspecialidadeByUrl(String url);
    
    @Query("select la from SiteEspecialidades la where id = ?1")
    public SiteEspecialidades getEspecialidadeById(Long id);    

}
