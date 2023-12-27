/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.site;



import  com.karinevilhena.main.entities.Pessoa;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Data
@Entity 
@Table(name = "fale_conosco", schema = "estrutura")
public class FaleConosco {
    
    private @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FaleConoscoSeq") 
    @SequenceGenerator(name="FaleConoscoSeq",sequenceName="fale_conosco_id_seq", schema = "estrutura", allocationSize=1)
    Long id;
    
    private String nome;
    private String email;
    private String assunto;
    private String texto;
    private String telefone;
    
    @Temporal(TemporalType.DATE)
    public Date data_fale_conosco;
    
    @Temporal(TemporalType.DATE)
    public Date data_lido;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lido_por")
    private Pessoa lidoPor;
    
    public FaleConosco(){}
    
}
