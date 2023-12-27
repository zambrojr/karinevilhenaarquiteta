<template>

            <b-table small
                    class="small"
                     show-empty
                     selectable
                     no-local-sorting
                     select-mode="single"
                     @row-selected="onRowSelected"
                     bordered
                     :sticky-header="true"
                     :no-border-collapse="false"
                     responsive :fields="fields" :items="itens_tabela">
                <template v-slot:empty>
                    Nenhum registro encontrado para os filtros informados
                </template>
            </b-table>


</template>

<script>



export default {
    
    /* */
    components: {      
     
    }, 

    /* */
    props: {

        fields:{type:Array, default: function(){ return []} },
        url:{type:String}
        
    },   

    /* */
    data() {
        return {
            obj:{},
            itens_tabela:[],
        }
    },


    computed:{
      
    },


    /* */
    methods: {

        /* */
        onRowSelected(items) {
            this.$emit('selected', items);
        },


        /* */
        getLista(){
            this.$api.utils.getGenericList(this.url).then((response) => {
                if(response.status == 200){
                    this.itens_tabela    =   response.data
                }
            });  
        },
       
        
      
    },

    /* */
    mounted(){
        this.getLista();
    } ,  
        
}
</script>

