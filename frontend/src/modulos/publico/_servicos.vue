<template>

		<section class="services-section-two">
			<div class="auto-container">
				<!-- Sec Title -->
				<div class="sec-title">
					<h2>Nossos Serviços</h2>
					<div class="text">Do pequeno espaço ao grande empreendimento realizamos projetos arquitetônicos personalizados ao seu estilo e necessidade utilizando-se das modernas tendências de design e decoração.</div>
				</div>
				
				<div class="row clearfix">
					
					<!-- Service Block -->
					<div class="service-block-two col-lg-4 col-md-6 col-sm-12" v-for="especialidaed in especialidades" :key="especialidaed.id">
						<div class="inner-box wow fadeInLeft" data-wow-delay="0ms" data-wow-duration="1500ms">
							<div class="content">
								<div class="icon-box">
									<span :class="especialidaed.icone"></span>
								</div>
								<h3><a href="#" @click="goEspecialidade(especialidaed)">{{especialidaed.titulo}}</a></h3>
								<div class="text">{{especialidaed.descricao_breve}}</div>
								<a href="#" @click="goEspecialidade(especialidaed)"  class="read-more">Saiba Mais</a>
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
                especialidades:[],
            }
        },

    /* */
	computed:{
	},

    /* */
    methods: {

        /* */
        goEspecialidade(epeci){
            this.$router.push('/servicos/'+epeci.url)
        },


        listaEspecialidades(){

            this.$api.utils.listaEspecialidades({})
            .then(response => { 
                this.especialidades = this.$api.scl(response.data);
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
            this.especialidades = this.params;
        } else {
            this.listaEspecialidades();
        }        
	}        
        
}
</script>