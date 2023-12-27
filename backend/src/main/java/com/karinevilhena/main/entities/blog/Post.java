/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.blog;

import com.karinevilhena.main.entities.*;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author MAURO PORTO
 */
@Data
@Entity 
@Table(schema = "blog")
public class Post {
    
    private @Id Integer id;
    private String titulo;
    private String conteudo;
    private String resumo;
    private String imagem;
    private Boolean publicado;
    
    private String refId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="autor_id")        
    private Pessoa autor;       
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoria_id")        
    private Categoria categoria;           
    
    @Temporal(TemporalType.DATE)
    public Date data;           
}
