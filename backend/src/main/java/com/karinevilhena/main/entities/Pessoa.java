/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity 
@Table(name = "pessoa", schema = "estrutura")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PessoaFisica.class, name = "F"),
    @JsonSubTypes.Type(value = PessoaJuridica.class, name = "J")
})
public class Pessoa {
    
    private @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pessoaSeq") 
    @SequenceGenerator(name="pessoaSeq",sequenceName="pessoa_id_pessoa_seq", schema = "estrutura", allocationSize=1)
    Long id_pessoa;    
  
    private String email;
    private String fone;
    private String fone_residencial;
    private String fone_comercial;    
    private String tipo;
    private String img_perfil;
    private String ref_id;
    
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    @JsonIgnoreProperties("pessoa")
    private Endereco endereco;    
    
    public String getNomeCompleto(){
        String nome = "";
        if(this instanceof PessoaFisica){
            nome = ((PessoaFisica) this).getNomeSobrenome();
        } else if(this instanceof PessoaJuridica){
            nome = ((PessoaJuridica) this).getRazao_social();
        }                  
        return nome;
    }
    
    
    
    
    
    public Pessoa(){}
    
}
