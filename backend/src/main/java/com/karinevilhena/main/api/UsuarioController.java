package com.karinevilhena.main.api;

import com.karinevilhena.main.entities.Role;
import com.karinevilhena.main.entities.estrutura.Usuario;
import com.karinevilhena.main.entities.estrutura.UsuarioFlgMensagens;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.karinevilhena.main.service.MailService;
import com.karinevilhena.main.service.UsuarioService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MAURO PORTO
 */
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;    
    
    @Autowired
    MailService mailService;        
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */  
    @GetMapping("/getUserLi")
    Usuario getUserLi() {
      Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return user;
    } 
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */      
    @GetMapping("/listaUsuarios")
    @Secured("ROLE_ADMIN")
    List<Usuario> listaUsuarios() {      
      return usuarioService.listaUsuarios();
    }     
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */      
    @GetMapping("/getRoles")
    List<Role> listaRoles() {      
      return usuarioService.listaRoles();
    }         
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */   
    @GetMapping("/getUsuario")
    @Secured("ROLE_ADMIN")
    Optional<Usuario> getUsuario(@RequestParam Long id_usuario) {      
      return usuarioService.getUsuario(id_usuario);
    }         
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */       
    @PostMapping("/save")
    Usuario save(@RequestBody Usuario usuario) {
        if(usuario.getId() == null){
            String password = usuarioService.generatePassword();
            usuarioService.novaPessoa(usuario, password);
            mailService.sendBoasVindasUsuario(usuario, password);                              
        } else {
            usuarioService.savePessoa(usuario);
        }
        return usuario;
    }          
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */      
    @PostMapping("/reenviamail")
    void reenviamail() {
        String userName                     =   ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Usuario user                        =   usuarioService.findByEmail(userName);     
        usuarioService.reenviaToken(user);
    }      
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */       
    @PostMapping("/flgMensagemdada")
    Usuario save(@RequestBody UsuarioFlgMensagens objFlgMsgs) {
        Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        objFlgMsgs.setUsuario(user);
        usuarioService.saveUserMsgs(objFlgMsgs);
        user.setFlgMsg(objFlgMsgs);
        return user;
    }    
    
}
