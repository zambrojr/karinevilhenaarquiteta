<template>
<div>


<!--Page Title-->
<section class="page-title" :style="'background-image:url('+getImgUrl('paralimpico_m.jpg')+')'">
    <div class="auto-container">
        <h2>Serviços</h2>
        <ul class="page-breadcrumb">
            <li><a href="/">home</a></li>
        </ul>
    </div>
</section>
<!--End Page Title-->

<!-- Services Page Section -->
<section class="services-page-section">
    <div class="auto-container">
        
        <!-- Sec Title -->
        <div class="sec-title light centered">
            <h2>We Provide Different Services In Interior Field</h2>
            <div class="text">Survival strategies to ensure proactive domination. At the end of the day, going forward, a new normal that has evolved from generation X is on the runway heading towards a streamlined cloud solution. User generated content in real-time will have multiple.</div>
        </div>
        
        <div class="row clearfix">
            
            <!-- Service Block -->
            <div class="service-block-three col-lg-4 col-md-6 col-sm-12"  v-for="especialidaed in especialidades" :key="especialidaed.id">
                <div class="inner-box wow fadeInUp" data-wow-delay="0ms" data-wow-duration="1500ms">
                    <div class="image">
                        <a @click="goEspecialidade(especialidaed)"><img :src="getImgServico(especialidaed.imagem)" alt="" /></a>
                    </div>
                    <div class="lower-content">
                        <h3><a @click="goEspecialidade(especialidaed)">{{especialidaed.titulo}}</a></h3>
                        <div class="text servicopanel">{{especialidaed.descricao_breve}}</div>
                        <a @click="goEspecialidade(especialidaed)" class="read-more">Saiba mais</a>
                    </div>
                </div>
            </div>
            
            
        </div>



    </div>
</section>
<!-- End Story Section -->    
        <section class="fale-conosco-section">
            <div class="auto-container">
                <div class="contact-1 mb-50">
                    <fale-conosco></fale-conosco>
                </div>            
            </div>
        </section>

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
            this.$router.push('/servicos/'+epeci.url)
        },        

        /* */ 
        getImgUrl(file){
            return process.env.VUE_APP_FOTOS_PATH+file;
        },

		/* */ 
        getImgServico(file){
            return process.env.VUE_APP_FOTOS_PATH+"servicos/"+file;
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
        this.listaEspecialidades();
	}        
        
}
</script>