<template>

<div><strong>Escolha opção no menu</strong></div>


</template>

<script>



export default {
    
    /* */
    components: {      
     
    }, 


    /* */
    props: {
        
    },   

    /* */
    data() {
        return {
            objOrcamento:{},
            listaAcessorios:[],
            options:[{value:0, text:"0"},{value:1, text:"1" },{value:2, text:"2" },{value:3, text:"3" },{value:4, text:"4" },{value:5, text:"5" }],

            fieldsParams: [
                    { key: 'nome', label: 'Nome',  sortable: true },
                    { key: 'valor', label: 'Valor padrão',  sortable: true },
                    { key: 'qtd', label: 'Qtd',  sortable: true },
                    { key: 'vlr_total', label: 'Total',  sortable: true },
            ],
            
            cotacaoPeso:{}            

        }
    },


    computed:{
        valorTotalParametros(){
            return 3;//_.sumBy(this.objOrcamento.orcamentoPessoaParametrosPreco, function(o) { return o.vlr_total; });
        },

        valorTotalCriterios(){
            var totalPeso =3;// _.sumBy(this.listaCriterios, function(o) { var opcao = o.criterioDificuldadeOpcao.find(x=>{return x.id == o.opcao_id}); return opcao.peso; });
            return totalPeso * this.cotacaoPeso.valor_peso;
        },

        valorTotalOrcamento(){
            return this.valorTotalParametros + this.valorTotalCriterios
        },

        titleParamsPrecificacao(){
            return "Parâmetros Precificação ("+this.formatReal(this.valorTotalParametros)+")";
        },


        titleCriteriosDificuldade(){
            return "Critérios de Dificuldade ("+this.formatReal(this.valorTotalCriterios)+")";
        }        
    },


    /* */
    methods: {

        /* */
        lblProporcaoComodo(comodo,idx){
            var linha = parseInt(idx)+1;
            return comodo+" "+linha;
        },

        /* */
        recalcVlrParamPreco(objParam){
            objParam.vlr_total = objParam.qtd * objParam.parametrosPrecificacao.valor
        },

        /* */
        getListaCriteriosDificuldades(){
                this.$api.orcamento.creiteriosdificuldades.listaCriterios({}).then((response) => {
                    if(response.status == 200){

                        this.listaCriterios = [];
                            response.data.forEach(xcriterio=>{

                            var opcaoSelec = this.objOrcamento.orcamentoPessoaCriterioDificuldade.find(elem=>{return elem.criterioDificuldade.id == xcriterio.id})

                            this.$set(xcriterio, "opcao_id",opcaoSelec.criterioDificuldadeOpcao.id);
                            this.listaCriterios.push(xcriterio);
                        })

                    }
                })
                .catch(error => {
                    console.log(error);
                });  
        },        

        /* */
        getListaOrcamentos(){
            this.$api.orcamento.getOrcamento({id:15}).then((response) => {
            //this.$api.orcamento.getOrcamento({id:this.id_orcamento}).then((response) => {
                if(response.status == 200){
                    this.objOrcamento    =   response.data

                    this.objOrcamento.orcamentoPessoaAmbiente.forEach(ambiente=>{
                        this.$set(ambiente, "lista", []);
                    });

                    this.$nextTick(function () {
                        this.getListaCriteriosDificuldades();
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


        
        
      
    },

    /* */
    mounted(){
        this.getListaOrcamentos();
        this.getCotacaoPesoAtual();
    } ,  
        
}
</script>

