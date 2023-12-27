<template>
<div>
    <!--Page Title-->
    <section class="page-title" style="background-image:url(/images/main-slider/architect1.webp)">
    <div class="auto-container">
        <h2>Processo de Trabalho <span>- {{processoAtivo.titulo}}</span></h2>
            <ul class="page-breadcrumb">
            <li><a href="/">home</a></li>
				<li>Processo de Trabalho</li>
                <li>{{processoAtivo.titulo}}</li>               
            </ul>
        </div>
    </section>
    <!--End Page Title-->


<div class="auto-container">
<div class="row clearfix">

				<!--Content Side / Services Detail -->
                <div class="content-side col-xl-12 col-lg-12 col-md-12 col-sm-12">
                    <div class="services-detail">
						<div class="inner-box">
							<div class="text">

                                <form-wizard color="#e0b262" @on-change="alterou" ref="wizard" stepSize="lg" :validateOnBack="false" next-button-text="Próximo" back-button-text="Anterior">
                                    <span slot="title"></span>

                                    <tab-content v-for="processo in processosTrabalho" :key="processo.id" :title="processo.titulo" :icon="processo.icon" > 
                                            <div class="row pt-5">
                                                <div class="col">
                                                    <p v-html="processo.descricao" />
                                                </div>   
                                            </div>
                                    </tab-content>

                                    <template slot="footer" slot-scope="props">
                                    <div class="wizard-footer-left">
                                        <wizard-button v-if="props.activeTabIndex > 0 && !props.isLastStep" @click.native="props.prevTab()" :style="props.fillButtonStyle">Anterior</wizard-button>
                                        </div>
                                        <div class="wizard-footer-right">
                                        <wizard-button v-if="!props.isLastStep" @click.native="props.nextTab()" class="wizard-footer-right" :style="props.fillButtonStyle">Próximo</wizard-button>
                                        </div>
                                    </template>

                                </form-wizard>    

							</div>
						</div>
					</div>
				</div>
				

				
			</div>

                <div class="contact-1 mb-50 pt-5">
                    <fale-conosco></fale-conosco>
                </div>

		</div>


	</div>



</template>
   

<script>


import faleConosco from '../../../components/sistema/outros/faleConosco';



export default {

    /* */
    components: {
        faleConosco,
        FormWizard: () => import('vue-form-wizard').then(module => module.FormWizard),
        TabContent: () => import('vue-form-wizard').then(module => module.TabContent),
        WizardButton: () => import('vue-form-wizard').then(module => module.WizardButton),
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

                processosTrabalho:[],
                processoAtivo:{}

            }
        },

    /* */
	computed:{


	},


    /* */
    methods: {

        alterou(){
            var tis = this;
            this.$nextTick(() => { 
                this.processoAtivo = this.processosTrabalho[tis.$refs.wizard.activeTabIndex];
            });
        },

        listaProcessosTrabalho(){

            this.$api.utils.listaProcessosTrabalho({})
            .then(response => { 
                this.processosTrabalho = this.$api.scl(response.data);
                this.processoAtivo = this.processosTrabalho[0];                
            }) 
            .catch();  
        },		

    },


    /* */
    created() {
     
    },    

    /* */
    mounted(){
        this.listaProcessosTrabalho();
    },
    
    updated(){
        this.$nextTick(() => { 
            if(this.$refs.wizard)
            this.$refs.wizard.activateAll();
        });        
    }
        
}
</script>