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
@Table(name = "menu_topo", schema = "estrutura")
public class MenuTopo {
    
    private @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MenuTopoSeq") 
    @SequenceGenerator(name="MenuTopoSeq",sequenceName="menu_topo_id_seq", schema = "estrutura", allocationSize=1)
    Long id;
    
    
    private Integer ordem;    
    private String titulo;
    
    private String link;
    private String resumo;
    private String conteudo;
    private String ref_url;
    
    private Boolean ativo;
    private Boolean destaque;
    
    
    
    public MenuTopo(){}
    
}
