/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.estrutura;



import com.karinevilhena.main.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity 
@Table(schema = "estrutura")
public class Menu {
    
    private @Id 
    Long id;
    
    private String nome;
    private String link;
    private String descricao;
    private Boolean ativo;
    private Integer ordem;
    
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="menu_pai")
    private Menu parent;
    
    @OneToMany(mappedBy="parent", fetch = FetchType.LAZY)
    private Collection<Menu> children;       
    
    

    
    
    public Menu(){}
    
}
