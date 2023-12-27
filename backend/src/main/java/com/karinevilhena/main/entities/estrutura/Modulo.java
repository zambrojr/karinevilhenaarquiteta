/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.estrutura;



import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity 
@Table(name = "modulo", schema = "estrutura")
public class Modulo {
    
    private @Id 
    Long id;
    
    private String nome;
    private String link;
    private String descricao;
    private String ico;
    
    @OneToMany(mappedBy="modulo", fetch = FetchType.LAZY)
    private Collection<Resource> resource;          
    
    public Modulo(){}
    

}
