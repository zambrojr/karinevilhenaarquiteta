/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository.site;



import com.karinevilhena.main.entities.site.FaleConosco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PB003321
 */
@Repository
public interface FaleConoscoRepository extends JpaRepository<FaleConosco, Long> { 


}
