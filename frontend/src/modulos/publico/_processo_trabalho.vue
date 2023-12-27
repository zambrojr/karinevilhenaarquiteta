<template>

<section class="featured-section" style="background-image: url(images/background/architect1.webp)">
			<div class="auto-container">
				<!-- Title Box -->
				<div class="title-box">
					<h2>Processo de Trabalho</h2>
				</div>
				
				<div class="row clearfix">
					<!-- Feature Block -->
					<div class="feature-block col-lg-3 col-md-6 col-sm-12" v-for="processo in processosTrabalho" :key="processo.id">
						<div class="inner-box wow fadeInLeft" data-wow-delay="0ms" data-wow-duration="1500ms">
							<div class="icon-outer">
								<div class="icon-box">
									<span :class="processo.icon"></span>
								</div>
								<div class="feature-number">{{processo.ordem}}</div>
							</div>
							<div class="lower-content">
								<h3><a @click="goProcessoTrabalho(processo)">{{processo.titulo}}</a></h3>
								<div class="text">{{processo.resumo}}</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>


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
        params:[]
    },            


    /* */
    data() {
            return {
				processosTrabalho:[]
            }
        },

    /* */
	computed:{
	},

    /* */
    methods: {


		goProcessoTrabalho(){
			this.$router.push('/processo_trabalho').catch(()=>{});
		},


        listaProcessosTrabalho(){
            this.$api.utils.listaProcessosTrabalho({})
            .then(response => { 
                this.processosTrabalho = this.$api.scl(response.data);
            }) 
            .catch();  
        },	



    },

    /* */
    created() {
     
    },    

    /* */
    mounted(){
        if(this.params){
            this.processosTrabalho = this.params;
        } else {
            this.listaProcessosTrabalho();
        }
	}        
        
}
</script>