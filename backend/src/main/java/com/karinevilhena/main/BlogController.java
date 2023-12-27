/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main;

import com.karinevilhena.main.entities.Pessoa;
import com.karinevilhena.main.entities.blog.Categoria;
import com.karinevilhena.main.entities.blog.Post;
import com.karinevilhena.main.entities.blog.PostFilter;
import com.karinevilhena.main.service.BlogService;
import com.karinevilhena.main.service.PessoaService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author MAURO PORTO
 */
@Controller
public class BlogController  {
 
    
    @Autowired
    BlogService blogService;      
    
    @Autowired
    PessoaService pessoaService;    
    
    @RequestMapping("/blog")
    public String index(@RequestParam("page") Optional<Integer> page, Model model) throws Exception {
       int currentPage = page.orElse(1);
       int size = 5; //default page size is 10
       currentPage--;
       model.addAttribute("pageTitle", "Blog");
       model.addAttribute("title", "Blog");
       model.addAttribute("categorias", blogService.getAllCategorias());
       
       PostFilter filter = new PostFilter();
       
       model.addAttribute("postsBlog", blogService.getPostsBlogPaginated(filter, PageRequest.of(currentPage, size, Sort.by("id").descending())));
       return "blog.html";
    }      
    
    @RequestMapping("/blog/categoria/{someID}")
    public String postPorCategoria(@PathVariable(value="someID") String id, @RequestParam("page") Optional<Integer> page, Model model) throws Exception {
       int currentPage = page.orElse(1);
       int size = 5; //default page size is 10
       currentPage--;
       model.addAttribute("pageTitle", "Blog");
       model.addAttribute("title", "Blog");       
       model.addAttribute("categorias", blogService.getAllCategorias());
       
       Categoria cat = blogService.getCategoria(id);
       PostFilter filter = new PostFilter();
       filter.setCategoria(cat.getRef_id());
       model.addAttribute("link", "/categoria/".concat(cat.getRef_id()));
       
       model.addAttribute("postsBlog", blogService.getPostsBlogPaginated(filter, PageRequest.of(currentPage, size, Sort.by("id").descending())));
       return "blog.html";
    }          
    
    
    @RequestMapping("/blog/autor/{someID}")
    public String postPorAutor(@PathVariable(value="someID") String id, @RequestParam("page") Optional<Integer> page, Model model) throws Exception {
       int currentPage = page.orElse(1);
       int size = 5; //default page size is 10
       currentPage--;
       model.addAttribute("pageTitle", "Blog - Autor ");
       model.addAttribute("title", "Blog");       
       model.addAttribute("categorias", blogService.getAllCategorias());
       
       Pessoa pes = pessoaService.getAutorByRefId(id);
       PostFilter filter = new PostFilter();
       filter.setUser(pes.getRef_id());
       model.addAttribute("link", "/autor/".concat(pes.getRef_id()));
       
       model.addAttribute("postsBlog", blogService.getPostsBlogPaginated(filter, PageRequest.of(currentPage, size, Sort.by("id").descending())));
       return "blog.html";
    }      
    
    
  
    @RequestMapping("/blog/post/{someID}")
    public String post(@PathVariable(value="someID") String id, Model model) throws Exception {
       Post post = blogService.getPost(id);
       model.addAttribute("pageTitle", post.getTitulo());       
       model.addAttribute("title", post.getTitulo());   
       model.addAttribute("categorias", blogService.getAllCategorias());
       model.addAttribute("post", post);
       return "blog-detail.html";
    }      
    
    
  
    
    
}
