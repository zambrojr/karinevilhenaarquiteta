<template>

<section>
    <page-title nome="Produtos" subTitulo="Cadastro de Produtos"></page-title>

       <b-container>

                    <section>
                        <div class="row" ref="container">
                            <box-padrao isOpen>
                                <lista-pessoa ref="listaAcessorios" v-on:editaPessoa="editaPessoa"></lista-pessoa>    
                                <div class="row">
                                    <div class="col">
                                        <b-button variant="outline-secondary" @click="novaPessoa">Cadastrar Produto</b-button>
                                    </div>
                                </div>                                
                            </box-padrao>
                        </div>
                    </section>
                 
        </b-container>            
   
        <b-modal v-model="modalFormPessoa" size="xl" :no-close-on-backdrop=true ok-title="Salvar" cancel-title="Cancelar" @ok="salvarPessoa" >
            <form-pessoa ref="formPessoa" :idPessoa="id_pessoa" v-on:pessoaSalva="listaAcessorios"></form-pessoa>  
        </b-modal>

</section>


</template>

<script>


import formPessoa from './formulario.vue';
import listaAcessorios from './lista.vue';

export default {

    /* */
    components: {
        'form-pessoa' :formPessoa,
        'lista-pessoa' :listaAcessorios,
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
                modalFormPessoa:false,
                id_pessoa:null
            }
        },

    /* */
	computed:{
	},

    /* */
    methods: {

        /* */
        editaPessoa(objPessoa){
            this.id_pessoa = objPessoa.id_pessoa;
            this.modalFormPessoa = true;
        },

        /* */
        salvarPessoa(evt){
            evt.preventDefault();
            this.$refs.formPessoa.savePessoa();
        },

        /* */
        listaAcessorios(){
            this.$refs.listaAcessorios.getlistaAcessorios();
        },        

        /*  */
        novaPessoa(){
            this.id_pessoa = null;
            this.modalFormPessoa = true;
        }
 
    },

    /* */
    created() {
     
    },    

    /* */
    mounted(){



	}        
        
}
</script>