/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karinevilhena.main.service;

import com.karinevilhena.main.entities.site.FaleConosco;
import com.karinevilhena.main.entities.site.MenuTopo;
import com.karinevilhena.main.entities.site.SessoesAtivasSite;
import com.karinevilhena.main.entities.site.SiteDestaques;
import com.karinevilhena.main.entities.site.SiteEspecialidades;
import com.karinevilhena.main.entities.site.SiteProcessoTrabalho;
import com.karinevilhena.main.repository.site.FaleConoscoRepository;
import com.karinevilhena.main.repository.site.MenuTopoRepository;
import com.karinevilhena.main.repository.site.SessoesAtivasSiteRepository;
import com.karinevilhena.main.repository.site.SiteDestaquesRepository;
import com.karinevilhena.main.repository.site.SiteEspecialidadesRepository;
import com.karinevilhena.main.repository.site.SiteProcessoTrabalhoRepository;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PB003321
 */
@Service
public class SiteService {

    
    @Autowired
    Environment env;      
    
    @Autowired
    SiteEspecialidadesRepository siteEspecialidadesRepository;
    
    @Autowired
    FaleConoscoRepository faleConoscoRepository;        

    @Autowired
    SiteDestaquesRepository siteDestaquesRepository;    
    
    @Autowired
    SiteProcessoTrabalhoRepository siteProcessoTrabalhoRepository;       
    /*
    @Autowired
    AtributosImovelRepository atributosImovelRepository;
    
    @Autowired
    TipoNegocioRepository tipoNegocioRepository;    
  
    @Autowired
    GrupoImovelRepository grupoImovelRepository;       
    
*/     
    
    @Autowired 
    MenuTopoRepository menuTopoRepository;      
    
    @Autowired
    SessoesAtivasSiteRepository sessoesAtivasSiteRepository;             
    
    
   public static final int GCM_TAG_LENGTH = 16;
    
   
    public static String getRandomDoubleBetweenRange(int min, int max){
        int x = ThreadLocalRandom.current().nextInt(min, max + 1);
        return String.valueOf(x);
    }    
    
    /**
     * <p>
     * Método para ...
     * </p>
     * @author Mauro Porto
     * @return List<Contrato>
     * @since 1.0
     */    
    public String generateCod(int tamanho) {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = tamanho;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        
        generatedString = generatedString.concat(getRandomDoubleBetweenRange(10000,99999));
        
        return generatedString.toUpperCase();
    }       
    
    
    public String cryptoRetorno(String toCrypt) throws InvalidKeySpecException, Exception{
        String password = "lcjDemo";
        byte[] saltBytes = Base64.getDecoder().decode("ELYI4svyJvg=");
        int iterations = 1000;
        int keyLength = 256;
        char[] passwordChars = password.toCharArray();
        //byte[] saltBytes = salt.getBytes();

        byte[] digest = hashPassword(passwordChars, saltBytes, iterations, keyLength);
        
        

        String encoded = Base64.getEncoder().encodeToString(digest);
        //System.out.println(encoded);
        
        SecretKey key = getKey(passwordChars, saltBytes, iterations, keyLength);
        
        
        byte[] iv = Base64.getDecoder().decode("vE4O5LrSPhZq/GAXCpNrKw==");
        byte[] cipherText = encrypt(toCrypt.getBytes("UTF-8"), key, iv);
        
        String cryy = Base64.getEncoder().encodeToString(cipherText);
        //System.out.println(cryy);
        
        return cryy;

        //System.out.println("HASSSSSSSSSSSSS");
        //String hashedString = Hex.encodeHexString(digest);
        
        //System.out.println(hashedString);        
    }
    
    
    public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) throws InvalidKeySpecException {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA256" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }       
    
    
    public static SecretKey getKey( final char[] password, final byte[] salt, final int iterations, final int keyLength ) throws InvalidKeySpecException {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA256" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            return key;
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }    
    
    
    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception
    {
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        
        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        
        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
        
        // Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
        
        // Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);
        
        return cipherText;
    }    

    @Cacheable("listaEspecialidades")
    public List<SiteEspecialidades> listaEspecialidades(){
        return siteEspecialidadesRepository.listaEspecialidades();
    }
    
    public SiteEspecialidades getEspecialidadeByUrl(String url) {
        return siteEspecialidadesRepository.getEspecialidadeByUrl(url);
    }    
    
    
    public MenuTopo getMenuTopoByUrl(String url){
        return menuTopoRepository.getMenuTopoByUrl(url);
    }      
    
    
    public void faleConosco(FaleConosco faleconosco) {
        faleConoscoRepository.save(faleconosco);    
    }    
        
    @Cacheable("destaquesSite")
    public List<SiteDestaques> listaDestaquesAtivos(){
        return siteDestaquesRepository.listaDestaquesAtivos();
    }         
    
    @Cacheable("listaProcessosTrabalho")
    public List<SiteProcessoTrabalho> listaProcessosTrabalho(){
        return siteProcessoTrabalhoRepository.listaProcessosTrabalho();
    }         
    
    
    @Cacheable("sessoesAtivas")
    public List<SessoesAtivasSite> getSessoesSite() {
        return sessoesAtivasSiteRepository.getSessoesSite();
    }       
    
    
    /*
    public List<SiteDestaques> listaDestaques(){
        return siteDestaquesRepository.listaDestaques();
    }    
       
      
    
    
    public SiteEspecialidades getEspecialidadeByUrl(String url) {
        return siteEspecialidadesRepository.getEspecialidadeByUrl(url);
    }

    public SiteDestaques getDestaqueByUrl(String url) {
        return siteDestaquesRepository.getDestaqueByUrl(url);
    }
    
    
    public List<SiteDestaques> getDestaquesSite() {
        return siteDestaquesRepository.findAll();
    }    

    public void saveGrupoImovel(GrupoImovels registro) {
        grupoImovelRepository.save(registro);
    }

    public void saveAtributoImovel(AtributosImovel registro) {
        atributosImovelRepository.save(registro);
    }

    public void saveTipoNegocio(TipoNegocio registro) {
        tipoNegocioRepository.save(registro);
    }

    public SiteDestaques getDestaqueById(Long id) {
        return siteDestaquesRepository.getDestaqueById(id);
    }

    public void saveDestaque(SiteDestaques destaque) {
        if(destaque.getCodigo()==null){
            Integer codgio = siteDestaquesRepository.maxCodigo();
            destaque.setCodigo(codgio+1);
        }
        siteDestaquesRepository.save(destaque);
    }
    
    
    public String uploadfotodestaque(MultipartFile file) throws IOException {
        String nameAfile = generateCod(30).concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename()));
        String filePath = env.getProperty("fotosDir").concat("banner/").concat(nameAfile);     
        file.transferTo(new File(filePath));        
        return nameAfile;
    }    

    public void faleConosco(FaleConosco faleconosco) {
        faleConoscoRepository.save(faleconosco);    
    }
    
    public List<SessoesAtivasSite> getSessoesSite() {
        return sessoesAtivasSiteRepository.findAll();
    }    

    public void saveSessoesSite(SessoesAtivasSite destaque) {
        sessoesAtivasSiteRepository.save(destaque);
    }

    public SiteEspecialidades getEspecialidadeById(Long id) {
        return siteEspecialidadesRepository.getEspecialidadeById(id);
    }

    public void saveEspecialidade(SiteEspecialidades destaque) {
        siteEspecialidadesRepository.save(destaque);
    }
    
    */
    
}
