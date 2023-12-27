<template>
<div>
    <!--Page Title-->
    <section class="page-title" style="background-image:url(/images/main-slider/paralimpico_m.jpg)">
    <div class="auto-container">
        <h2>{{especialidade.titulo}}</h2>
            <ul class="page-breadcrumb">
            <li><a href="/">home</a></li>
				<li><a href="/servicos">Serviços</a></li>
                <li>{{especialidade.titulo}}</li>
            </ul>
        </div>
    </section>
    <!--End Page Title-->

<div class="sidebar-page-container">
<div class="auto-container">
<div class="row clearfix">

				<!--Content Side / Services Detail -->
                <div class="content-side col-xl-9 col-lg-8 col-md-12 col-sm-12">
<div class="services-detail">
						<div class="inner-box">
							<div class="text">
								<p v-html="especialidade.texto" />
							</div>
						</div>
					</div>
				</div>
				
				<!--Sidebar Side-->
				<div class="sidebar-side col-xl-3 col-lg-4 col-md-12 col-sm-12">
					<aside class="sidebar">
						
						<!-- Sidebar Widget / List Widget-->
                        <div class="sidebar-widget list-widget">
							<!-- Services List -->
							<ul class="services-list">
								<li v-for="especialidaed in especialidades" :key="especialidaed.id" v-bind:class="{ active: especialidaed.id==especialidade.id}"><a href="#" @click="goEspecialidade(especialidaed)">{{especialidaed.titulo}}</a></li>
							</ul>
						</div>
										
					</aside>
				</div>
				
			</div>

                <div class="contact-1 mb-50">
                    <fale-conosco></fale-conosco>
                </div>

		</div>


	</div>



</div>
</template>
   

<script>


import faleConosco from '../../../components/sistema/outros/faleConosco';



export default {

    /* */
    components: {
        faleConosco
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

                especialidade:{},
                especialidades:[]

            }
        },

    /* */
	computed:{


	},


    /* */
    methods: {


        /* */
        goEspecialidade(epeci){
            //this.$router.push('/nossa-especialidade/'+epeci.url).catch(()=>{});
            this.getEspecialidade(epeci.url);
        },        

        listaEspecialidades(){

            this.$api.utils.listaEspecialidades({})
            .then(response => { 
                this.especialidades = this.$api.scl(response.data);
            }) 
            .catch();  

        },       


        getEspecialidade(url){
            
            this.$api.site.getEspecialidade({"url": url})
            .then(response => { 
              
                this.especialidade    =   response.data;
                window.scrollTo(0,0);


            }) 
            .catch(()=>{
             
                this.$emit("notFound")
            });  

        },


    },


    /* */
    created() {
     
    },    

    /* */
    mounted(){

        if(this.$route.params.epecialidade){
            this.getEspecialidade(this.$route.params.epecialidade);
            this.listaEspecialidades();
        }

	}        
        
}
</script>