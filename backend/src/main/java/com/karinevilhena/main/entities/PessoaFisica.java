/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author MAURO PORTO
 */
@Data
@Entity
@Table(name = "pessoa_fisica", schema = "estrutura")
@PrimaryKeyJoinColumn(name="id_pessoa")
public class PessoaFisica extends Pessoa {
    
    private String cpf;    
    private String nome;
    private String sobrenome;
    private String rg;
    private String genero;
    
    private Integer estado_civil;
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date  nascimento;    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="conjuge")        
    private PessoaFisica conjuge;       
    
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cod_ocupacao")        
    private Ocupacao ocupacao;       */
    
    public String getNomeSobrenome(){
        return this.nome.concat(" ").concat(this.sobrenome);
    }
    
}
