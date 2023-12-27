/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.blog;
import com.karinevilhena.main.entities.blog.Categoria;
import com.karinevilhena.main.entities.blog.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author MAURO PORTO
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> { 
    

    @Query("select p from Categoria p "
            + "where p.ref_id = ?1")    
    Categoria getCategoriaByRefId(String ref_id);
        
    
    
}
