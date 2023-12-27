<template>

<div>

        <b-table class="small text-left" responsive :fields="fields"  :small=true :striped=true :items="listaDescontos">


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
                listaDescontos:[],

                fields: [
                          { key: 'id', label: 'ID',  sortable: true },
                          { key: 'descricao', label: 'Descrição',  sortable: true },
                          { key: 'percent_sugerido', label: 'Porcentagem',  sortable: true },
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
        getlistaDescontos()
        {
            this.$api.utils.getGenericList("/orcamento/getlistadescontosproposta")
            .then(response => { 
                    this.listaDescontos    =response.data;
            }) 
            .catch();
        },

 
    },

    /* */
    created() {
     
    },    

    /* */
    mounted(){

        this.getlistaDescontos();

	}        
        
}
</script>