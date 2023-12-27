/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.estrutura;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author PC
 */
@Data
@Entity 
@Table(name = "verification_token", schema = "estrutura")
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;
 
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tokenUsu") 
    @SequenceGenerator(name="tokenUsu",sequenceName="usuariotoken_id_seq", schema = "estrutura", allocationSize=1)     
    private Long id;
     
    private String token;
   
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario user;
     
    private Date expiryDate;

    
    
    public VerificationToken(){}
    
    public VerificationToken(String token, Usuario user) {
        this.token=token;
        this.setUser(user);
        this.setExpiryDate(this.calculateExpiryDate(24));
    }
    
    private Date calculateExpiryDate(int expiryTimeInHours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.HOUR, expiryTimeInHours);
        return new Date(cal.getTime().getTime());
    }
     
    // standard constructors, getters and setters
}
