<template>

  
 
            <div>
                

                <div class="row pt-4">
                    <div class="col">
                        <b-form-group id="fieldset-horizontal" label-cols-sm="4" label-cols-lg="3" label="Nome" label-for="nome" >                        
                            <b-form-input type="text" id="nome" v-model="usuario.pessoa.nome" :state="!this.$v.$dirty || this.$v.usuario.pessoa.nome.required" placeholder="Informe o seu nome" trim></b-form-input>
                            <b-form-invalid-feedback id="input-live-feedback">Informe o seu nome</b-form-invalid-feedback>
                        </b-form-group>
                    </div>
                </div>       

                <div class="row">
                    <div class="col">
                        <b-form-group id="fieldset-horizontal" label-cols-sm="4" label-cols-lg="3" label="Sobrenome" label-for="sobrenome" >                        
                            <b-form-input type="text" id="sobrenome" v-model="usuario.pessoa.sobrenome" :state="!this.$v.$dirty || this.$v.usuario.pessoa.sobrenome.required" placeholder="Informe o seu sobrenome" trim></b-form-input>
                            <b-form-invalid-feedback id="input-live-feedback">Informe o seu sobrenome</b-form-invalid-feedback>
                        </b-form-group>
                    </div>
                </div>       

                <div class="row">
                    <div class="col">
                            <b-form-group id="fieldset-horizontal" label-cols-sm="4" label-cols-lg="3" label="Email" label-for="email" >                        
                                <b-form-input @input="emailCadastrado=false" type="email" placeholder="Informe o seu email" id="email" v-model="usuario.pessoa.email" :state="!this.$v.$dirty || this.$v.usuario.email.required" trim></b-form-input>
                                <b-form-invalid-feedback id="input-live-feedback" :state="!this.$v.$dirty || this.$v.usuario.email.required">Informe o seu email</b-form-invalid-feedback>                             

                                <b-popover :disabled="!emailCadastrado" :show="emailCadastrado" target="email" placement="top" variant="warning">
                                Email já cadastrado. Por favor, insira outro.
                                </b-popover>

                            </b-form-group>
                    </div>      
                </div>       

                <div class="row">                                  
                    <div class="col">
                        <b-form-group id="fieldset-horizontal" label-cols-sm="4" label-cols-lg="3" label="Telefone" label-for="telefone" >                        
                        <b-form-input v-mask="['(##) ####-####', '(##) #####-####']" id="telefone" placeholder="Informe o seu telefone" :state="!this.$v.$dirty || this.$v.usuario.pessoa.fone.required" class="form-control form-control-sm" v-model="usuario.pessoa.fone" />
                        <b-form-invalid-feedback id="input-live-feedback">Informe o seu telefone</b-form-invalid-feedback>                                                           
                        </b-form-group>
                    </div>
                </div>


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
                email:null,
                password:null,
                pessoa:{
                    sobrenome:null,
                    nome:null,   
                    nascimento:null,                    
                    fone:null,
                }
            }
                           
        }
    },

    /* */
    computed: {

    },


    /* */
    methods: {
 

        getUser(){
            return this.usuario;
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
            email:{required},
            password:{required},
            pessoa:{
                sobrenome: {required},
                nome: {required},
                nascimento:{required},          
                fone:{required},
            }            
        },
    } 


        
}
</script>
