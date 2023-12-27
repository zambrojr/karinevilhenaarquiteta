<template>

<div>
        <div class="form-horizontal">

                    <div class="row">
                        <div class="col">
                        
                            <div >

                                <div class="row">
                                    <b-form-group class="col"  label="RG:" :state="!this.$v.$dirty || this.$v.objPessoa.rg.required" invalid-feedback="Campo obrigatório" label-for="input-1" >
                                        <b-form-input  type="text" size="sm" v-model="objPessoa.rg" required placeholder="RG"></b-form-input>
                                    </b-form-group>
                                </div>

                                <div class="row">
                                    <b-form-group class="col" :state="!this.$v.$dirty || this.$v.objPessoa.nome.required" invalid-feedback="Campo obrigatório" label="Nome:" label-for="input-1" >
                                        <b-form-input  type="text" size="sm" v-model="objPessoa.nome" required placeholder="Nome"></b-form-input>
                                    </b-form-group>
                                </div>

                                <div class="row">
                                    <b-form-group class="col" :state="!this.$v.$dirty || this.$v.objPessoa.sobrenome.required" invalid-feedback="Campo obrigatório" label="Sobrenome:" label-for="input-1" >
                                        <b-form-input  type="text" size="sm" v-model="objPessoa.sobrenome" required placeholder="Sobrenome"></b-form-input>
                                    </b-form-group>                
                                </div>

                                <div class="row">
                                    <b-form-group class="col" :state="!this.$v.$dirty || this.$v.objPessoa.ocupacao.codigo.required" invalid-feedback="Campo obrigatório" label="Ocupação:" label-for="input-1" >
                                        <select-ocupacao v-model="objPessoa.ocupacao.codigo" :descricao="objPessoa.ocupacao.titulo"></select-ocupacao>
                                    </b-form-group>                
                                </div>


                            </div>

                            <div class="row">
                                <b-form-group class="col-lg-4" label="Telefone:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objPessoa.fone.required" invalid-feedback="Campo obrigatório">
                                        <the-mask :mask="['(##) ####-####', '(##) #####-####']" class="form-control form-control-sm" v-model="objPessoa.fone" />
                                </b-form-group>

                                <b-form-group class="col"  label="E-mail:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objPessoa.email.required" invalid-feedback="Campo obrigatório">
                                    <b-form-input  type="text" size="sm" v-model="objPessoa.email" required placeholder="E-mail"></b-form-input>
                                </b-form-group>
                            </div>           
                    
                    </div>                                 

                    <div class="col">


                            <div class="row">
                                <b-form-group class="col-md-6" label="CEP:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objPessoa.endereco.cep.required" invalid-feedback="Campo obrigatório">
                                    <the-mask :mask="'#####-###'" v-model="objPessoa.endereco.cep" class="form-control form-control-sm" />   
                                </b-form-group>             
                            </div>



                            <div class="row">
                                <b-form-group class="col-lg-6" label="Logradouro:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objPessoa.endereco.logradouro.required" invalid-feedback="Campo obrigatório">
                                    <b-form-input  type="text" size="sm" v-model="objPessoa.endereco.logradouro" required placeholder="Logradouro"></b-form-input>
                                </b-form-group>

                                <b-form-group class="col-lg-2" label="Número:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objPessoa.endereco.numero.required" invalid-feedback="Campo obrigatório">
                                    <b-form-input  type="text" size="sm" v-model="objPessoa.endereco.numero" required placeholder="Número"></b-form-input>
                                </b-form-group>

                                <b-form-group class="col-lg-4" label="Complemento:" label-for="input-1" >
                                    <b-form-input  type="text" size="sm" v-model="objPessoa.endereco.complemento" required placeholder="Complemento"></b-form-input>
                                </b-form-group>
                            </div>


                            <div class="row">
                                <b-form-group class="col-lg-2" label="UF:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objPessoa.endereco.bairro.cidade.uf.id_uf.required" invalid-feedback="Campo obrigatório">
                                    <select-uf v-model="objPessoa.endereco.bairro.cidade.uf.id_uf" ></select-uf>
                                </b-form-group>
                                <b-form-group class="col-lg-6" label="Cidade:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objPessoa.endereco.bairro.cidade.codigo_ibge.required" invalid-feedback="Campo obrigatório">
                                    <select-cidade v-model="objPessoa.endereco.bairro.cidade.codigo_ibge" :uf="objPessoa.endereco.bairro.cidade.uf.id_uf"></select-cidade>
                                </b-form-group>                
                            </div>

                            <div class="row">
                                <b-form-group class="col-lg-8" label="Bairro:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objPessoa.endereco.bairro.id_bairro.required" invalid-feedback="Campo obrigatório">
                                    <div style="display: flex"><select-bairro ref="selectBairro" v-model="objPessoa.endereco.bairro.id_bairro" :cidade="objPessoa.endereco.bairro.cidade.codigo_ibge"></select-bairro><span v-if="this.$v.objPessoa.endereco.bairro.cidade.codigo_ibge.required" @click="popoverShow=true" class="pl-3 pt-2 fa fa-plus"></span></div>
                                </b-form-group>                        
                            </div>
                    </div>
                    </div>                    

        </div>


            <b-modal v-model="popoverShow" id="modal-prevent-closing" title="Cadastro rápido de Bairro" :no-close-on-backdrop=true ok-title="Salvar" cancel-title="Cancelar" @ok="salvarBairro">
                <div>
                    <b-form-group label="Nome" label-for="popover-input-1" label-cols="3" :state="input1state" class="mb-1" description="Insira o nome do novo bairro" invalid-feedback="Este campo é obrigatorio" >
                        <b-form-input type="text" id="popover-input-1" :state="input1state" ref="input1" v-model="novoBairro" size="sm"></b-form-input>
                    </b-form-group>
                </div>
            </b-modal>

</div>            

</template>
   

<script>


import { requiredIf, required } from 'vuelidate/lib/validators'

import {mask} from 'vue-the-mask'


export default {

    /* */
    directives: {mask},

    
    /* */
    components: {

    }, 

    /* */
    watch: {
    },

    /* */
    props: {
        idPessoa:{}
    },            

    /* */
    data() {
            return {
                novoBairro:'',
                input1state: null,
                formValido:true,
                popoverShow: false,
                objPessoa:{
                    tipo: 'F',
                    id_pessoa:null,
                    cpfcnpj:'',
                    
                    razao_social:null,
                    nome_fantasia:null,
                    insc_estadual:null,
                    contato:null,
                    cnpj:null,
                    conjuge:{
                        id_pessoa:null,
                        tipo:'F'
                    },
                    estado_civil:0,
                    rg:null,
                    nome:null,
                    sobrenome:null,
                    cpf:null,
                    cod_ocupacao:null,
                    ocupacao:{
                        codigo:null,
                        titulo:null
                    },

                    endereco:{
                        id_endereco:null,
                        cep:null,
                        logradouro:null,
                        numero:null,
                        complemento:null,
                        bairro:{
                            id_bairro:null,
                            nome: null,
                            cidade:{
                                codigo_ibge: null,
                                nome: null,
                                uf: {
                                    id_uf: null,
                                    nome: null,
                                    sigla: null
                                }
                            }
                        },

                    },

                    fone:null,
                }
            }
        },

    /* */
	computed:{
        isEmpresa(){    return this.objPessoa.cpfcnpj.length==14    },
        isCasado(){    return this.objPessoa.estado_civil==1    },
        descricaoConguge(){ return this.formataCpfCnpj(this.objPessoa.conjuge)+" - "+this.objPessoa.nome}

	},

    /* */
    methods: {
 
        /* */
        invalidFeedback(campo) {        
            return campo!='';
        },

        /* */
        formataCpfCnpj(vlaor){ return vlaor.tipo == 'J' ? this.$helper.formataCpfCnpj(vlaor.cnpj) : this.$helper.formataCpfCnpj(vlaor.cpf);  }, 

        /* */
        savePessoa(){
            this.$v.$touch();
            if(!this.$v.$invalid){
                this.objPessoa.tipo     = this.isEmpresa ? 'J' : 'F';
                this.objPessoa.cpf      = !this.isEmpresa ? this.objPessoa.cpfcnpj : null;
                this.objPessoa.cnpj     = this.isEmpresa ? this.objPessoa.cpfcnpj : null;
                this.$api.pessoa.savePessoa(this.objPessoa)
                .then(response => { 
                    if(response.status==200){
                        this.$bvToast.toast('Salvo com sucesso!', { title: 'Cadastro de pessoa',   variant: 'success',    toaster:'b-toaster-top-center'    }) 
                    }
                
                    this.objPessoa              =   response.data;
                   // this.objPessoa.cpfcnpj      = response.data.hasOwnProperty("cpf") ?   response.data.cpf   :  response.data.cnpj;
                    this.$emit('pessoaSalva', this.objPessoa);

                }) 
                .catch();              
            }
        },

        /* */
        salvarBairro(bvModalEvt) {

            bvModalEvt.preventDefault();

            if (!this.novoBairro) { this.input1state = false    }

            if (this.novoBairro ) {
                this.input1state = true

                this.$api.utils.savebairro({nome: this.novoBairro, latitude:0, longitude:0, cidade:this.objPessoa.endereco.bairro.cidade})
                .then(response => { 
                    if(response.status==200){
                        this.$bvToast.toast('Salvo com sucesso!', { title: 'Cadastro de pessoa',   variant: 'success',    toaster:'b-toaster-top-center'    }) 
                    }
                    this.$refs.selectBairro.addBairro(response.data);
                    this.objPessoa.endereco.bairro       =   response.data;

                    this.$nextTick(() => {
                        this.$bvModal.hide('modal-prevent-closing')
                    })

                }) 
                .catch(); 

                
            }
        },
        
        /* */
        onClose() {
            this.popoverShow = false
        },        


    },

    /* */
    created() {
     
    },    

    /* */
    mounted(){

        if(this.idPessoa){
            
            this.$api.pessoa.getPessoa({id_pessoa:this.idPessoa})
            .then(response => { 
                
                    this.objPessoa              =   response.data;
                   // this.objPessoa.cpfcnpj      =   response.data.hasOwnProperty("cpf") ?   response.data.cpf   :  response.data.cnpj;

            }) 
            .catch();            
            
        }
	},

    validations: {
        objPessoa: {

            cpfcnpj:{required},
            fone:{required},
            email:{required},

            conjuge:{
                id_pessoa:{
                    required:requiredIf(function () {
                        return !this.isEmpresa && this.isCasado
                    })
                }
            },            
            
            estado_civil:{
                required:requiredIf(function () {
                    return !this.isEmpresa
                })
            },

            razao_social:{
                required:requiredIf(function () {
                    return this.isEmpresa
                })
            },
            contato:{
                required:requiredIf(function () {
                    return this.isEmpresa
                })
            },
        
            nome:{
                required:requiredIf(function () {
                    return !this.isEmpresa
                })
            },
            sobrenome:{
                required:requiredIf(function () {
                    return !this.isEmpresa
                })
            },
            rg:{
                required:requiredIf(function () {
                    return !this.isEmpresa
                })
            },

            ocupacao:{
                codigo:{
                    required:requiredIf(function () {
                        return !this.isEmpresa
                    })
                }                
            },
        
            endereco:{
                cep:{required},
                logradouro:{required},
                numero:{required},                
                bairro:{
                    id_bairro:{required},
                    cidade:{
                        codigo_ibge: {required},
                        uf: {
                            id_uf: {required},
                        }
                    }                    
                }                
            },


        },
    }    
            
        
}
</script>