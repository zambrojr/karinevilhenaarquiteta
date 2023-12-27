<template>

<section>
    <page-title nome="Orçamento"  subTitulo="Calculadora de preço de orçamento"></page-title>


       <b-container>

            <section>
                <div class="row dados" ref="container">
                    <box-padrao isOpen>
                        <div style="width:100%">
                        <b-row v-if="pessoaCarregada">
                            <div class="col-md-4">
                                <label>Nome completo</label>
                                <div><strong>{{objOrcamento.pessoa.nomeCompleto}}</strong></div>
                            </div>
                            <div class="col-md-3">
                                <label>Email</label>
                                <div>{{objOrcamento.pessoa.email}}</div>
                            </div>
                            <div class="col-md-3">
                                <label>Telefone</label>
                                <div>{{objOrcamento.pessoa.fone}}</div>
                            </div> 
                        </b-row>  

                        <b-row class="pt-5">
                            <div class="container">
                            <b-tabs pills fill>

                                <b-tab active>
                                    <template v-slot:title>
                                            <strong>{{titleAmbientes}}</strong>
                                    </template>
                                    <section>
                                            <panel-ambientes v-if="ambientesCarregados" v-on:valorambientes="valorambientes" :orcamento_id="id_orcamento" v-model="objOrcamento.orcamentoAmbiente"></panel-ambientes>
                                    </section>
                                </b-tab>

                                <b-tab>
                                    <template v-slot:title>
                                        <strong>{{titleCriteriosDificuldade}}</strong>
                                    </template>                                    
                                    <b-row class="pt-5" v-for="objDificuldade in listaCriterios" :key="objDificuldade.id" >
                                        <b-col>
                                            <b-form-group label-cols-sm="4" size="lg" :label="objDificuldade.criterioDificuldade.nome" id="fieldset-1" label-for="input-1" >
                                                <b-form-radio-group @change="chnageCriterioDificuldade(objDificuldade)" v-model="objDificuldade.id_opcao">
                                                    <b-col style="display: grid;"> 
                                                        <b-form-radio size="lg" v-for="(opcao) in objDificuldade.listaCcriterioDificuldadeOpcao" :value="opcao.id" :key="opcao.id" >{{opcao.nome}} (peso {{opcao.peso}})<p v-if="opcao.descricao">{{opcao.descricao}}</p></b-form-radio>
                                                    </b-col>    
                                                </b-form-radio-group>
                                            </b-form-group>
                                        </b-col>   
                                    </b-row>               
                                </b-tab>

                                <b-tab>
                                    <template v-slot:title>
                                        <strong>{{titleParamsPrecificacao}}</strong>
                                    </template>                                    


                                    <section>
                                        <b-row class="pt-5" >
                                            <b-col style="width:100%"> 
                                                <b-table stacked="md" selectable select-mode="single"  class="small text-left relatorio" :fields="fieldsParams"  :small=true :striped=true :items="objOrcamento.orcamentoParametrosPreco">
                                                    <template v-slot:cell(qtd)="row">
                                                        <input type="number" class="inputPersonal inputPersonalMinsize" v-model="row.item.qtd" @input="recalcVlrParamPreco(row.item)">
                                                    </template>            

                                                    <template v-slot:cell(valor)="row">
                                                        {{formatMonetario(row.item.parametrosPrecificacao.valor)}}
                                                    </template>      

                                                    <template v-slot:cell(vlr_total)="row">
                                                        <money class="inputPersonal inputPersonalMinsize" @input="salvaValorParamPreco(row.item)" v-model="row.item.vlr_total" v-bind="money"></money>
                                                    </template>       

                                                    <template v-slot:cell(nome)="row">
                                                        {{row.item.parametrosPrecificacao.nome}}
                                                    </template>                                                       

                                                </b-table>
                                             </b-col> 
                                        </b-row>               
                                     </section>
                               
                                </b-tab>


                                <b-tab>
                                    
                                    <template v-slot:title>
                                        <strong>Total</strong>
                                    </template>      

                                    <b-overlay :show="showManoWanderley" rounded="sm">
                                    <div class="container text-right pt-5">

                                        <b-table-simple  hover small caption-top responsive>


                                            <b-tr>
                                                <b-td>Critérios de Dificuldade (Valor peso {{formatMonetario(cotacaoPeso.valor_peso)}})</b-td>
                                                <b-td class="text-right">{{formatMonetario(valorTotalCriterios)}}</b-td>
                                            </b-tr>
                                            <b-tr>
                                                <b-td>Produtos</b-td>
                                                <b-td class="text-right">{{formatMonetario(valorTotalAmbientes)}}</b-td>
                                            </b-tr>                                    
                                            <b-tr>
                                                <b-td>Parâmetros Precificação</b-td>
                                                <b-td class="text-right">{{formatMonetario(valorTotalParametros)}}</b-td>
                                            </b-tr>     
                                            <b-tr>
                                                <b-th>Valor</b-th>     
                                                <b-th class="text-right">{{formatMonetario(subTotalOrcamento)}}</b-th>     
                                            </b-tr>                                                                                                                          
                                            <b-tr v-for="objCriterio in listadescontosproposta" :key="objCriterio.id" >
                                                <b-td>
                                                    <span v-if="objCriterio.flg_obrigatorio">{{objCriterio.descricao}} ({{objCriterio.percent_sugerido}}%)</span>
                                                    <b-form-checkbox v-else  name="check-button" v-model="objCriterio.checked" switch @change="changeDesconto(objCriterio)">
                                                        {{objCriterio.descricao}} ({{objCriterio.percent_sugerido}}%)
                                                    </b-form-checkbox>                                                        
                                                </b-td>
                                                <b-td class="text-right">{{formatMonetario(calculaDesconto(objCriterio))}}</b-td>
                                            </b-tr>                      
                                            <b-tr>
                                                <b-th>Lucro Líquido</b-th>     
                                                <b-th class="text-right">{{formatMonetario(totalOrcamento())}}</b-th>     
                                            </b-tr>  
                                            <b-tr>   
                                                <b-th colspan="2" class="text-right"><b-button v-if="objOrcamento.status==0" variant="outline-secondary" @click="fecharOrcamento">Fechar e enviar Proposta</b-button></b-th>     
                                            </b-tr>                                                                                                                                                      
                                        </b-table-simple>
                                    </div>
                                    </b-overlay>
                                </b-tab>


                            </b-tabs>
                            </div>
                        </b-row>
                 

                        </div>
                    </box-padrao>
                </div>
            </section>        

        </b-container>


</section>

</template>

<script>

/* global _ */

import panelAmbiente from "./ambientes"
import { Money } from "v-money";

export default {
    
    /* */
    components: {      
        "panel-ambientes": panelAmbiente,
        Money
    }, 


    /* */
    props: {
    },   

    /* */
    data() {
        return {
            objOrcamento:{},
            showManoWanderley:false,
            listaCriterios:[],
            listaParametrosPrecificacao:[],
            options:[{value:0, text:"0"},{value:1, text:"1" },{value:2, text:"2" },{value:3, text:"3" },{value:4, text:"4" },{value:5, text:"5" }],

            fieldsParams: [
                    { key: 'nome', label: 'Nome',  sortable: true },
                    { key: 'valor', label: 'Valor padrão',  sortable: true },
                    { key: 'qtd', label: 'Qtd',  sortable: true },
                    { key: 'vlr_total', label: 'Total',  sortable: true },
            ],
            
            cotacaoPeso:{},
            id_orcamento:null,
            str_proposta:null,
            valorTotalAmbientes:0,
            listadescontosproposta:[],

            money: {
                decimal: ',',
                thousands: '.',
                prefix: '',
                suffix: '',
                precision: 2,
                masked: false /* doesn't work with directive */
            },                

        }
    },


    computed:{

        ambientesCarregados(){
                return Object.prototype.hasOwnProperty.call(this.objOrcamento,"orcamentoAmbiente")
        },

        valorTotalParametros(){
            return _.sumBy(this.objOrcamento.orcamentoParametrosPreco, function(o) { return o.vlr_total; });
        },

        valorTotalCriterios(){
            var totalPeso = _.sumBy(this.listaCriterios, function(opcao) {return Object.prototype.hasOwnProperty.call(opcao.criterioDificuldadeOpcao,"peso") ? opcao.criterioDificuldadeOpcao.peso : 0; });
            return totalPeso * this.cotacaoPeso.valor_peso;
        },

        subTotalOrcamento(){
            return this.valorTotalParametros + this.valorTotalCriterios + this.valorTotalAmbientes
        },


        pessoaCarregada(){
            return Object.prototype.hasOwnProperty.call(this.objOrcamento,"pessoa")
        },


        titleParamsPrecificacao(){
            return "Parâmetros Precificação ("+this.formatMonetario(this.valorTotalParametros)+")";
        },


        titleCriteriosDificuldade(){
            return "Critérios de Dificuldade ("+this.formatMonetario(this.valorTotalCriterios)+")";
        }        ,


        titleAmbientes(){
            return "Ambientes ("+this.formatMonetario(this.valorTotalAmbientes)+")";
             
        }

    },


    /* */
    methods: {

        /* */
        changeDesconto(objCriterio){
                if(this.$root.isUsuarioValido()){


                    this.$api.orcamento.creiteriosdificuldades.changeDescontoOrcamento({orcamento:{id:this.id_orcamento}, descontoProposta:{id:objCriterio.id}}).then((response) => {
                        if(response.status != 200){
                            console.log("erro")
                        }
                    });

                }
        },

        /* */
        fecharOrcamento(){


            this.$bvModal.msgBoxConfirm('Deseja fechar o Orçamento e envia-lo para '+this.objOrcamento.pessoa.nomeCompleto+"?", {
                    title: 'Orçamento',
                    okTitle: 'Fechar',
                    cancelTitle: 'Cancelar',
                    centered: true
                    })
                    .then(value => {
                        if(value){

                            var orcamento = {id:this.objOrcamento.id};
                            this.showManoWanderley = true;
                            this.$api.orcamento.fecharOrcamento(orcamento).then((response) => {
                                if(response.status == 200){
                                    this.openProposta();
                                    console.log(response.data);
                                }
                                this.showManoWanderley = false;
                            })
                            .catch(error => {
                                console.log(error);
                                this.showManoWanderley = false;
                            }); 

                        }
            })

        },

        /* */
        valorambientes(vlr){
            this.valorTotalAmbientes = vlr;
        },

        /* */
        lblProporcaoComodo(comodo,idx){
            var linha = parseInt(idx)+1;
            return comodo+" "+linha;
        },


        /* */
        recalcVlrParamPreco: _.debounce(function (objParam) {
                objParam.vlr_total = objParam.qtd * objParam.parametrosPrecificacao.valor;
                this.saveVlrParamPreco(objParam);
        }, 500),

        /* */
        salvaValorParamPreco: _.debounce(function (objParam) {
                this.saveVlrParamPreco(objParam);
        }, 500),


        /* */
        saveVlrParamPreco(objParam){
            if(this.$root.isUsuarioValido()){
                this.$api.orcamento.updateOrcamentoParametrosPreco(objParam).then((response) => {
                    if(response.status != 200){
                        console.log("erro");
                    }
                }); 
            }
        },


        calculaDesconto(objDesconto){
            return objDesconto.flg_obrigatorio || objDesconto.checked ? this.subTotalOrcamento * objDesconto.percent_sugerido / 100 : 0;
        },


        totalOrcamento(){
            var tis = this;
            return this.subTotalOrcamento - _.sumBy(this.listadescontosproposta, function(o) { return tis.calculaDesconto(o); });
        },
        

        /* */
        getListaCriteriosDificuldades(){
                this.$api.orcamento.creiteriosdificuldades.listaCriterios({}).then((response) => {
                    if(response.status == 200){

                        this.listaCriterios = [];
                            response.data.forEach(xcriterio=>{

                            var opcaoSelec = this.objOrcamento.orcamentoCriterioDificuldade.find(elem=>{return elem.criterioDificuldade.id == xcriterio.id})
                            if(opcaoSelec==undefined){
                                opcaoSelec = {id:null, criterioDificuldade: xcriterio, criterioDificuldadeOpcao:{}, id_opcao:null}
                            } else {
                                this.$set(opcaoSelec, "id_opcao",opcaoSelec.criterioDificuldadeOpcao.id);
                            }
                            this.$set(opcaoSelec, "listaCcriterioDificuldadeOpcao", xcriterio.criterioDificuldadeOpcao);
                            //var orcamentoCriterioDificuldade = this.objOrcamento.orcamentoCriterioDificuldade.find(elem=>{return elem.criterioDificuldade.id == xcriterio.id})

                            this.listaCriterios.push(opcaoSelec);
                        })

                    }
                })
                .catch(error => {
                    console.log(error);
                });  
        },        

        /* */
        chnageCriterioDificuldade(opcaoCriterioDif){

            this.$nextTick(() => { 
                if(this.$root.isUsuarioValido()){
                    if(opcaoCriterioDif.id == null){
                        this.$api.orcamento.insertOrcamentoCriterioDificuldade(
                            {
                                id:null, 
                                criterioDificuldadeOpcao:{id: opcaoCriterioDif.id_opcao}, 
                                criterioDificuldade:{id: opcaoCriterioDif.criterioDificuldade.id},
                                orcamento:{id:this.objOrcamento.id}
                            }).then((response) => {
                            if(response.status == 200){
                                opcaoCriterioDif.id = response.data.id
                            }
                        }); 
                    } else {
                        this.$api.orcamento.updateOrcamentoCriterioDificuldade({id:opcaoCriterioDif.id, criterioDificuldadeOpcao:{id: opcaoCriterioDif.id_opcao}}).then((response) => {
                            if(response.status != 200){
                                console.log(response);
                            }
                        }); 
                    }
                }

                var objOpcao    =   opcaoCriterioDif.listaCcriterioDificuldadeOpcao.find(x=>{ return x.id == opcaoCriterioDif.id_opcao })
                this.$set(opcaoCriterioDif, "criterioDificuldadeOpcao", objOpcao);

            });
        },

        /* */
        getOrcamento(){
            //this.$api.orcamento.getOrcamento({id:15}).then((response) => {
            this.$api.orcamento.getOrcamento({proposta:this.str_proposta}).then((response) => {
                if(response.status == 200){
                    this.objOrcamento    =   response.data
                    this.id_orcamento = this.objOrcamento.id;

                    this.objOrcamento.orcamentoAmbiente.forEach(ambiente=>{
                        this.$set(ambiente, "lista", []);
                    });

                    this.$nextTick(function () {
                        this.getListaCriteriosDificuldades();
                        this.getlistadescontosproposta(); 
                    });
                }
            });  
        },

        /* */
        getCotacaoPesoAtual(){
            this.$api.orcamento.getCotacaoPesoAtual({}).then((response) => {
                if(response.status == 200){
                    this.cotacaoPeso    =   response.data
                }
            });  
        },        

        /* */
        getlistadescontosproposta(){
            this.$api.orcamento.getlistadescontosproposta({}).then((response) => {
                if(response.status == 200){
                    response.data.forEach(x=>{
                        var temp = this.objOrcamento.orcamentoDescontoAprovado.find(y=>{ return y.descontoProposta.id == x.id})
                        this.$set(x, "checked", temp!=undefined);
                        this.listadescontosproposta.push(x);
                    })                    
                }
            });  
        },          

        /* */
        nomePessoa(pessoa){
            return pessoa.nomeCompleto
        },

        /* */
        emailPessoa(pessoa){
            return pessoa.email
        },

        /* */ 
        formataDataHora(vlaor){ 
            return this.$helper.dataHoraBrasil(vlaor);  
        },

        /* */
        formatReal(vlaor){ 
            return this.$helper.formatReal(vlaor);  
        },

        /* */
        formatMonetario(vlaor){ 
            return this.$helper.formatMonetario(vlaor);  
        },
        
        
        /* */
        onRowSelected(items) {
            console.log(items)
        },


        /* */
        par(indOrc){
            return indOrc%2==0
        },


        /* */
        balaoBoasVindas(){
            if(this.$root.isUsuarioCarregado() && !this.$root.isUsuarioValido()){
            var textoBalao = "Este é o painel onde voce podera bla bla bla...";
            textoBalao += "<br><br>Em <strong>Ambientes</strong> voce poderá bla bla bla...";
            textoBalao += "<br><br>Em <strong>Parâmetros precificação</strong> voce poderá bla bla bla...";
            this.$root.showTips(textoBalao, []);   
            }
        } ,


        openProposta(){
            this.getOrcamento();
            this.getCotacaoPesoAtual();             
        }
        
      
    },



    /* */
    created() {
       this.$bus.on('userLoaded', this.balaoBoasVindas);
    },  

    /* */
    mounted(){
        if(this.$route.query.proposta){
            this.balaoBoasVindas();
            this.str_proposta = this.$route.query.proposta;
            this.openProposta();
        } else {
            this.$router.push({name: 'orcamento_listaproposta'})
        }
    } ,  
        
}
</script>

