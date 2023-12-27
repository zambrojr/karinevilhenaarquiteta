/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.service;

import com.karinevilhena.main.entities.Pessoa;
import com.karinevilhena.main.entities.blog.Categoria;
import com.karinevilhena.main.entities.blog.Post;
import com.karinevilhena.main.entities.blog.PostFilter;
import com.karinevilhena.main.repository.blog.CategoriaRepository;
import com.karinevilhena.main.repository.blog.PostRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.StringUtils;

/**
 *
 * @author mauro
 */
@Service
public class BlogService {
    
    @Autowired
    CategoriaRepository categoriaRepository;
    
    @Autowired
    PostRepository postRepository;    
    
    @PersistenceContext
    private EntityManager entityManager;    
    
    
    public List<Categoria> getAllCategorias(){
        return categoriaRepository.findAll();
    }
    
    
    public List<Post> getPostsBlog(){
        return postRepository.getPosts();
    }    

    
    private Long total(PostFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Post> root = criteria.from(Post.class);

        List<Predicate> predicates = criarRestrincoes(filter, builder, root);
        criteria.where(predicates.toArray(new Predicate[0]));
        criteria.select(builder.count(root));
        return entityManager.createQuery(criteria).getSingleResult();
    }      
    
    public Page<Post> getPostsBlogPaginated(PostFilter filter, Pageable pageable) {        
                
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();        
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        
        Root<Post> anuncio = cq.from(Post.class);
        Fetch<Object, Object> autor = anuncio.fetch("autor", JoinType.INNER);
        autor.fetch("endereco", JoinType.INNER);
        anuncio.fetch("categoria", JoinType.INNER);
        
        List<Predicate> predicates = criarRestrincoes(filter, cb, anuncio);
        cq.where(predicates.toArray(new Predicate[0]));  
        
        List<Order> orderList = new ArrayList();
        orderList.add(cb.desc(anuncio.get("data")));
        cq.orderBy(orderList);                
                
        TypedQuery<Post> result = entityManager.createQuery(cq);        
        result.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        result.setMaxResults(pageable.getPageSize());        
        
        
        List<Post> anucnios = result.getResultList();
        
        return new PageImpl<>(anucnios, pageable, total(filter));
        
    }

    public Post getPost(String id) {
        return postRepository.findByRefId(id);
    }

    
    private List<Predicate> criarRestrincoes(PostFilter filter, CriteriaBuilder cb, Root<Post> anuncio) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (!StringUtils.isEmpty(filter.getCategoria())) {
            Join<Post, Categoria> tiponegocio = anuncio.join("categoria", JoinType.INNER);
            predicates.add(cb.equal(tiponegocio.get("ref_id"), filter.getCategoria()));
        }        
        
        if (!StringUtils.isEmpty(filter.getUser())) {
            Join<Post, Pessoa> pessoa = anuncio.join("autor", JoinType.INNER);
            predicates.add(cb.equal(pessoa.get("ref_id"), filter.getUser()));
        }               
        
        predicates.add(cb.equal(anuncio.get("publicado"), true));
        
        return predicates;
    }

    public Categoria getCategoria(String ref_id) {
        return categoriaRepository.getCategoriaByRefId(ref_id);
    }
    
}
