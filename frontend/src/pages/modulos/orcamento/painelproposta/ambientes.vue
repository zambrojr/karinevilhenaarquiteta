<template>
<div>
    <b-container class="pt-5">
        <div v-for="(objAmbiente, ind) in listaAmbientes" :key="objAmbiente.id">
            <b-row sm="4" class="pb-3 pt-3" v-bind:class="{ divBkgStr: par(ind) }">
                <b-col sm="3">
                    <b-row>
                    <div class="col">{{objAmbiente.nome}}</div>
                    <div class="p-1 pl-3"><a @click="addListaAmbiente(objAmbiente)"><b-icon icon="plus-circle"></b-icon></a></div>
                    </b-row>
                </b-col>
                <b-col>
                    <template v-for="(objComp, ind) in objAmbiente.lista" >
                        <div :key="ind"> 
                            <div class="row pb-2">
                                <div class="col text-center"><strong>{{lblProporcaoComodo(objAmbiente.nome, ind)}}</strong></div>
                            </div>                                       
                            <div class="row">
                                <div class="col"><select-proporcao @input="setAcessoriosAmbiente(objAmbiente, objComp)" v-model="objComp.proporcaoAmbiente"></select-proporcao></div>
                                <div class="col text-right">{{formatMonetario(objComp.vlr_total)}}</div>
                                <div class="col-2 p-2 text-center"><a @click="dropListaAmbiente(objAmbiente, objComp, ind)"><b-icon icon="trash"></b-icon></a></div>
                            </div>           
                            <div class="row pt-3">
                                <div class="col">
                                    <b-tabs pills fill>
                                        <b-tab active>
                                            <template v-slot:title>
                                                    <strong>Acessórios</strong>
                                            </template>
                                            <b-row>
                                                <b-col>
                                                    <b-table stacked="md" selectable select-mode="single"  class="small text-left relatorio" :fields="fieldsParams"  :small=true :striped=true :items="objComp.orcamentoAmbienteAcessorio">
                                                            <template v-slot:cell(produto)="row">
                                                                {{row.item.acessorio.descricao}}
                                                            </template>           
                                                            <template v-slot:cell(qtd_sugerido)="row">
                                                                {{row.item.qtd_sugerido}}
                                                            </template>    
                                                            <template v-slot:cell(qtd)="row">
                                                                <input type="number" @input="calculaPrecoAcessorioFinal(row.item, objComp)" v-model="row.item.qtd" class="inputPersonal inputPersonalMinsize">
                                                            </template>                        
                                                            <template v-slot:cell(vlr_sugerido)="row">
                                                                {{formatMonetario(row.item.acessorio.vlr_sugerido)}}
                                                            </template>                     
                                                            <template v-slot:cell(vlr_total)="row">
                                                                <money class="inputPersonal inputPersonalMinsize" @input="salvaAcessorioFinal(row.item, objComp)" v-model="row.item.vlr_total" v-bind="money"></money>
                                                            </template>                  
                                                            <template v-slot:cell(acoes)="row">
                                                            <a @click="dropAcessorioAmbiente(objComp, row.item)"><b-icon icon="trash"></b-icon></a>
                                                            </template>                                                                                                                                                                                                                                                                      
                                                    </b-table>

                                                </b-col>   
                                            </b-row>                                            
                                            <b-row class="pb-5">
                                                <b-col class="text-center">
                                                    <b-button @click="addAcessorio(objComp)" variant="outline-secondary">Inserir Produto&nbsp;<b-icon icon="plus-circle"></b-icon></b-button>
                                                </b-col>   
                                            </b-row>

                                        </b-tab>
                                        <b-tab>
                                            <template v-slot:title>
                                                    <strong>Critérios de Dificuldade</strong>
                                            </template>

                                                <b-row>
                                                <b-col>
                                                    <b-table stacked="md" class="small text-left relatorio" :fields="fieldsCriterios"  :small=true :striped=true :items="objComp.orcamentoAmbienteCriterioDificuldade">
                                                            <template v-slot:cell(nome)="row">
                                                                {{row.item.criterioDificuldade.nome}}
                                                            </template>           
                                                            <template v-slot:cell(opcoes)="row">
                                                                <b-form-radio-group @change="chnageCriterioDificuldade(objComp,row.item)" v-model="row.item.id_opcao">
                                                                    <b-col> 
                                                                        <b-form-radio size="lg" v-for="(opcao) in row.item.criterioDificuldade.criterioDificuldadeOpcao" :value="opcao.id" :key="opcao.id" >{{opcao.nome}}</b-form-radio>
                                                                    </b-col>    
                                                                </b-form-radio-group>
                                                            </template>                                                                                                
                                                    </b-table>
                                                </b-col>
                                                </b-row>
                                            

                                        </b-tab>
                                    </b-tabs>
                                </div>
                            </div>       
                        </div>
                    </template>                                                                                      
                </b-col>                                                
            </b-row>   
        </div>       
    </b-container> 

    <b-modal v-model="modalAcessorio" cancel-title="Cancelar" ok-title="Inserir" @ok="insertAcessorio" :ok-disabled="!isAcessorioSelecionado">
        <select-generic url="/acessorios/lista" :fields="fieldsModalAcessorios" v-on:selected="acessorioSelecionadoListagem"></select-generic>
    </b-modal>

</div>
</template>

<script>

import selectProporcao from "../../../../components/orcamento/selectProporcao"
import selectgeneric from "../../../../components/general/selectGeneric"
import { Money } from "v-money";

var _ = require('lodash');

export default {
    
    /* */
    components: {      
        "select-proporcao": selectProporcao,
        "select-generic": selectgeneric,                
        Money
    }, 

    /* */
    props: {
        value:{type:Array, default: function(){ return []} },
        orcamento_id:{type:Number, default:null}
    }, 


    /* */
    data() {
        return {
            listaAmbientes:[],

            listaCriterios:[],
            //acessorios
            modalAcessorio: false,
            objAmbienteSelecionado:{},
            acessorioSelecionado:{},
            fieldsModalAcessorios: [ { key: 'descricao', label: 'Descricao', sortable: true}],
            fieldsParams: [
                    { key: 'produto', label: 'Produto',  sortable: true },
                    { key: 'qtd_sugerido', label: 'Qtd Sugerido',  sortable: true },
                    { key: 'qtd', label: 'Qtd',  sortable: true },
                    { key: 'vlr_sugerido', label: 'Vlr Sugerido',  sortable: true },
                    { key: 'vlr_total', label: 'Vlr Final',  sortable: true },
                    { key: 'acoes', label: '', class:"text-center"},
            ],  
            fieldsCriterios: [
                    { key: 'nome', label: 'Criterio',  sortable: true },
                    { key: 'opcoes', label: 'Opções',  sortable: true },
            ],    

            money: {
                decimal: ',',
                thousands: '.',
                prefix: '',
                suffix: '',
                precision: 2,
                masked: false /* doesn't work with directive */
            }                                      
        }
    },

    /* */
    computed:{
        isAcessorioSelecionado(){ return this.acessorioSelecionado!==null && Object.prototype.hasOwnProperty.call(this.acessorioSelecionado, "id") },
    },

    /* */
    methods: {


        /* */
        _addAmbiente(newObjeto, objAmbiente){
                if(!Object.prototype.hasOwnProperty.call(objAmbiente, 'lista')){
                    this.$set(objAmbiente, "lista", []);
                }
                
                newObjeto.proporcaoAmbiente={};
                newObjeto.orcamentoAmbienteAcessorio=[];
                newObjeto.orcamentoAmbienteCriterioDificuldade=[];              
                
                this.listaCriterios.forEach(criterio=>{
                        var opcaoSelec = {id:null, criterioDificuldade: criterio, criterioDificuldadeOpcao:{}, id_opcao:null};
                        newObjeto.orcamentoAmbienteCriterioDificuldade.push(opcaoSelec)
                });

                newObjeto.vlr_total=0;

                objAmbiente.lista.push(newObjeto);//{proporcaoAmbiente:{}, orcamentoAmbienteAcessorio:[]});

                this.$emit("input", this.value);
        },

        /* */
        addListaAmbiente(objAmbiente){
            if(this.$root.isUsuarioValido())
            {
                this.$api.orcamento.insertOrcamentoAmbiente({orcamento:{id:this.orcamento_id},ambiente:objAmbiente}).then((response) => {
                    if(response.status == 200){
                        var newObjeto = response.data;
                        this._addAmbiente(newObjeto, objAmbiente);
                    }
                })
            }
            else 
            {
                var newObjeto = {orcamento:{id:this.orcamento_id},ambiente:objAmbiente};
                this._addAmbiente(newObjeto, objAmbiente);
            }

        },


        /* */
        __confirmadropListaAmbiente(objAmbiente, ind){
            objAmbiente.lista.splice(ind,1)
            this.$emit("valorambientes", this.valorTotalAcessorios());
        },
        /* */
        confirmadropListaAmbiente(objAmbiente, objComp, ind){

            if(this.$root.isUsuarioValido())
            {
                this.$api.orcamento.deleteOrcamentoAmbiente({id:objComp.id}).then((response) => {
                    if(response.status == 200){
                        this.__confirmadropListaAmbiente(objAmbiente, ind);
                    }
                });
            }
            else{
                this.__confirmadropListaAmbiente(objAmbiente, ind);
            }

            this.$emit("input", this.value);
        },

        dropListaAmbiente(objAmbiente, objComp, ind){
            this.$bvModal.msgBoxConfirm('Deseja excluir permanentemente este ambiente?', {
                    title: 'Ambiente',
                    okTitle: 'Excluir',
                    cancelTitle: 'Cancelar',
                    centered: true
                    })
                    .then(value => {
                        if(value){
                            this.confirmadropListaAmbiente(objAmbiente, objComp, ind);
                        }
            })

        },

        /* */
        getListaAmbientes(){
            this.$api.orcamento.creiteriosdificuldades.listaAmbiente().then((response) => {
                if(response.status == 200){
                    this.listaAmbientes    =   [];
                    response.data.forEach(objAmbiente=>{
                        var lista = this.value.filter(x=>{return x.ambiente.id==objAmbiente.id});




                        lista.forEach(ambiente=>{


                            this.listaCriterios.forEach(criterio=>{

                                    var opcaoSelec = ambiente.orcamentoAmbienteCriterioDificuldade.find(elem=>{return elem.criterioDificuldade.id == criterio.id})
                                    if(opcaoSelec==undefined){
                                        opcaoSelec = {id:null, criterioDificuldade: criterio, criterioDificuldadeOpcao:{}, id_opcao:null};
                                        ambiente.orcamentoAmbienteCriterioDificuldade.push(opcaoSelec)
                                    } else {
                                        this.$set(opcaoSelec.criterioDificuldade, "criterioDificuldadeOpcao",criterio.criterioDificuldadeOpcao);
                                        this.$set(opcaoSelec, "id_opcao",opcaoSelec.criterioDificuldadeOpcao.id);
                                    }

                            })


                        });

                        this.$set(objAmbiente, "lista", lista);
                        this.listaAmbientes.push(objAmbiente);
                    });
                    this.$emit("valorambientes", this.valorTotalAcessorios());
                }
            });  
        },



        ///////////////ACESSORIOS/////////////////////

        /* */
        valorTotalAcessorios(){
            return _.sumBy(this.listaAmbientes, function(objComp) { 
                return _.sumBy(objComp.lista, function(o) { 
                    return  _.sumBy(o.orcamentoAmbienteAcessorio, function(o) { return o.vlr_total; });
                });
            });
        },


        /* */
        __dropAcessorioAmbiente(objComp, objAcessorio){
            var indAce = objComp.orcamentoAmbienteAcessorio.findIndex(x=>{return x.id == objAcessorio.id})
            objComp.orcamentoAmbienteAcessorio.splice(indAce,1)
            this.__sumObjComp(objComp);
            this.$emit("valorambientes", this.valorTotalAcessorios());
        },
        /* */
        dropAcessorioAmbiente(objComp, objAcessorio){

            if(this.$root.isUsuarioValido())
            {
                this.$api.orcamento.deleteAcessorio({id:objAcessorio.id}).then((response) => {
                    if(response.status == 200){
                        this.__dropAcessorioAmbiente(objComp, objAcessorio);
                    }
                });
            }
            else{
                this.__dropAcessorioAmbiente(objComp, objAcessorio);
            }
        },

        /* */
        acessorioSelecionadoListagem(items){
            this.acessorioSelecionado = items.length>0    ?   items[0]    :   null;
        },


        /* */
        _addAcessorio(data, objAmbienteSelecionado){
                    objAmbienteSelecionado.orcamentoAmbienteAcessorio.push(data);
                    objAmbienteSelecionado.vlr_total       =   _.sumBy(objAmbienteSelecionado.orcamentoAmbienteAcessorio, function(o) { return o.vlr_total; });
                    this.$emit("valorambientes", this.valorTotalAcessorios());
        },
        /* */
        persisteAcessorio(acessorioSelecionado, objAmbienteSelecionado)
        {
            if(this.$root.isUsuarioValido())
            {            
                this.$api.orcamento.insertAcessorio({acessorio: acessorioSelecionado, orcamentoAmbiente:{id: objAmbienteSelecionado.id}, qtd:1, qtd_sugerido: null, vlr_total: 0}).then((response) => {
                    if(response.status == 200){
                        this._addAcessorio(response.data, objAmbienteSelecionado);
                    }
                });            
            }
            else
            {
                this._addAcessorio({acessorio: acessorioSelecionado, orcamentoAmbiente:{id: objAmbienteSelecionado.id}, qtd:0, qtd_sugerido: null, vlr_total: 0}, objAmbienteSelecionado);
            }
        },


        /* */
        insertAcessorio(){
            this.persisteAcessorio(this.acessorioSelecionado, this.objAmbienteSelecionado);
        },

        /* */
        addAcessorio(objComp){
            this.objAmbienteSelecionado = objComp;
            this.modalAcessorio = true;
        },

        confirmaSetAcessoriosAmbiente(ambiente, objComp){
            //Inserir Lista de acessorios sugeridos// this.persisteAcessorio(acessorioSelecionado, objAmbienteSelecionado)
            if(this.$root.isUsuarioValido())
            {
                this.$api.orcamento.setOrcamentoListaAcessoriosSugeridos({id:objComp.id, proporcaoAmbiente: objComp.proporcaoAmbiente, ambiente:objComp.ambiente}).then((response) => {
                    if(response.status == 200){
                        this.$set(objComp, "orcamentoAmbienteAcessorio", response.data);
                        this.$emit("valorambientes", this.valorTotalAcessorios());
                        this.__sumObjComp(objComp);
                    }
                });
            } else {
                this.__sumObjComp(objComp);
            }
        },


        /* */
        setAcessoriosAmbiente(ambiente, objComp){

            if(this.$root.isUsuarioValido())
            {
                this.$api.orcamento.updateProporcaoAmbiente({id:objComp.id, proporcaoAmbiente: objComp.proporcaoAmbiente}).then((response) => {
                    if(response.status != 200){
                        console.log(ambiente, objComp);
                    }
                }); 
            }

            if(Object.prototype.hasOwnProperty.call(objComp, 'orcamentoAmbienteAcessorio') && objComp.orcamentoAmbienteAcessorio.length>0){

                this.$bvModal.msgBoxConfirm('Já existe uma lista de produtos vinculados à este ambiente. Deseja manter a lista atual ou criar uma nova?', {
                        title: 'Lista de Produtos',
                        okTitle: 'Criar uma Nova',
                        cancelTitle: 'Manter a atual',
                        centered: true
                        })
                        .then(value => {
                            if(value){  this.confirmaSetAcessoriosAmbiente(ambiente, objComp);  }
                        })
            } else {
                this.confirmaSetAcessoriosAmbiente(ambiente, objComp);
            }


        },        

        /* */
        __recalculaVlrAmbiente(objComp){
            this.$api.orcamento.recalculaTotalAmbiente(objComp).then((response) => {
                if(response.status != 200){
                    console.log("erro")
                }
                this.$emit("valorambientes", this.valorTotalAcessorios());
            });            
        },
        

        /* */
        __sumObjComp(objComp){
            objComp.vlr_total       =   _.sumBy(objComp.orcamentoAmbienteAcessorio, function(o) { return o.vlr_total; });
        },

        /* */
        calculaPrecoAcessorioFinal: _.debounce(function (objAcessorio, objComp) {
                objAcessorio.vlr_total  =   parseInt(objAcessorio.qtd) * objAcessorio.acessorio.vlr_sugerido;
                this.persisteCalculaPrecoAcessorioFinal(objAcessorio, objComp);
        }, 500),

        /* */
        salvaAcessorioFinal: _.debounce(function (objAcessorio, objComp) {
                this.persisteCalculaPrecoAcessorioFinal(objAcessorio, objComp);
        }, 500),

        /* */
        persisteCalculaPrecoAcessorioFinal(objAcessorio, objComp){
            this.__sumObjComp(objComp);
            if(this.$root.isUsuarioValido())
            {      
                this.$api.orcamento.updateAcessorio(objAcessorio).then((response) => {
                    if(response.status != 200){
                        console.log("erro")
                    }
                    this.$emit("valorambientes", this.valorTotalAcessorios());
                });            
            }
            else {
                this.$emit("valorambientes", this.valorTotalAcessorios());
            }
        },

        //////////////////////////////////


        /* */
        par(indOrc){
            return indOrc%2==0
        },        

        /* */
        lblProporcaoComodo(comodo,idx){
            var linha = parseInt(idx)+1;
            return comodo+" "+linha;
        },

        /* */
        formatMonetario(vlaor){ 
            return this.$helper.formatMonetario(vlaor);  
        },        

        /* */
        getListaCriteriosDificuldades(){
                this.$api.orcamento.creiteriosdificuldades.listaCriterios({}).then((response) => {
                    if(response.status == 200){
                        this.listaCriterios = response.data;
                        this.$nextTick(() => { 
                            this.getListaAmbientes();
                        });
                    }
                })
                .catch(error => {
                    console.log(error);
                });  
        },        


        /* */
        chnageCriterioDificuldade(objComp,opcaoCriterioDif){

            this.$nextTick(() => { 
                if(this.$root.isUsuarioValido())
                {      
                    console.log(objComp,opcaoCriterioDif);
                    var obj ={
                                    id:opcaoCriterioDif.id, 
                                    criterioDificuldadeOpcao:{id: opcaoCriterioDif.id_opcao}, 
                                    criterioDificuldade:{id: opcaoCriterioDif.criterioDificuldade.id},
                                    orcamentoAmbiente:{id:objComp.id}
                            };

                    
                    this.$api.orcamento.insertAmbienteCriterioDificuldade(obj).then((response) => {
                        if(response.status != 200){
                            console.log("erro")
                        }
                    }); 
                }
            });

        }


    }, 

    /* */
    mounted(){        
        this.getListaCriteriosDificuldades();
    } ,  
        


}

</script>