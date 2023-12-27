<template>
    <div>
<div class="sec-title">
				<h2>Entre em Contato</h2>
				<div class="text">Do you have anything in your mind to let us know?  Kindly don't delay to connect to us by means of our contact form.</div>
			</div>

        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <b-form-group id="input-group-1" :state="!this.$v.$dirty || this.$v.faleConosco.nome.required" invalid-feedback="Por favor, informe o seu nome" label="Nome" label-for="input-1" >
                    <input type="text" name="nome" v-model="faleConosco.nome"  class="form-control" />
                </b-form-group>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <b-form-group id="input-group-1" :state="!this.$v.$dirty || this.$v.faleConosco.email.required" invalid-feedback="Por favor, informe o seu e-mail" label="Email" label-for="input-1" >
                    <input type="email" name="email" v-model="faleConosco.email" class="form-control" />
                </b-form-group>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <b-form-group id="input-group-1" :state="!this.$v.$dirty || this.$v.faleConosco.assunto.required" invalid-feedback="Por favor, informe o assunto" label="Assunto" label-for="input-1" >
                    <input type="text" name="assunto" v-model="faleConosco.assunto" class="form-control" />
                </b-form-group>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <b-form-group id="input-group-1" :state="!this.$v.$dirty || this.$v.faleConosco.telefone.required" invalid-feedback="Por favor, informe o seu telefone" label="Telefone" label-for="input-1" >
                    <the-mask :mask="['(##) ####-####', '(##) #####-####']" class="form-control form-control-sm" v-model="faleConosco.telefone"  />
                </b-form-group>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <b-form-group id="input-group-1" :state="!this.$v.$dirty || this.$v.faleConosco.texto.required" invalid-feedback="Por favor, insira uma mensagem" label="Mensagem" label-for="input-1" >
                    <textarea class="form-control" v-model="faleConosco.texto" name="message" />
                </b-form-group>
            </div>
            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                <div class="send-btn">
                    <button @click="submeter" class="theme-btn btn-style-one">Enviar</button>
                </div>
            </div>
        </div>

    </div>
</template>

<script>

import { required } from 'vuelidate/lib/validators'
import moment from 'moment'
import {mask} from 'vue-the-mask'

export default {
              

    /* */
    directives: {mask},

    /* */              
    components: {
            BFormGroup: () => import('bootstrap-vue').then(module => module.BFormGroup)
    },   

    /* */
    props: {
    
    },    

    /* */
    data() {
            return {
                faleConosco:{
                    nome:null,
                    email:null,
                    assunto:null,
                    telefone:null,
                    texto:null,
                    data_fale_conosco: moment().format('YYYY-MM-DD'),
                }

          }
    },

    watch: {
    },        
        
    methods: {
        /* */ 
        submeter()
        {
            
            this.$v.$touch();
            
            if(!this.$v.$invalid){

                this.$api.site.faleconosco(this.faleConosco)
                .then(() => { 
                        this.$bvToast.toast('Sua mensagem foi enviada com sucesso!', { title: 'Fale Conosco',   variant: 'success',    toaster:'b-toaster-bottom-center'    })      
                }) 
                .catch();  
            }
        },

    },
        
    /* */        
    computed: {
    },
    
    /* */    
    mounted() {

    },


    validations: {
        faleConosco:{
            nome:{required},
            email:{required},
            assunto:{required},
            telefone:{required},
            texto:{required},
        }
    }

     
}


</script>    
