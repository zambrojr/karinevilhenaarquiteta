/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.blog;

import java.util.List;
import lombok.Data;

/**
 *
 * @author mauro
 */
@Data
public class PostFilter {
    
 private int page;
 private int size;
 private String categoria;
 private String user;
 
    
}
