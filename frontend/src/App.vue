<template>
  <div id="app">
    <b-avatar src="https://media-exp1.licdn.com/dms/image/C4E03AQEik6jDXPt4fg/profile-displayphoto-shrink_200_200/0?e=1596067200&v=beta&t=pmznOC9HahpuaRvF3r6WTHz0Bbm2AIULlAJUYyvJXdM" size="6rem"></b-avatar>
    <div class="container pt-5">

<form-wizard title="Orçamento" finish-button-text="Enviar" next-button-text="Próximo" back-button-text="Anterior" subtitle="Marque as opções">

  <tab-content title="Dados de contato" > 
        <b-row class="pb-3" >
            <form-cadastro ref="formUsuario"></form-cadastro>
        </b-row>
  </tab-content>

  <tab-content title="Ambientes" > 
        <b-row class="pb-3" >
            <b-col sm="4" v-for="ambiente in listaAmbiente" :key="ambiente.id">
                    <b-form-group :label="ambiente.nome" id="fieldset-1" label-for="input-1" >
                      <b-form-select v-model="ambiente.valor" :options="options"></b-form-select>
                    </b-form-group>
            </b-col>   
        </b-row>
  </tab-content>

  <tab-content v-for="criterio in listaCriterios" :key="criterio.id" :title="criterio.nome" > 


        <b-row class="pb-3" >
            <b-col sm="8">
                    <b-form-group id="fieldset-1" label-for="input-1" >
                      <b-row class="my-1">
                          <b-col class="tituloOpcao"> 
                              <b-form-radio size="lg" v-for="(opcao) in criterio.criterioDificuldadeOpcao" :value="opcao.id" :key="opcao.id" v-model="criterio.opcao_id" >{{opcao.nome}}<p v-if="opcao.descricao">{{opcao.descricao}}</p></b-form-radio>
                          </b-col>            
                      </b-row>
                    </b-form-group>
            </b-col>   
        </b-row>

  </tab-content>


     <template slot="footer" slot-scope="props">
       <div class="wizard-footer-left">
           <wizard-button v-if="props.activeTabIndex > 0 && !props.isLastStep" @click.native="props.prevTab()" :style="props.fillButtonStyle">Anterior</wizard-button>
        </div>
        <div class="wizard-footer-right">
          <wizard-button v-if="!props.isLastStep" @click.native="props.nextTab()" class="wizard-footer-right" :style="props.fillButtonStyle">Próximo</wizard-button>

          <wizard-button v-else @click.native="enviaFormulario" class="wizard-footer-right finish-button" :style="props.fillButtonStyle">{{props.isLastStep ? 'Enviar' : 'Próximo'}}</wizard-button>
        </div>
      </template>

</form-wizard>    
</div>
  </div>



</template>

<script>



//import Recorder from "./lib/Recorder"
import formCadastro from "./components/formCadastro"

export default {

      name: 'App',

      components: {   
        "form-cadastro":formCadastro

      },

      data() {
          return {
              listaCriterios: [],
              listaAmbiente:[],
              options:[{value:0, text:"0"},{value:1, text:"1" },{value:1, text:"2" },{value:1, text:"3" },{value:1, text:"4" },{value:1, text:"5" }]
          }
      },  

      computed:{
        
      },

      methods:{

            /* */
            getListaCriteriosDificuldades(){
                    this.$api.orcamento.creiteriosdificuldades.listaCriterios({}).then((response) => {
                        if(response.status == 200){

                            this.listaCriterios = [];
                              response.data.forEach(x=>{

                                this.$set(x, "opcao_id",0);
                                this.listaCriterios.push(x);
                              })

                        }
                    })
                    .catch(error => {
                      console.log(error);
                    });  
            },

            /* */
            getListaAmbiente(){
                    this.$api.orcamento.creiteriosdificuldades.listaAmbiente({}).then((response) => {
                        if(response.status == 200){

                          this.listaAmbiente = [];
                          response.data.forEach(x=>{

                            this.$set(x, "valor",0);
                            this.listaAmbiente.push(x);
                          })

                          
                        }
                    })
                    .catch(error => {
                      console.log(error);
                    });  
            },


            /* */
            enviaFormulario(){
                
                    this.showManoWanderley = true;
                    var user = this.$refs.formUsuario.getUser();
                    var pessoa = user.pessoa;
                    pessoa.tipo='F';
                    pessoa.id_pessoa=null;

                    var orcamentoPessoa={id:null, pessoa:pessoa,  data:null, orcamentoPessoaAmbiente:[], orcamentoPessoaCriterioDificuldade:[] };
                    this.listaAmbiente.forEach(questao=>{                    
                            orcamentoPessoa.orcamentoPessoaAmbiente.push({id:null, valor:questao.valor, ambiente:questao});
                    });

                    this.listaCriterios.forEach(criterio=>{                    
                            var criterioOpcao = criterio.criterioDificuldadeOpcao.find(x=>{return x.id == criterio.opcao_id})  
                            orcamentoPessoa.orcamentoPessoaCriterioDificuldade.push({id:null,  criterioDificuldade:{id:criterio.id}, criterioDificuldadeOpcao: criterioOpcao});
                    });                    

                    this.$api.orcamento.saveOrcamento(orcamentoPessoa)
                    .then(response => { 
                        this.showManoWanderley = false;
                        if(response.status==200){
                            //this.$bvToast.toast('Salvo com sucesso!', { title: 'Cadastro de pessoa',   variant: 'success',    toaster:'b-toaster-top-center'    }) 
                            //location.href="/intra/testes/home";
                        }

                    }) 
                    .catch(error => {
                        this.showManoWanderley = false;
                      if(error.response.status==409){
                          this.emailCadastrado = true;
                      }
                    });              
                
            }
            
          
      },

      mounted(){
          this.getListaCriteriosDificuldades();
          this.getListaAmbiente();
      }

}

</script>