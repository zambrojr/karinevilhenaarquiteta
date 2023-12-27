/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.service;

import com.karinevilhena.main.entities.Pessoa;
import com.karinevilhena.main.entities.Role;
import com.karinevilhena.main.entities.estrutura.Usuario;
import com.karinevilhena.main.entities.estrutura.UsuarioFlgMensagens;
import com.karinevilhena.main.entities.estrutura.VerificationToken;
import com.karinevilhena.main.repository.RoleRepository;
import com.karinevilhena.main.repository.UsuarioFlgMensagensRepository;
import com.karinevilhena.main.repository.UsuarioRepository;
import com.karinevilhena.main.repository.estrutura.VerificationTokenRepository;
import com.karinevilhena.main.exceptions.UserAlreadyExistException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author MAURO PORTO
 */
@Service
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioRepository userRepository;    
    
    @Autowired
    private UsuarioFlgMensagensRepository usuarioFlgMensagensRepository;    
    
    @Autowired
    private PasswordEncoder passwordEncoder;    
    
    @Autowired
    private RoleRepository roleRepository;      
    
    @Autowired
    private PessoaService pessoaService;        
    
    @Autowired
    MailService mailService;        
    
    
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;             
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usuario user = userRepository.findByEmail(userName);
        
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }        
        
         return user;//User(user.getEmail(), user.getPassword(),getAuthorities(user));
    }

    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public String generatePassword() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }    
    
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }    
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    private static Collection<? extends GrantedAuthority> getAuthorities(Usuario user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    void habilitaUsuarioCliente(Pessoa proprietario) {
        
        Usuario user = userRepository.getPessoa(proprietario.getId_pessoa());
        if(!(user instanceof Usuario)){
            
            user = new Usuario();
            user.setAtivo(Boolean.TRUE);
            user.setEmail(proprietario.getEmail());
            user.setPassword(passwordEncoder.encode("admin"));  
            user.setPessoa(proprietario);
            
            List<Role> roles = new ArrayList<Role>();
            user.setRoles(roles);
        }

        Role roleCliente = roleRepository.getOne(new Long(3));
        if(!user.getRoles().contains(roleCliente)){
            user.getRoles().add(roleCliente);
        }

        userRepository.save(user);                    
    }

    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public List<Usuario> listaUsuarios() {
        return userRepository.findAll();
    }

    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public List<Role> listaRoles() {
        return roleRepository.findAll();
    }

    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public Optional<Usuario> getUsuario(Long id_usuario) {
        return userRepository.findById(id_usuario);
    }
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public Usuario findByEmail(String userName) {
        return userRepository.findByEmail(userName);
    }     
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public VerificationToken findNotVerifiedByToken(String token) {
        return verificationTokenRepository.findNotVerifiedByToken(token);
    }    

    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public Usuario savePessoa(Usuario usuario) {
        userRepository.save(usuario);
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
   public Usuario novaPessoa(Usuario usuario, String senha) {
        usuario.setPassword(passwordEncoder.encode(senha));        
        userRepository.save(usuario);
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
    @Transactional
    public Usuario novoUsuario(Usuario usuario, String password) {
       
        Usuario existente = findByEmail(usuario.getEmail());
        if(existente instanceof Usuario){
            throw new UserAlreadyExistException("Email já cadastrado"); 
        }
        usuario.getPessoa().setEmail(usuario.getEmail());
        Pessoa pessoa = pessoaService.savePessoa(usuario.getPessoa());
        usuario.setId(null);
        usuario.setPessoa(pessoa);
        usuario.setAtivo(Boolean.TRUE);
        usuario.setVerificado(Boolean.FALSE);
        usuario.setQtd_acesso(new Long(0));
        List<Role> roles = new ArrayList<Role>();
        usuario.setRoles(roles);
        
        UsuarioFlgMensagens flags = new UsuarioFlgMensagens();
        flags.setBoas_vindas(false);
        usuario.setFlgMsg(flags);
        flags.setUsuario(usuario);

        Role roleUsuario = roleRepository.getRoleByName("ROLE_USER");
        usuario.getRoles().add(roleUsuario);
        usuario.setPassword(passwordEncoder.encode(password));  
        Usuario novoUsuario = novaPessoa(usuario, password);
        
        String token = UUID.randomUUID().toString();
        createVerificationToken(novoUsuario, token);
        
        mailService.sendConfirmationEmail(usuario, password, token);
        
        return novoUsuario;
        
    }

    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public void createVerificationToken(Usuario user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }    
    
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    @Transactional
    public Boolean verificaToken(String token) {
       
        VerificationToken existente = findNotVerifiedByToken(token);
        if(existente instanceof VerificationToken){
            
            Usuario usuario = existente.getUser();
            usuario.setVerificado(Boolean.TRUE);
            userRepository.save(usuario);
            return true;
        } 
        
        return false;
    }
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    @Transactional
    public void reenviaToken(Usuario user) {
        
        VerificationToken objToken = verificationTokenRepository.findByUser(user.getId());
        verificationTokenRepository.delete(objToken);
        String token = UUID.randomUUID().toString();
        createVerificationToken(user, token);
        user.setPessoa(pessoaService.findPessoa(user.getPessoa().getId_pessoa()));
        mailService.sendNewConfirmationEmail(user, token);
    }
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
     public void saveUserMsgs(UsuarioFlgMensagens userMsgs){
        usuarioFlgMensagensRepository.save(userMsgs);
    }
    
    
    
}
