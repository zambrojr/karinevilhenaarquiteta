/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.blog;
import com.karinevilhena.main.entities.blog.Categoria;
import com.karinevilhena.main.entities.blog.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author MAURO PORTO
 */
public interface PostRepository extends JpaRepository<Post, Long> { 
    
    @Query("select distinct p from Post p "
            + "join FETCH p.autor a "
            + "order by p.id desc ")
    List<Post> getPosts();     

    @Query("select distinct p, a, c from Post p "
            + "join FETCH p.autor a "
            + "join FETCH p.categoria c "
            + "where p.refId = ?1")    
    Post findByRefId(String id);
    
}
