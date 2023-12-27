/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository;


import com.karinevilhena.main.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import com.karinevilhena.main.entities.estrutura.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MAURO PORTO
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { 

    Usuario findByEmail(String email);
    
    @Query("select u from Usuario u where u.pessoa.id_pessoa = ?1")
    Usuario getPessoa(Long id_pessoa);
    
}
