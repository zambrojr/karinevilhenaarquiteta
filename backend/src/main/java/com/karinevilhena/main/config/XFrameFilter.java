/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author PC
 */
public class XFrameFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest,
                                    HttpServletResponse httpResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        httpResponse.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");

        filterChain.doFilter(httpRequest, httpResponse);
    }

   
} 
