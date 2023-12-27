<template>

<div>

        <b-table class="small text-left" responsive :fields="fields"  :small=true :striped=true :items="listaAcessorios">

        <template v-slot:cell(vlr_sugerido)="data">
            {{formatMonetario(data.item.vlr_sugerido)}}
        </template>



        <template v-slot:cell(acoes)="data">
            <b-icon icon="pencil-square" v-b-tooltip.hover title="Editar"></b-icon>
            <span class="pl-1 fa fa-credit-card" style="font-size: large;" @click="liquidarLancamento(data.item)" v-b-tooltip.hover title="..."></span>
            <span class="pl-1 fa fa-certificate" style="font-size: large;" @click="liquidarLancamento(data.item)" v-b-tooltip.hover title="..."></span>
            <span class="pl-1 fa fa-comments" style="font-size: large;" @click="liquidarLancamento(data.item)" v-b-tooltip.hover title="..."></span>
            <span class="pl-1 fa fa-skype" style="font-size: large;" @click="liquidarLancamento(data.item)" v-b-tooltip.hover title="..."></span>
            <span class="pl-1 fa fa-id-card" style="font-size: large;" @click="liquidarLancamento(data.item)" v-b-tooltip.hover title="..."></span>
            <span class="pl-1 fa fa-edit" style="font-size: large;" @click="liquidarLancamento(data.item)" v-b-tooltip.hover title="..."></span>
            <!--v-bind:class="{ 'fa-disabled': data.item.status!='P' }"-->
        </template>
        
        </b-table>

</div>

 



</template>
   

<script>




export default {

    /* */
    components: {

    }, 

    /* */
    watch: {

    },

    /* */
    props: {
    },            

    /* */
    data() {
            return {
                listaAcessorios:[],

                fields: [
                          { key: 'id', label: 'ID',  sortable: true },
                          { key: 'descricao', label: 'Descrição',  sortable: true },
                          { key: 'vlr_sugerido', label: 'Valor Sugerido',  sortable: true  },
                          { key: 'acoes', label: ' ', sortable: false },   
                  ],                
            }
        },

    /* */
	computed:{
	},

    /* */
    methods: { 

        /* */
        formatMonetario(vlaor){ 
            return this.$helper.formatMonetario(vlaor);  
        },        

        /* */
        editaPessoa(objPessoa){

            this.$emit('editaPessoa', objPessoa);

        },

        /* */
        getlistaAcessorios()
        {
            this.$api.utils.getGenericList("/acessorios/lista")
            .then(response => { 
                    this.listaAcessorios    =response.data;
            }) 
            .catch();
        },

 
    },

    /* */
    created() {
     
    },    

    /* */
    mounted(){

        this.getlistaAcessorios();

	}        
        
}
</script>