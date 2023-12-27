import axios from 'axios';
import router from '../routes/routes';






var filaRequisicoes = [];

var api = axios.create({
    baseURL: process.env.VUE_APP_BACKEND_URL+"/api",
    responseType: 'json',
});

api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token')
    if (token) {    config.headers.Authorization = 'Bearer '+localStorage.getItem('token') }  

    filaRequisicoes.push(config);
    router.app.showOverlay = true;

    return config;
});

api.interceptors.response.use(
    (response) => {

        if(filaRequisicoes.length>0)filaRequisicoes.splice(0,1);
        if(filaRequisicoes.length<=0) router.app.showOverlay = false;

        return response;
    },
    (error) => {

        filaRequisicoes.length = [];
        router.app.showOverlay = false;

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
    
    
    quadra: {
        listaQuadrasLote(params) {
            return api.get("/quadra/listaquadraslote", { params });
        },
        
        listaQuadrasLoteamento(params) {
            return api.get("/quadra/listaquadrasloteamento", { params });
        },        

        saveQuadra(params) {
            return api.post("/quadra/savequadra", params);
        },       

        saveListaQuadra(params) {
            return api.post("/quadra/saveListaQuadra", params);
        },               

        saveQuadraObject(params) {
            return api.post("/quadra/savequadraobject", params);
        }, 

    },


    lote: {
        listaLotes(params) {
            return api.get("/lote/listalotes", { params });
        },
        listaLotesPorStatus(params) {
            return api.get("/lote/listalotesporstatus", { params });
        },        
        saveLote(params) {
            return api.post("/lote/savelote", params);
        },         
        saveListaLotes(params) {
            return api.post("/lote/saveListaLotes", params);
        },          
        saveLoteObject(params) {
            return api.post("/lote/saveloteobject", params);
        },      
        saveValoresListaLote(params) {
            return api.post("/lote/saveValoresListaLote", params);
        },        
        updateLotePainelProjecao(params) {
            return api.post("/lote/updateLotePainelProjecao", params);
        },          
        get(params) {
            return api.get("/lote/get", { params });
        },   
        reserva(params) {
            return api.get("/lote/reserva", { params });
        },          
        cancelaReserva(params) {
            return api.post("/lote/cancelaReserva", params );
        },  
        confirmaVenda(params) {
            return api.post("/lote/venda/confirmaVenda", params );
        },     
        saveValoresLote(params) {
            return api.post("/lote/saveValoresLote", params);
        }, 
        getLoteParcelamento(params) {
            return api.get("/lote/getparcelamento", { params });
        },           
    },


    statuslote: {
        lista(params) {
            return api.get("/statuslote/lista", { params });
        },    
    },

   
    
    loteamento: {
        getLoteamento(params) {
            return api.get("/loteamento/getloteamento", { params });
        },         
        getLotesLoteamento(params) {
            return api.get("/lote/getLotesLoteamento", { params });
        },     
        getVendasTempo(params) {
            return api.get("/lote/getvendastempo", { params });
        },         
        listaLoteamentos(params) {
            return api.get("/loteamento/listaLoteamentos", { params });
        },
        listalotes(params) {
            return api.get("/loteamento/listalotes", { params });
        },        
        listaLoteamentosProprios(params) {
            return api.get("/loteamento/listaLoteamentosProprios", { params });
        },
        saveLoteamento(params) {
            return api.post("/loteamento/saveloteamento", params);
        },   


    },    

    loteamentoparcelamento: {

        getLoteamentoCondicoesParcelamento(params) {
            return api.get("/loteamentoparcelamento/getloteamentocondicoesparcelamento", { params });
        },        
        saveValoresParcelamentosLote(params){
            return api.post("/loteamentoparcelamento/saveValoresParcelamentosLote", params );
        },

        listalotesParcelamento(params) {
            return api.get("/loteamentoparcelamento/listalotes", { params });
        },   

        listalotesParcelamentoQuadra(params) {
            return api.get("/loteamentoparcelamento/listalotesquadra", { params });
        },   

        saveValoresLote(params) {
            return api.post("/loteamentoparcelamento/saveValoresLote", params);
        },         

    },
        

    imobiliaria: {
        anuncio:{
            lista() {
                return api.get("imobliaria/anuncio/lista", {  });
            },       
            save(params) {
                return api.post("/imobliaria/anuncio/salvar", params);
            },       
            get(params) {
                return api.get("imobliaria/anuncio/get", {params});
            },         
            getAnexos(params) {
                return api.get("/imobliaria/anuncio/listaanexos", {params});
            },              
            uploadanexo(params) {
                return api.post("/imobliaria/anuncio/uploadanexo", params);
            },             
            downloadanexo(params){
                let config = {responseType: 'blob'};
                return api.post('/imobliaria/anuncio/downloadanexo', params, config);                
            },
            deleteAnexo(params) {
                return api.post('/imobliaria/anuncio/deleteanexo', params);
            }         ,
            getFotos(params) {
                return api.get("/imobliaria/anuncio/listafotos", {params});
            },    
            uploadfoto(params) {
                return api.post("/imobliaria/anuncio/uploadfoto", params);
            },       
            deleteFoto(params) {
                return api.post('/imobliaria/anuncio/deletefoto', params);
            }                                             
        },

        site:{
            getdestaquessite(params) {
                return api.get("/imobliaria/cadastro/getdestaquessite", {params});
            },                
            getDestaqueById(params) {
                return api.get("/imobliaria/cadastro/getdestaquebyid", {params});
            },            
            saveDestaque(params) {
                return api.post('/imobliaria/cadastro/savedestaque', params);
            },                   
            uploadfotodestaque(params) {
                return api.post("/imobliaria/cadastro/uploadfotodestaque", params);
            },                
            getespecialidadessite(params) {
                return api.get("/imobliaria/cadastro/getespecialidadessite", {params});
            },                   
            getEspecialidadeById(params) {
                return api.get("/imobliaria/cadastro/getespecialidadebyid", {params});
            },       
            saveEspecialidade (params) {
                return api.post('/imobliaria/cadastro/saveespecialidade ', params);
            },                           
        }
    },


    pessoa: {
        getListaPessoa() {
            return api.get("/pessoa/getlistapessoa", {  });
        },         
        getListaPessoaJuridica() {
            return api.get("/pessoa/getlistapessoajuridica", {  });
        },         
        getListaEmpresas() {
            return api.get("/pessoa/getlistaempresa", {  });
        },                 
        getPessoa(params) {
            return api.get("/pessoa/get", { params });
        },   
        savePessoa(params) {
            return api.post("/pessoa/save", params);
        },
        getCorretor(params) {
            return api.get("/pessoa/getCorretor", { params });
        },
    },    



    cartorio: {
        getListaCartorio() {
            return api.get("/cartorio/getListaCartorio", {  });
        },         
    }, 
    
    
    clausulaContrato: {
        getListaclausula() {
            return api.get("/contrato/getListaclausulaContrato", {  });
        },      
        getClausulaContrato(params) {
            return api.get("/contrato/getClausulaContrato", { params });
        },         
        saveClausulaContrato(params) {
            return api.post("/contrato/saveClausulaContrato", params);
        },           
    },       

    



    utils: {
        loadCidades(params) {
            return api.get("/utils/loadCidades", { params });
        },        
        loadUfs() {
            return api.get("/utils/loadUfs", {  });
        },  
        loadBairros(params) {
            return api.get("/utils/loadBairros", { params });
        },          
                   
        loadOcupacoes() {
            return api.get("/utils/loadOcupacoes", {  });
        },       
        savebairro(params) {
            return api.post("/utils/savebairro", params);
        },              
        

        loadGrupoImovel() {
            return api.get("/utils/loadGrupoImovel", {  });
        },  
        
        loadTipoImovels() {
            return api.get("/utils/loadTipoImovels", {  });
        },  
        
        loadTipoNegocio() {
            return api.get("/utils/loadTipoNegocio", {  });
        },          

        loadAtributosImovel() {
            return api.get("/utils/loadAtributosImovel", {  });
        },              
        
        getGenericList(url){
            return api.get(url, {});
        },

        saveGeneric(url, params){
            return api.post(url, params);
        },        
        
    },
      
    venda: {
        vendaLote(params) {
            return api.post("/lote/venda/venda", params);
        },   
        reservaLote(params) {
            return api.post("/lote/reserva", params);
        },    
        getParcelas(params){
            return api.get("/lote/getParcelas", { params });
        },
        getVenda(params) {
            return api.get("/lote/venda", { params });
        },                      
        listaVendasTempo(params) {
            return api.get("/lote/listaVendasTempo", {params});
        },        
        getVendasCorretor(params) {
            return api.get("/lote/getVendasCorretor", {params});
        },                   
        getAnexos(params) {
            return api.get("/lote/venda/listaanexos", {params});
        },    
        uploadanexo(params) {
            return api.post("/lote/venda/uploadanexo", params);
        },      
        deleteAnexo(params) {
            return api.post("/lote/venda/deleteanexo", params);
        },                    
        
    },     

    cliente: {
        listaLotes(params) {
            return api.get("/cliente/listaLotes", {params});
        },           
    },  


    docs: {
        compravenda(params) {
            let config = {responseType: 'blob'};
            return api.post("/doc/doccompravenda", params, config);
        },           
        generico(url, params) {
            let config = {responseType: 'blob'};
            return api.post(url, params, config);
        },                                       
    },     

     relatorios: {
        loteamento:{
            vendasCorretor(params) {
                return api.get("/relatorios/vendasloteamentocorretor", { params });
            },  

            vendasTempo(params) {
                return api.get("/relatorios/vendasloteamentotempo", { params });
            },
        }, 
    },

    calendario: {
        getEventos(params) {
            return api.get("/calendario/getEventos", { params });
        },  
    },

    agenda: {
        agendaEvento(params){
            return api.post("/agenda/agendaEvento", params);
        }

    },    

    logatividades: {
        loadAll(){
            return api.get("/logatividades/loadAll", {});
        }

    },    
    


    financeiro: {
        remessa:{
            geraNovoLote(params) {
                return api.get("/financeiro/remessa/geraNovoLote", { params });
            },  
            downloadRemessa(params) {
                let config = { responseType: null , transformResponse: undefined};
                api.defaults.responseType="text"
                return api.get("/financeiro/remessa/downloadRemessa", { params }, config);
            },       
            lista(params) {
                return api.get("/financeiro/remessa/lista", { params });
            },    
            listaboletosarquivo(params) {
                return api.get("/financeiro/remessa/listaboletosarquivo", { params });
            },      
            listaBoletosSemRemessa(params) {
                return api.get("/financeiro/remessa/listaBoletosSemRemessa", { params });
            },                  

        },         
        planoconta:{
            lista(params) {
                return api.get("/contabil/planoconta/lista", { params });
            },  
        }, 
        lancamento:{
            insert(lancamentoDebito) {
                return api.post("/financeiro/lancamento/insert", lancamentoDebito);
            },        
            liquidar(lancamentoDebito) {
                return api.post("/financeiro/lancamento/liquidar", lancamentoDebito);
            },                 
            lista(params) {
                return api.get("/financeiro/lancamento/lista", { params });
            },
            listaRecorrente(params) {
                return api.get("/financeiro/lancamento/listarecorrente", { params });
            },    
            uploadanexo(params) {
                return api.post("/financeiro/lancamento/uploadanexo", params);
            },              
            listaanexos(params) {
                return api.get("/financeiro/lancamento/listaanexos", { params });
            },         
            downloadanexo(params){
                let config = {responseType: 'blob'};
                return api.post('/financeiro/lancamento/downloadanexo', params, config);                
            },
            deleteAnexo(params) {
                return api.post('/financeiro/lancamento/deleteanexo', params);
            }             

        },
        contabil:{
            listaBalancete(params) {
                return api.get("/contabil/lancamento/balancete", { params });
            } ,
            listaPlanoContaSaldo(params) {
                return api.get("/contabil/planoconta/listaPlanoContaSaldo", { params });
            } ,
            listaPlanoContaSaldoFilhas(params) {
                return api.get("/contabil/planoconta/listaPlanoContaSaldoFilhas", { params });
            }     ,
            listaLancamento(params) {
                return api.get("/contabil/lancamento/lista", { params });
            }                              
        },
        contabancaria:{
            lista(params) {
                return api.get("/financeiro/contabancaria/lista", { params });
            } ,
            getSaldosConta(params) {
                return api.get("/financeiro/contabancaria/getSaldosConta", { params });
            } ,
            getSaldoDiario(params) {
                return api.get("/financeiro/contabancaria/getSaldoDiario", { params });
            } ,
        },
        lancamentoRecorrente:{
            insert(dados) {
                return api.post("/financeiro/lancamento/insertlancrecorrente", dados);
            },              
        }        

    },



};  
