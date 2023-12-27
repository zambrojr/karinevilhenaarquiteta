/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.entities.estrutura;

import com.karinevilhena.main.entities.blog.*;
import java.util.List;
import lombok.Data;

/**
 *
 * @author mauro
 */
@Data
public class SiteContent {
    
 private String key;
 private String content;

    public SiteContent(String key, String content) {
        this.key = key;
        this.content = content;
    }
 
    
 
 
}
