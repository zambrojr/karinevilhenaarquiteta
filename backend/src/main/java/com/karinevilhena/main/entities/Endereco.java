/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;


@Data
@Entity 
public class Endereco {
    
    
    private @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="enderecoSeq") 
    @SequenceGenerator(name="enderecoSeq",sequenceName="endereco_id_seq", allocationSize=1)
    Long id_endereco;    

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    //id_bairro numeric,
    private String cep;
    
    @ManyToOne
    @JoinColumn(name="codigo_ibge")    
    private Cidade cidade;          
    
    @OneToOne(mappedBy = "endereco")
    private Pessoa pessoa;    
    
    @JsonIgnore
    public String getEnderecoCompleto(){
        return this.getLogradouro().concat(", ").concat(this.getNumero()).concat(" ").concat(this.getComplemento());
    }
    
    Endereco(){}
    
}
