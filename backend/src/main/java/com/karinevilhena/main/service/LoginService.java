/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.service;

import com.karinevilhena.main.entities.Role;
import com.karinevilhena.main.entities.estrutura.Modulo;
import com.karinevilhena.main.entities.estrutura.Usuario;
import com.karinevilhena.main.repository.UsuarioRepository;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class LoginService implements ApplicationListener<AuthenticationSuccessEvent> { 
    
    @Autowired
    private UsuarioRepository userRepository;    

    @Autowired
    private HttpSession httpSession;    
    
    
    @Autowired
    UsuarioService usuarioService;        
    
    @Autowired
    EstruturaService estruturaService;          
    
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
           
           String userName = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
           Usuario user = userRepository.findByEmail(userName);
           user.setDatal_ult_acesso(new Date());
           user.setQtd_acesso(user.getQtd_acesso()+1);
           userRepository.save(user);
           
           Usuario userLogged = (Usuario) event.getAuthentication().getPrincipal();
           userLogged.setQtd_acesso(user.getQtd_acesso());         
           
            if(user.getRoles().get(0).getName().equals("ROLE_USER")){
                Role role_usuario = usuarioService.getRoleByName("ROLE_USER");
                Long[] listaIdRoloes = {role_usuario.getId()};
                List<Modulo> modulos = estruturaService.listaModuloResources(listaIdRoloes);
                httpSession.setAttribute("moduloAtivo", modulos.get(0).getId());
            }
           
    }    
    
}
