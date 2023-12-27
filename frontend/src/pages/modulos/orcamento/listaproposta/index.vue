<template>

<section>
    <page-title nome="Orçamento" subTitulo="Requisição de orçamentos"></page-title>

       <b-container>

                    <section>
                        <div class="row" ref="container">
                            <box-padrao isOpen>
        
                                <b-table  stacked="md" selectable select-mode="single" @row-selected="onRowSelected" class="small text-left relatorio" responsive :fields="fields"  :small=true :striped=true :items="listaOrcamentos">
                                    <template v-slot:cell(nome)="data">
                                        {{nomePessoa(data.item.pessoa)}}
                                    </template>
                                    <template v-slot:cell(email)="data">
                                        {{emailPessoa(data.item.pessoa)}}
                                    </template>                            
                                    <template v-slot:cell(data)="data">
                                        {{formataDataHora(data.item.data)}}
                                    </template>         
                                    <template v-slot:cell(status)="data">
                                         {{descricaoStatus(data.item.status)}}                                        
                                    </template>                                                
                                </b-table>

                            </box-padrao>
                        </div>
                    </section>        
                                
 


        </b-container>

</section>

</template>

<script>

export default {
    
    /* */
    components: {      
    }, 

    /* */
    data() {
        return {
            listaOrcamentos:[],

                fields: [
                          { key: 'nome', label: 'Nome',  sortable: true },                          
                          { key: 'email', label: 'Email',  sortable: true },                          
                          { key: 'data', label: 'Data',  sortable: true },           
                          { key: 'status', label: 'Status',  sortable: true },           
                  ],  

        }
    },

    /* */
    methods: {

        descricaoStatus(status){
				switch(status){
					case 0: return "Aberto"
                    case 1: return "Fechado(Dentro da validade)"
                }
        },

        getListaOrcamentos(){
            this.$api.orcamento.listaOrcamentos({}).then((response) => {
                if(response.status == 200){
                    this.listaOrcamentos    =   [];
                    
                    response.data.forEach(x=>{
                        this.listaOrcamentos.push(x);                   
                    })
                }
            })
            .catch(error => {
                console.log(error);
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
        onRowSelected(items) {
            var obj = items[0];
            this.$router.push({name: 'orcamento_painelproposta', query: {proposta:  obj.proposta}})
        },
      
    },

    /* */
    mounted(){
        //this.$bus.on('readyListaModulos', this.listouModulos);

        this.getListaOrcamentos();
    } ,  
        
}
</script>

