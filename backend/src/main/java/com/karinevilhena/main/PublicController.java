/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main;

import com.karinevilhena.main.entities.blog.PostFilter;
import com.karinevilhena.main.entities.site.MenuTopo;
import com.karinevilhena.main.entities.site.SessoesAtivasSite;
import com.karinevilhena.main.entities.site.SiteEspecialidades;
import com.karinevilhena.main.service.BlogService;
import com.karinevilhena.main.service.SiteService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MAURO PORTO
 */
@Controller
public class PublicController  {
   
    
    @Autowired
    SiteService siteService;    
    
    @Autowired
    BlogService blogService;        
    
   @RequestMapping("/")
    public String index(Model model) throws Exception {
        //siteService.teste();
        model.addAttribute("pageTitle", "Karine Vilhena Arquitetura e Interiores");
        model.addAttribute("pageContent", "Procurando serviços de Design de Interiores ou Arquitetura? Temos a sua solução. Procure-nos agora pelo Telefone (31)-99651-3247 ou nossos outros canais de comunicação e faça uma avaliação sem compromisso.");

        model.addAttribute("listaDestaquesSite", siteService.listaDestaquesAtivos());
        
        List<SessoesAtivasSite> sessoesAtivas = siteService.getSessoesSite();
        model.addAttribute("sessoesAtivas", sessoesAtivas); 

        SessoesAtivasSite sessaoAtiva = sessoesAtivas.stream().filter(sess -> "servicos".equals(sess.getCodigo())).findAny().orElse(null);
        if(sessaoAtiva != null){        
            model.addAttribute("listaEspecialidades", siteService.listaEspecialidades()); 
        }
        
        sessaoAtiva = sessoesAtivas.stream().filter(sess -> "processo_trabalho".equals(sess.getCodigo())).findAny().orElse(null);
        if(sessaoAtiva != null){                
            model.addAttribute("listaProcessosTrabalho", siteService.listaProcessosTrabalho());
        }
        
        sessaoAtiva = sessoesAtivas.stream().filter(sess -> "dicas_blog".equals(sess.getCodigo())).findAny().orElse(null);
        if(sessaoAtiva != null){                
            PostFilter filter = new PostFilter();
            filter.setPage(1);
            filter.setSize(3);
            Pageable paging = PageRequest.of(filter.getPage()-1, filter.getSize());
            model.addAttribute("blogPosts", blogService.getPostsBlogPaginated(filter, paging));
        }
        
        return "index.html";
    }   

   @RequestMapping("/index.html")
    public String indexHtml(Model model) throws Exception {
        //siteService.teste();
        model.addAttribute("pageTitle", "Karine Vilhena Arquitetura e Interiores");
        model.addAttribute("pageContent", "Procurando serviços de Design de Interiores ou Arquitetura? Temos a sua solução. Procure-nos agora pelo Telefone (31)-99651-3247 ou nossos outros canais de comunicação e faça uma avaliação sem compromisso.");
        model.addAttribute("listaEspecialidades", siteService.listaEspecialidades()); 
        model.addAttribute("listaDestaquesSite", siteService.listaDestaquesAtivos()); 
        return "index.html";
    }      
    
    
   @RequestMapping("/contato")
    public String contato(Model model) throws Exception {
        //siteService.teste();
        model.addAttribute("pageTitle", "Arquitetura e Interiores - Contato | Karine Vilhena");
        model.addAttribute("pageContent", "Procurando serviços de Design de Interiores ou Arquitetura? Temos a sua solução. Procure-nos agora pelo Telefone (31)-99651-3247 ou nossos outros canais de comunicação e faça uma avaliação sem compromisso.");
        return "contato.html";
    }       
    
   @RequestMapping("/about")
    public String about(Model model) throws Exception {
        //siteService.teste();
        model.addAttribute("pageTitle", "Sobre | Karine Vilhena Arquitetura e Interiores");
        model.addAttribute("pageContent", "Procurando serviços de Design de Interiores ou Arquitetura? Temos a sua solução. Procure-nos agora pelo Telefone (31)-99651-3247 ou nossos outros canais de comunicação e faça uma avaliação sem compromisso.");
        return "about.html";
    }     
    
   @RequestMapping("/servicos")
    public String nossaEspecialidade(Model model, HttpServletRequest request) throws Exception {
        //SiteEspecialidades especialidade = siteService.getEspecialidadeByUrl(urlid);
        //model.addAttribute("especialidade", especialidade); 
        model.addAttribute("pageTitle", "Arquitetura e Interiores - Nossos Serviços | Karine Vilhena");
        model.addAttribute("pageContent", "Procurando serviços de Design de Interiores ou Arquitetura? Temos a sua solução. Procure-nos agora pelo Telefone (31)-99651-3247 ou nossos outros canais de comunicação e faça uma avaliação sem compromisso.");
        model.addAttribute("listaEspecialidades", siteService.listaEspecialidades()); 
        return "nossaEspecialidade.html";
    }    
    
    
   @RequestMapping("/processo_trabalho")
    public String processoTrabalho(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("pageTitle", "Arquitetura e Interiores - Processo de Trabalho | Karine Vilhena");
        model.addAttribute("pageContent", "Procurando serviços de Design de Interiores ou Arquitetura? Conheça o nosso processo de trabalho simplificado. Procure-nos agora pelo Telefone (31)-99651-3247 ou nossos outros canais de comunicação e faça uma avaliação sem compromisso.");
        return "processoTrabalho.html";
    }       
    
   @RequestMapping("/servicos/{urlid}")
    public String nossaEspecialidade(@PathVariable("urlid") String urlid, Model model, HttpServletRequest request) throws Exception {
        SiteEspecialidades especialidade = siteService.getEspecialidadeByUrl(urlid);
        model.addAttribute("especialidade", especialidade); 
        model.addAttribute("pageTitle", "Arquitetura e Interiores - ".concat(especialidade.getTitulo().concat(" | Karine Vilhena")));
        model.addAttribute("pageContent", especialidade.getDescricao_breve());
        model.addAttribute("listaEspecialidades", siteService.listaEspecialidades()); 
        return "especialidade.html";
    }      
    
    
   @RequestMapping("/apresentacao/{urlid}")
    public String apresentacao(@PathVariable("urlid") String urlid, Model model, HttpServletRequest request) throws Exception {
        MenuTopo menuTopo = siteService.getMenuTopoByUrl(urlid);
        model.addAttribute("menuTopo", menuTopo); 
        model.addAttribute("pageTitle", menuTopo.getTitulo().concat(" | Karine Vilhena Arquitetura e Interiores"));
        model.addAttribute("pageContent", menuTopo.getResumo());
        model.addAttribute("listaEspecialidades", siteService.listaEspecialidades()); 
        return "menutopo.html";
    }          
    
    
}
