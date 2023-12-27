/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.karinevilhena.main.entities.estrutura.UsuarioFlgMensagens;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MAURO PORTO
 */
@Repository
public interface UsuarioFlgMensagensRepository extends JpaRepository<UsuarioFlgMensagens, Long> { 


    
}
