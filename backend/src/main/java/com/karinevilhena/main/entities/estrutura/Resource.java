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
@Table(name = "resources", schema = "estrutura")
public class Resource {
    
    private @Id 
    Long id;
    
    private String nome;
    private String menu;
    private String descricao;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id_modulo")
    private Modulo modulo;    
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="resource_pai")
    private Resource parent;
    
    @OneToMany(mappedBy="parent", fetch = FetchType.EAGER)
    private Collection<Resource> children;       
    
    
    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
       name="role_resource",
       schema = "estrutura",
       joinColumns={@JoinColumn(name="resource_id", referencedColumnName="id")},
       inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private List<Role> roles;        
    
    
    public Resource(){}
    
}
