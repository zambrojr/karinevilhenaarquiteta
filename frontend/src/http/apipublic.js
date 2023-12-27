/* global sjcl */

import axios from 'axios';
import router from '../routes/public';

var filaRequisicoes = [];

var api = axios.create({
    baseURL: process.env.VUE_APP_BACKEND_URL+"/appublic",
    responseType: 'json',
});

var apitext = axios.create({
    baseURL: process.env.VUE_APP_BACKEND_URL+"/appublic",
    responseType: 'text',
});


api.interceptors.request.use((config) => {

    filaRequisicoes.push(config);
    //router.app.showOverlay = true;

    return config;
});

api.interceptors.response.use(
    (response) => {

        if(filaRequisicoes.length>0)filaRequisicoes.splice(0,1);
      //  if(filaRequisicoes.length<=0) router.app.showOverlay = false;

        return response;
    },
    (error) => {

        filaRequisicoes.length = [];
        //router.app.showOverlay = false;

        let status = Number.parseInt(error.response.status);
        let objVue = router.app;

        //erros de servidor
        if (status > 499) {

            objVue.$bvModal.msgBoxOk('Ocorreu um erro inesperado', {
                title: 'Erro',
                size: 'sm',
                headerBgVariant: "danger",
                buttonSize: 'sm',
                okVariant: 'danger',
                headerClass: 'p-2 border-bottom-0',
                footerClass: 'p-2 border-top-0',
                centered: true
              })
                .then(() => {
                    objVue.$root.closeWait();
                })

        }

        return Promise.reject(error);
    }
);

import {Base64} from 'js-base64';

export default {



    scl(data){

        var v = {		
            ciphertext:data,
            key:sjcl.codec.hex.toBits('77AD039F 0E31497E E8D6E6C8 D31CAD8F 8B1C2D4F B075A0ED C22A5656 53309CCA'),
            iv:sjcl.codec.hex.toBits('BC4E0EE4 BAD23E16 6AFC6017 0A936B2B'),
            mode: "gcm",
            tag: "64"		
        };
        var iv = v.iv, key = v.key,  aes, ciphertext=v.ciphertext;
        if (ciphertext.length === 0) { return; }
        if (!v.password && !v.key.length) {
            console.log("Can't decrypt: need a password or key!"); return;
        }
            /* it's raw */
          ciphertext = sjcl.codec.base64.toBits(ciphertext);
          aes = new sjcl.cipher.aes(key);
          try {
            v.plaintext = sjcl.codec.utf8String.fromBits(sjcl.mode[v.mode].decrypt(aes, ciphertext, iv, v.adata));
            var resposta = Base64.decode(v.plaintext);
            return JSON.parse(resposta);
          } catch (e) {
            console.log("Can't de: " + e);
          }        
    },    

    decode(plaintext){
        var resposta = Base64.decode(plaintext);
        return JSON.parse(resposta);        
    },




    site:{
        getEspecialidade(params) {
            return api.get("/getespecialidade", {params});
        },      
        getApresentacao(params) {
            return api.get("/getapresentacao", {params});
        },              
        faleconosco(params) {
            return api.post("/faleconosco", params);
        },      
        getBlogPosts(params) {
            return api.get("/blog/getposts", {params});
        },   
        getBlogPost(params) {
            return api.get("/blog/post", {params});
        },               
        getCategoria(params) {
            return apitext.get("/blog/getCategoria", {params});
        },      
        getAutor(params) {
            return apitext.get("/blog/getautor", {params});
        },       
        getSessoesSite(params) {
            return apitext.get("/sessoessite", {params});
        },                  
        content(params) {
            return apitext.get("/content", {params});
        },               
        
       /* getEmpreendimento(params) {
            return api.get("/getempreendimento", {params});
        },    

        */          
             
    },
  

    utils: {

        listaProcessosTrabalho(params) {
            return apitext.get("/listaprocessostrabalho", {params});
        },   
        listamenu(params) {
            return apitext.get("/listamenu", {params});
        },           
        listaDestaquesSite(params) {
            return apitext.get("/listadestaquessite", {params});
        },   
        listaEspecialidades() {
            return  apitext.get("/listaEspecialidades", {});
        },   
        listaCategorias() {
            return  apitext.get("/listaCategorias", {});
        },                   
        
/*        
        getSessoesSite() {
            return api.get("/sessoessite", {});
        },     
*/        
    },



};  
