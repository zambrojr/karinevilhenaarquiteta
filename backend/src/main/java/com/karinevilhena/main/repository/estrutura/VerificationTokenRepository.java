/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.estrutura;
import com.karinevilhena.main.entities.estrutura.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author MAURO PORTO
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> { 
    
    @Query("select v, u from VerificationToken v INNER join v.user u where u.verificado = false and v.token = ?1 ")
    VerificationToken findNotVerifiedByToken(String token);    
    
    @Query("select v from VerificationToken v INNER join v.user u where u.id = ?1 ")
    VerificationToken findByUser(Long idUser);      

}
