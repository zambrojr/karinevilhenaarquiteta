/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karinevilhena.main.entities.Pessoa;
import com.karinevilhena.main.entities.blog.Categoria;
import com.karinevilhena.main.entities.blog.Post;
import com.karinevilhena.main.entities.blog.PostFilter;
import com.karinevilhena.main.entities.estrutura.Menu;
import com.karinevilhena.main.entities.estrutura.SiteContent;
import com.karinevilhena.main.entities.site.FaleConosco;
import com.karinevilhena.main.entities.site.MenuTopo;
import com.karinevilhena.main.entities.site.SessoesAtivasSite;
import com.karinevilhena.main.entities.site.SiteDestaques;
import com.karinevilhena.main.entities.site.SiteEspecialidades;
import com.karinevilhena.main.entities.site.SiteProcessoTrabalho;
import com.karinevilhena.main.service.BlogService;
import com.karinevilhena.main.service.EstruturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.karinevilhena.main.service.PessoaService;
import com.karinevilhena.main.service.SiteService;
import com.karinevilhena.main.service.UsuarioService;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;


//import com.karinevilhena.main.entities.Lote;

/**
 *
 * @author MAURO PORTO
 */
@RestController
@RequestMapping("/appublic")
public class PublicoController {

    @Autowired
    PessoaService pessoaService;
    
    @Autowired
    UsuarioService usuarioService;        

    @Autowired
    SiteService siteService;     
    
    @Autowired
    BlogService blogService;          
    
    @Autowired
    EstruturaService estruturaService;      
    
    @GetMapping("/listaEspecialidades")
    String listaEspecialidades() throws JsonProcessingException, Exception {
      List<SiteEspecialidades> temp = siteService.listaEspecialidades();
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson =  Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(temp).getBytes("UTF-8"));      
        return siteService.cryptoRetorno(arrayToJson);
    }         
    
    @GetMapping("/listadestaquessite")
    String listaDestaquesSite() throws JsonProcessingException, Exception {
      List<SiteDestaques> temp = siteService.listaDestaquesAtivos();
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson =  Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(temp).getBytes("UTF-8"));      
        return siteService.cryptoRetorno(arrayToJson);
    }          
    
    
    @GetMapping("/listaprocessostrabalho")
    String listaProcessosTrabalho() throws JsonProcessingException, Exception {
      List<SiteProcessoTrabalho> temp = siteService.listaProcessosTrabalho();
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson =  Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(temp).getBytes("UTF-8"));      
        return siteService.cryptoRetorno(arrayToJson);
    }        
     
    
    @GetMapping("/getespecialidade")
    SiteEspecialidades getespecialidade(@RequestParam String url) {
      SiteEspecialidades anuncio = siteService.getEspecialidadeByUrl(url);
      if(anuncio==null){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Falha");
      }
      return anuncio;
    }     
    
    @GetMapping("/getapresentacao")
    MenuTopo getapresentacao(@RequestParam String url) {
      MenuTopo menu = siteService.getMenuTopoByUrl(url);
      if(menu==null){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Falha");
      }
      return menu;
    }       
    
    
    @GetMapping("/listamenu")
    String ListaMenu() throws JsonProcessingException, Exception {
      List<Menu> temp = estruturaService.listaMenus();
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson =  Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(temp).getBytes("UTF-8"));      
        return siteService.cryptoRetorno(arrayToJson);
    }        
    
    
    
    @PostMapping("/faleconosco")
    public Boolean faleConosco(@RequestBody FaleConosco faleconosco) {            
        siteService.faleConosco(faleconosco);
        return true;
    }            
    
    
    @GetMapping("/blog/getposts")
    Page<Post> getposts(PostFilter filter) {
      Pageable paging = PageRequest.of(filter.getPage()-1, filter.getSize());
      return blogService.getPostsBlogPaginated(filter, paging);
    }         
    
    
    @GetMapping("/blog/post")
    Post getPost(@RequestParam String url) {
      return blogService.getPost(url);
    }      
     
    
    @GetMapping("/listaCategorias")
    String listaCategorias() throws JsonProcessingException, Exception {
      List<Categoria> temp = blogService.getAllCategorias();
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson =  Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(temp).getBytes("UTF-8"));      
        return siteService.cryptoRetorno(arrayToJson);
    }      
    
    
    @GetMapping("/blog/getCategoria")
    String getCategoria(String ref_id) throws JsonProcessingException, Exception {
        Categoria temp = blogService.getCategoria(ref_id);
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson =  Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(temp).getBytes("UTF-8"));      
        return siteService.cryptoRetorno(arrayToJson);
    }  
 
    
    @GetMapping("/blog/getautor")
    String getAutor(String ref_id) throws JsonProcessingException, Exception {
        Pessoa temp = pessoaService.getAutorByRefId(ref_id);
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson =  Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(temp).getBytes("UTF-8"));      
        return siteService.cryptoRetorno(arrayToJson);
    }      
    
    
    @GetMapping("/sessoessite")
     String getSessoesSite() throws JsonProcessingException, Exception {
        List<SessoesAtivasSite> temp = siteService.getSessoesSite();
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson =  Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(temp).getBytes("UTF-8"));      
        return siteService.cryptoRetorno(arrayToJson);
    }     
     
     
    @GetMapping("/content")
     String getcontent() throws JsonProcessingException, Exception {
         List<SessoesAtivasSite> sessoesativas = siteService.getSessoesSite();
         List<SiteDestaques> desta = siteService.listaDestaquesAtivos();
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        List<SiteContent> contents = new ArrayList();
        contents.add(new SiteContent("sessoesativas", Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(sessoesativas).getBytes("UTF-8"))));
        contents.add(new SiteContent("destaques", Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(desta).getBytes("UTF-8"))));
        
        SessoesAtivasSite sessaoAtiva = sessoesativas.stream().filter(sess -> "processo_trabalho".equals(sess.getCodigo())).findAny().orElse(null);
        if(sessaoAtiva != null){                
            contents.add(new SiteContent("processo_trabalho", Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(siteService.listaProcessosTrabalho()).getBytes("UTF-8"))));
        }        
        
        sessaoAtiva = sessoesativas.stream().filter(sess -> "servicos".equals(sess.getCodigo())).findAny().orElse(null);
        if(sessaoAtiva != null){        
            contents.add(new SiteContent("servicos", Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(siteService.listaEspecialidades()).getBytes("UTF-8"))));
        }        
        
        sessaoAtiva = sessoesativas.stream().filter(sess -> "dicas_blog".equals(sess.getCodigo())).findAny().orElse(null);
        if(sessaoAtiva != null){                
            PostFilter filter = new PostFilter();
            filter.setPage(1);
            filter.setSize(3);
            Pageable paging = PageRequest.of(filter.getPage()-1, filter.getSize());
            contents.add(new SiteContent("dicas_blog", Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(blogService.getPostsBlogPaginated(filter, paging)).getBytes("UTF-8"))));
        }        
        
        String arrayToJson =  Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(contents).getBytes("UTF-8"));      
        return siteService.cryptoRetorno(arrayToJson);
    }          
     
    
    
    
}