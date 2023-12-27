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
@Table(name = "site_destaques", schema = "estrutura")
public class SiteDestaques {
    
    private @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SiteDestaquesSeq") 
    @SequenceGenerator(name="SiteDestaquesSeq",sequenceName="site_destaques_id_seq", schema = "estrutura", allocationSize=1)
    Long id;
    
    
    private Integer codigo;
    private String titulo;
    private String subtitulo;
    private String resumo;
    private String descricao;
    private String banner;
    private String url;
    private String video;
    private Boolean ativo;
    
    public SiteDestaques(){}
    
}
