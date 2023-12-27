/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.estrutura;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.karinevilhena.main.entities.Pessoa;
import com.karinevilhena.main.entities.Role;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author MAURO PORTO
 * https://www.javaguides.net/2018/09/spring-boot-spring-mvc-role-based-spring-security-jpa-thymeleaf-mysql-tutorial.html
 */
@Entity 
@Data
@Table(name = "usuario", schema = "estrutura")
public class Usuario implements UserDetails {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lancUsu") 
    @SequenceGenerator(name="lancUsu",sequenceName="usuario_id_seq", schema = "estrutura", allocationSize=1)        
    private Long id;
    
    private String email;
    private String password;    
    private Boolean ativo;        
    private Boolean verificado;        
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date  datal_ult_acesso;
    
    private Long qtd_acesso;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_pessoa")        
    private Pessoa pessoa;         
    
    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
       name="usuario_role",
       schema = "estrutura",
       joinColumns={@JoinColumn(name="id_usuario", referencedColumnName="id")},
       inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private List<Role> roles;    
    
    
    @OneToOne(mappedBy = "usuario", cascade =  CascadeType.ALL)
    @JsonIgnoreProperties("usuario")
    private UsuarioFlgMensagens flgMsg;    
    
    
    public Usuario(){}
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] userRoles = this.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;        
    }

    public String getListaRoles() {
        String[] userRoles = this.getRoles().stream().map((role) -> role.getDescricao()).toArray(String[]::new);        
        return String.join(", ", userRoles);        
    }    
    
    
    public Long[] getListaIdRoloes() {
        Long[] userRoles = this.getRoles().stream().map((role) -> role.getId()).toArray(Long[]::new);        
        return userRoles;        
    }      
    
    
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return password;
    }    
    
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }
}
