/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.service;


import com.karinevilhena.main.entities.estrutura.Menu;
import com.karinevilhena.main.entities.estrutura.Modulo;
import com.karinevilhena.main.entities.estrutura.Resource;
import com.karinevilhena.main.entities.site.MenuTopo;
import com.karinevilhena.main.repository.estrutura.MenuRepository;
import com.karinevilhena.main.repository.estrutura.ModuloRepository;
import com.karinevilhena.main.repository.estrutura.ResourceRepository;
import com.karinevilhena.main.repository.site.MenuTopoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author MAURO PORTO
 */
@Service
public class EstruturaService {
 
    @Autowired
    ModuloRepository moduloRepository;        
    
    @Autowired
    ResourceRepository resourceRepository;        
    
    @Autowired
    MenuRepository menuRepository;        

    @Autowired 
    MenuTopoRepository menuTopoRepository;           
    
    public Optional<Modulo> getModuloResources(Long idModulo, Long[] listaIdRoloes) {
        return moduloRepository.getModuloResources(idModulo, listaIdRoloes);
    }    
 
    public Optional<Modulo> getModulo(Long idModulo) {
        return moduloRepository.findById(idModulo);
    }      
    
    public List<Modulo> listaModuloResources() {
        return moduloRepository.listaModuloResources();
    }        
    public List<Modulo> listaModuloResources(Long[] listaIdRoloes) {
        return moduloRepository.listaModuloResources(listaIdRoloes);
    }    
    
    public List<Resource> getResources() {
        return resourceRepository.listaResources();
    }
    
    @Cacheable("menus")
    public List<Menu> listaMenus(){
        return menuRepository.listaMenus();
    }
    
    @Cacheable("menusTopo")
    public List<MenuTopo> listaMenusTopoAtivos(){
        return menuTopoRepository.listaMenusAtivos();
    }    
    

}
