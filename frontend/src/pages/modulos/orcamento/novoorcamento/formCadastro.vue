<template>

  
 
            <div>
                <b-overlay :show="showManoWanderley" rounded="sm">

                <div class="row pt-4">
                    <div class="col">
                        <b-form-group id="fieldset-horizontal" label-cols-sm="4" label-cols-lg="3" label="Nome" label-for="nome" >                        
                            <b-form-input type="text" id="nome" v-model="usuario.pessoa.nome" placeholder="Informe o seu nome" trim></b-form-input>
                            <b-form-invalid-feedback id="input-live-feedback">Informe o seu nome</b-form-invalid-feedback>
                        </b-form-group>
                    </div>
                </div>       

                <div class="row">
                    <div class="col">
                        <b-form-group id="fieldset-horizontal" label-cols-sm="4" label-cols-lg="3" label="Sobrenome" label-for="sobrenome" >                        
                            <b-form-input type="text" id="sobrenome" v-model="usuario.pessoa.sobrenome" placeholder="Informe o seu sobrenome" trim></b-form-input>
                            <b-form-invalid-feedback id="input-live-feedback">Informe o seu sobrenome</b-form-invalid-feedback>
                        </b-form-group>
                    </div>
                </div>       

                <div class="row">
                    <div class="col">
                            <b-form-group id="fieldset-horizontal" label-cols-sm="4" label-cols-lg="3" label="Email" label-for="email" >                        
                                <b-form-input @input="emailCadastrado=false" type="email" placeholder="Informe o seu email" id="email" v-model="usuario.pessoa.email" trim></b-form-input>
                                <b-form-invalid-feedback id="input-live-feedback">Informe o seu email</b-form-invalid-feedback>                             

                                <b-popover :disabled="!emailCadastrado" :show="emailCadastrado" target="email" placement="top" variant="warning">
                                Email já cadastrado. Por favor, insira outro.
                                </b-popover>

                            </b-form-group>
                    </div>      
                </div>       

                <div class="row">                                  
                    <div class="col">
                        <b-form-group id="fieldset-horizontal" label-cols-sm="4" label-cols-lg="3" label="Telefone" label-for="telefone" >                        
                        <b-form-input v-mask="['(##) ####-####', '(##) #####-####']" id="telefone" placeholder="Informe o seu telefone" class="form-control form-control-sm" v-model="usuario.pessoa.fone" />
                        <b-form-invalid-feedback id="input-live-feedback">Informe o seu telefone</b-form-invalid-feedback>                                                           
                        </b-form-group>
                    </div>
                </div>


                <div class="row">                                  
                    <div class="col text-center">
                        <b-button :disabled="$v.$invalid" @click="addOrcamento()" >Continuar&nbsp;<b-icon icon="arrow-right-short"></b-icon></b-button>                    
                    </div>

                </div>                

            </b-overlay>
            </div>
    




</template>

  
<script>



import {mask} from 'vue-the-mask'
import { required } from 'vuelidate/lib/validators'
//import Password from 'vue-password-strength-meter'
export default {

    /* */
    directives: {mask},

    /* */
    components: { 
        //Password 
    }, 

    /* */
    props: {
        idAutoavaliacao:null
    },            

    /* */
    data() {
        return {
            secureLength:5,
            showManoWanderley: false,
            emailCadastrado:false,
            usuario:{
                pessoa:{
                    sobrenome:'das couves',
                    nome:"jose",   
                    email:"ze@dascouves.com",                    
                    fone:"31991954373",
                }
            }
                           
        }
    },

    /* */
    computed: {

    },


    /* */
    methods: {
 

        addOrcamento(){

                    this.showManoWanderley = true;
                    var pessoa = this.usuario.pessoa;
                    pessoa.tipo='F';
                    pessoa.id_pessoa=null;

                    this.$api.orcamento.novoOrcamento(pessoa)
                    .then(response => { 
                        this.showManoWanderley = false;
                        if(response.status==200){
                            this.$router.push({name: 'orcamento_painelproposta', query: {proposta:  response.data.proposta}})
                        }

                    }) 
                    .catch(error => {
                       console.log(error);
                    }); 
        },

    },


    /* */
    created() {
     
    },    

    /* */
    mounted(){
        console.log("dsfds");
    },



    validations: {
        usuario: {
            pessoa:{
                sobrenome: {required},
                nome: {required},
                email:{required},          
                fone:{required},
            }            
        },
    } 


        
}
</script>
