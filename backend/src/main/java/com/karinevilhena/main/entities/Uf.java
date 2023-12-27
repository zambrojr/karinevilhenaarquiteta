/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author MAURO PORTO
 */
@Data
@Entity 
public class Uf {
    
    private @Id Long id_uf;
    
    private String nome;
    private String sigla;
    
}
