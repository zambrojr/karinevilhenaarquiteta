/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.estrutura;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author MAURO PORTO
 * https://www.javaguides.net/2018/09/spring-boot-spring-mvc-role-based-spring-security-jpa-thymeleaf-mysql-tutorial.html
 */
@Entity 
@Data
@Table(name = "usuarios_flag_mensagens", schema = "estrutura")
public class UsuarioFlgMensagens{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="flgmsgUsu") 
    @SequenceGenerator(name="flgmsgUsu",sequenceName="usuarioflgmsg_seq", schema = "estrutura", allocationSize=1)        
    private Long id;
    
    private boolean boas_vindas;
    
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;
}
