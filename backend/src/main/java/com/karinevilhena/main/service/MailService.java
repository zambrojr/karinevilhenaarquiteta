/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.service;

import com.karinevilhena.main.entities.Pessoa;
import com.karinevilhena.main.entities.estrutura.Usuario;
import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author MAURO PORTO
 */
@Service
public class MailService {
    
    
    @Value("${app.constants.url}")
    private String appUrl;        
    
    @Autowired
    TemplateEngine templateEngine;    
    
    
    
    
    @Autowired
    public JavaMailSender emailSender;        

    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public void sendBoasVindasUsuario(Usuario usuario, String password) {
        
        Pessoa objPessoa = usuario.getPessoa();
        
        Context context = new Context();
        context.setVariable("nome", objPessoa.getNomeCompleto());
        context.setVariable("email", usuario.getEmail());
        context.setVariable("senha", password);
        String conteudo =   templateEngine.process("mail/boasVindas", context);        
        
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            //messageHelper.setFrom("sample@dolszewski.com");
            messageHelper.setTo("mauroporto@gmail.com");
            messageHelper.setSubject("Bem vindo ao portal de loteamentos");
            messageHelper.setText(conteudo, true);
        };        

        emailSender.send(messagePreparator);             
        
    }
 
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public void sendConfirmationEmail(Usuario usuario, String password, String token) {
        
        Pessoa objPessoa = usuario.getPessoa();
        String confirmationUrl = appUrl + "/regitrationConfirm.html?token=" + token;
        
        Context context = new Context();
        context.setVariable("nome", objPessoa.getNomeCompleto());
        context.setVariable("email", usuario.getEmail());
        context.setVariable("senha", password);
        context.setVariable("appUrl", confirmationUrl);
        String conteudo =   templateEngine.process("mail/confirmaEmail", context);        
        
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("bhvr@vps15114.publiccloud.com.br");
            messageHelper.setTo(usuario.getEmail());
            messageHelper.setSubject("Bem vindo ao TGF BHVR");
            messageHelper.setText(conteudo, true);
        };        

        emailSender.send(messagePreparator);       
        
    }    

    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */       
    void sendNewConfirmationEmail(Usuario usuario, String token) {
        String confirmationUrl = appUrl + "/regitrationConfirm.html?token=" + token;
        
        Context context = new Context();
        context.setVariable("nome", usuario.getPessoa().getNomeCompleto());
        context.setVariable("appUrl", confirmationUrl);
        String conteudo =   templateEngine.process("mail/reenvioConfirmaEmail", context);        
        
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("bhvr@vps15114.publiccloud.com.br");
            messageHelper.setTo(usuario.getEmail());
            messageHelper.setSubject("TGF BHVR - Confirme seu email");
            messageHelper.setText(conteudo, true);
        };        

        emailSender.send(messagePreparator);     
    }
    
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */       
   /* public void sendProposta(Orcamento orcamento) {
        
        Context context = new Context();
        context.setVariable("nome", "Fulano");
        String conteudo =   templateEngine.process("mail/orcamento_proposta", context);        
        
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);
            messageHelper.setFrom("bhvr@vps15114.publiccloud.com.br");
            messageHelper.setTo(orcamento.getPessoa().getEmail());
            messageHelper.setSubject("karinevilhena - Proposta de Orçamento");
            messageHelper.setText(conteudo, true);
            messageHelper.addAttachment("proposta.pdf", new ByteArrayResource(ReportsService.getPropostaPdf()));
        };        

        emailSender.send(messagePreparator);     
    }    */
    
    
}
