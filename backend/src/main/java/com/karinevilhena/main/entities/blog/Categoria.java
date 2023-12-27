/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.blog;

import com.karinevilhena.main.entities.*;
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
@Table(schema = "blog")
public class Categoria {
    private @Id Integer id;
    private String nome;
    private String ref_id;
    
}
