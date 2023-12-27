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


@Data
@Entity 
@Table(name = "site_processo_trabalho", schema = "estrutura")
public class SiteProcessoTrabalho {
    
    private @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SiteProcessoTrabalhoSeq") 
    @SequenceGenerator(name="SiteProcessoTrabalhoSeq",sequenceName="site_processo_trabalho_id_seq", schema = "estrutura", allocationSize=1)
    Long id;
    
    
    private Integer ordem;
    private String titulo;
    private String resumo;
    private String descricao;
    private String icon;
    
    public SiteProcessoTrabalho(){}
    
}
