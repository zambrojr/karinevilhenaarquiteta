import axios from 'axios';




var filaRequisicoes = [];

console.log(process.env)
console.log(process.env.VUE_APP_BACKEND_URL)

var api = axios.create({
    baseURL: process.env.VUE_APP_BACKEND_URL+"/apidremup",
    //baseURL: "http://localhost:8080/apidremup",
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

            const parametros = new FormData();
            parametros.append("username", params.email );    
            parametros.append("password", params.password );   

            return axios.post(process.env.VUE_APP_BACKEND_URL+"/login", parametros)
        }
    },
    
    
    orcamento: {
        
        creiteriosdificuldades:{  
            listaCriterios(params) {
                return api.get("/criteriosdf/lista", {params});
            },         
            listaAmbiente(params) {
                return api.get("/criteriosdf/listaambiente", {params});
            },                            
        },

        saveOrcamento(params){
            return api.post("/orcamento/registra", params);
        }
   
    },



};  
