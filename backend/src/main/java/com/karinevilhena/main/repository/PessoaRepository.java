/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.karinevilhena.main.entities.Pessoa;
import com.karinevilhena.main.entities.blog.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MAURO PORTO
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> { 

    
    @Query("select p, e from Pessoa p LEFT join p.endereco e")
    List<Pessoa> getlistapessoa();      

    @Query("select p, e from Pessoa p LEFT join p.endereco e where p.id_pessoa = ?1")
    Pessoa getCorretor(Long id_corretor);

    //@Query("select p, e, o from Pessoa p LEFT join p.endereco e LEFT join p.ocupacao o LEFT join FETCH p.conjuge co where p.id_pessoa = ?1")
    @Query("select p, e from Pessoa p LEFT join p.endereco e LEFT join FETCH p.conjuge co where p.id_pessoa = ?1")
    Pessoa getPessoa(Long id_pessoa);

    @Query("select p, e from Pessoa p LEFT join p.endereco e where p.ref_id = ?1")
    Pessoa getAutorByRefId(String ref_id);
    
}
