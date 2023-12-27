/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author MAURO PORTO
 */
@Data
@Entity 
@Table(name = "cbo_ocupacao")
public class Ocupacao {
 
    private @Id String codigo;
    
    private String titulo;
   
}
