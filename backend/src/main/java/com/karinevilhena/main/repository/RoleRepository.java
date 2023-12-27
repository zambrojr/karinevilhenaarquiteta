/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.karinevilhena.main.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MAURO PORTO
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> { 
  
    
    @Query("select r from Role r  where r.name = ?1")
    Role getRoleByName(String role);    
    
    
}
