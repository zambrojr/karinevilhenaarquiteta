/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities;

import com.karinevilhena.main.entities.estrutura.Usuario;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author MAURO PORTO
 * https://www.javaguides.net/2018/09/spring-boot-spring-mvc-role-based-spring-security-jpa-thymeleaf-mysql-tutorial.html
 */
@Entity
@Data
@Table(name = "roles", schema = "estrutura")
public class Role {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String descricao;    
    
    @ManyToMany(mappedBy = "roles")
    private List < Usuario > usuarios;
    
    
    public Role(){}
    
}
