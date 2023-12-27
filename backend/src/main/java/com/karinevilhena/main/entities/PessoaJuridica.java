/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author MAURO PORTO
 */
@Data
@Entity
@Table(name = "pessoa_juridica", schema = "estrutura")
@PrimaryKeyJoinColumn(name="id_pessoa")
public class PessoaJuridica extends Pessoa {
    
    private String cnpj;    
    
    private String razao_social;    
    private String nome_fantasia;    
    private String insc_estadual;    
    private String contato;    
    
}
