/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity 
@Table(name = "log_atividade")
public class LogAtividade {
    
    private @Id @GeneratedValue Long id_log;
    private String titulo;
    private String descricao;
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date data;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_pessoa")        
    private Pessoa pessoa;       
        
    public LogAtividade(){}    
}
