/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.site;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author PB003321
 */
@Data
@Entity
@Table(name = "sessoes_ativas_site", schema = "estrutura")
public class SessoesAtivasSite {
        
    private @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SessoesAtivasSiteSeq") 
    @SequenceGenerator(name="SessoesAtivasSiteSeq",sequenceName="sessoes_ativas_site_id_seq", schema = "estrutura", allocationSize=1)
    Long id;    
    
    private String titulo;
    private String resumo;
    private String codigo;
    private Boolean ativo;
    private Integer ordem;
    
}
