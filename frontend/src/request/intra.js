import axios from 'axios';


var filaRequisicoes = [];

//let jQuery = require('jquery');

//var token = jQuery("meta[name='_csrf']").attr("content");
//var header = jQuery("meta[name='_csrf_header']").attr("content");

/*axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

axios.defaults.headers.common = {
    'X-CSRF-TOKEN': "759c9dee-0417-4c50-a774-437398deedb0"
};*/
//axios.defaults.withCredentials = true; 
//axios.defaults.withCredentials = true
var api = axios.create({
    baseURL: process.env.VUE_APP_BACKEND_URL+"/apintra",
    //baseURL: "http://localhost:8080/apidremup",
    withCredentials:true,
    responseType: 'json',
});

api.interceptors.request.use((config) => {

    
    //document.querySelector('#loading').classList.add('is-active');
    return config;
});


api.interceptors.response.use(
    (response) => {

        if(filaRequisicoes.length>0)filaRequisicoes.splice(0,1);
        //if(filaRequisicoes.length<=0) document.querySelector('#loading').classList.remove('is-active');

        return response;
    },
    (error) => {

        filaRequisicoes.length = [];
        //document.querySelector('#loading').classList.remove('is-active');

        //let status = Number.parseInt(error.response.status);


        return Promise.reject(error);
    }
);



export default {

    estrutura: {
        listaMenu() {
            return api.get("/estrutura/getmenu", {});
        },  

        listaModulos() {
            return api.get("/estrutura/getmodulos", {});
        },  

        selectModule(params) {
            return api.post("/estrutura/selectmodule", params);
        },                
        getmoduloatual() {
            return api.get("/estrutura/getmoduloatual", {});
        },   
        getmodulosResources() {
            return api.get("/estrutura/getmodulosResources", {});
        },                                   
    },


    usuario:{
        getPermissoesUser() {
            return api.get("/usuario/getpermissaomenu");
        },
        loadUserLoggedIn(){
            return api.get("/usuario/getUserLi");
        },
        listaUsuarios(){
            return api.get("/usuario/listaUsuarios");
        },        
        getRoles(){
            return api.get("/usuario/getRoles");
        },      
        getUsuario(params) {
            return api.get("/usuario/getUsuario", { params });
        },          
        save(params) {
            return api.post("/usuario/save", params);
        }, 
        login(params){
            return axios.post(process.env.VUE_APP_BACKEND_URL+"/api/signin", params)
        }
    },
    
    
    orcamento: {
        

        novoOrcamento(params){
            return api.post("/orcamento/novo", params);
        },

        inventario:{
            novo(params) {
                return api.post("/orcamento/inventario/insert", params);
            },
            lista(params) {
                return api.get("/orcamento/inventario/lista", {params});
            },
        },
        
        creiteriosdificuldades:{  
            listaCriterios(params) {
                return api.get("/orcamento/criteriosdf/lista", {params});
            },         
            listaAmbiente(params) {
                return api.get("/orcamento/criteriosdf/listaambiente", {params});
            },                            
            listaParametrosPrecificacao(params) {
                return api.get("/orcamento/criteriosdf/listaparametrosprecificacao", {params});
            }, 
            changeDescontoOrcamento(params) {
                return api.post("/orcamento/criteriosdf/changedescontoorcamento", params);
            },            
                              
        },
        
        getCotacaoPesoAtual(params) {
            return api.get("/orcamento/criteriosdf/getcotacaopesoatual", {params});
        },        
        saveOrcamento(params){
            return api.post("/orcamento/registra", params);
        },

        listaOrcamentos(params) {
            return api.get("/orcamento/listaorcamentos", {params});
        },   

        getOrcamento(params) {
            return api.get("/orcamento/getorcamento", {params});
        },          
        
   
        getListaAcessoriosSugeridos(params) {
            return api.get("/orcamento/getacessoriossugeridos", {params});
        },             

        getlistadescontosproposta(params) {
            return api.get("/orcamento/getlistadescontosproposta", {params});
        },             
        

        insertAcessorio(params){
            return api.post("/orcamento/insertacessorio", params);
        },

        updateAcessorio(params){
            return api.post("/orcamento/updateacessorio", params);
        },        

        fecharOrcamento(params){
            return api.post("/orcamento/fechar", params);
        },          

        deleteAcessorio(params){
            return api.delete("/orcamento/deleteacessorioambiente", {params});
        },

        deleteOrcamentoAmbiente(params){
            return api.delete("/orcamento/deleteorcamentoambiente", {params});
        },        

        updateProporcaoAmbiente(params){
            return api.post("/orcamento/updateproporcaoambiente", params);
        },

        insertOrcamentoAmbiente(params){
            return api.post("/orcamento/insertorcamentoambiente", params);
        },

        updateOrcamentoCriterioDificuldade(params){
            return api.post("/orcamento/updateorcamentocriteriodificuldade", params);
        },        

        insertOrcamentoCriterioDificuldade(params){
            return api.post("/orcamento/insertorcamentocriteriodificuldade", params);
        },          

        updateOrcamentoParametrosPreco(params){
            return api.post("/orcamento/updateorcamentoparametrospreco", params);
        },            

        setOrcamentoListaAcessoriosSugeridos(params){
            return api.post("/orcamento/setorcamentolistaacessoriossugeridos", params);
        },    
        recalculaTotalAmbiente(params){
            return api.post("/orcamento/recalculatotalambiente", params);
        },            

        insertAmbienteCriterioDificuldade(params){
            return api.post("/orcamento/insertambientecriteriodificuldade", params);
        },              

    },


    utils:{
        getGenericList(url){
            return api.get(url, {});
        },
    },


};  
