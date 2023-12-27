/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.tasks;

import com.karinevilhena.main.service.EstruturaService;
import com.karinevilhena.main.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author mauro
 */
@Component
public class ScheduledTasks {
        
    @Autowired
    SiteService siteService;        
    
    @Autowired 
    EstruturaService estruturaService;        
    
    @CacheEvict(allEntries = true, cacheNames = { "sessoesAtivas","menus","menusTopo","destaquesSite","listaEspecialidades","listaProcessosTrabalho" })        
    @Scheduled(fixedDelay = 300000)
    //@Scheduled(fixedDelay = 30000)
    public void cacheEvict() 
    {        
        siteService.listaProcessosTrabalho();
        siteService.getSessoesSite();
        estruturaService.listaMenus(); 
        estruturaService.listaMenusTopoAtivos();         
        siteService.listaDestaquesAtivos();
        siteService.listaEspecialidades();        
        //System.out.println("Cache Limpo");
    }    
    
}
