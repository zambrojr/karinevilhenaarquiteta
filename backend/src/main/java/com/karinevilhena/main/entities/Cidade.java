/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author MAURO PORTO
 */
@Data
@Entity 
@Table(name = "municipios")
public class Cidade {
 
    private @Id Long codigo_ibge;
    
    private String nome;
    private String latitude;
    private String longitude;    
    private Boolean capital;
    
    @ManyToOne
    @JoinColumn(name="id_uf")    
    private Uf uf;     
    
}
