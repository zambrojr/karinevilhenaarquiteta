/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.config;

import com.karinevilhena.main.entities.Role;
import com.karinevilhena.main.entities.estrutura.Modulo;
import com.karinevilhena.main.entities.estrutura.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.karinevilhena.main.service.EstruturaService;
import com.karinevilhena.main.service.UsuarioService;

/**
 *
 * @author PC
 */
public class MySimpleUrlAuthenticationSuccessHandler
  implements AuthenticationSuccessHandler {
    
    @Autowired
    EstruturaService estruturaService;    
    
    @Autowired
    UsuarioService usuarioService;       
  
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication)
      throws IOException {
  
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected void handle(
            HttpServletRequest request,
            HttpServletResponse response, 
            Authentication authentication
    ) throws IOException {

        String targetUrl = determineTargetUrl(authentication, request);

        if (response.isCommitted()) {
            /*logger.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);*/
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication, HttpServletRequest request) {

        Map<String, String> roleTargetUrlMap = new HashMap<>();
        //roleTargetUrlMap.put("ROLE_USER", "/intra/testes/home");
        roleTargetUrlMap.put("ROLE_USER", request.getHeader("origin").concat("/intra"));
        roleTargetUrlMap.put("ROLE_ADMIN", request.getHeader("origin").concat("/intra"));
        roleTargetUrlMap.put("ROLE_VISITANTE", request.getHeader("origin").concat("/intra"));
        
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if(roleTargetUrlMap.containsKey(authorityName)) {
                
                if(authorityName.equals("ROLE_USER")){

                    Role role_usuario = usuarioService.getRoleByName(authorityName);
                    Long[] listaIdRoloes = {role_usuario.getId()};
                    List<Modulo> modulos = estruturaService.listaModuloResources(listaIdRoloes);
                    request.getSession().setAttribute("moduloAtivo", modulos.get(0).getId());   
                }
                
                return roleTargetUrlMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}