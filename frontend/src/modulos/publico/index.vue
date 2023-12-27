<template>
<div>

		<!-- Main Slider -->
		<section class="main-slider">
			<div class="slider-box">
			
				<!-- Banner Carousel -->
				<div class="banner-carousel owl-theme owl-carousel">
				
					<!-- Slide -->
					<div class="slide" v-for="destaque in listaDestaquesSite" :key="destaque.id">
						<div class="image-layer" :style="'background-image:url('+getImgBannerUrl(destaque.banner)+')'"></div>
						<div class="auto-container">
							<div class="content">
								<h2>{{destaque.titulo}}<br v-if="destaque.subtitulo" /> <span v-if="destaque.subtitulo">{{destaque.subtitulo}}</span></h2>
								<div class="text">{{destaque.resumo}}</div>
								<div class="btns-box">
									<a href="#" class="theme-btn btn-style-one"><span class="txt">Saiba mais</span></a>
								</div>
							</div>
						</div>
					</div>
										
				</div>
				
			</div>
		</section>
		<!-- End Banner Section -->
		
		<!-- News Section -->
		<component v-for="(sessao,index) in this.sessoesAtivasSite" :key="index" :is="sessao.component" :params="sessao.params"></component>
		<!-- End News Section -->
		
		<!-- Call To Action Section -->
		<section class="call-to-action-section" style="background-image: url(images/background/architect3.webp)">
				<div class="auto-container">
					<h2>Karine Vilhena Arquitetura e Interiores</h2>
					<div class="text">Interiors for all styles and budgets, Choose from thousands of <br> designs. Heart your favorites to shortlist.</div>
					<a href="contact.html" class="theme-btn btn-style-two"><span class="txt">Contate-nos</span></a>
				</div>
		</section>
		<!-- End Call To Action Section -->

</div>
</template>
   

<script>


//import {debounce} from 'lodash';
/* global $ */
export default {

	/* */
	components: {

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
                destaques:[],
				listaDestaquesSite:[],
				sessoesAtivasSite:[]
            }
        },

    /* */
	computed:{
		compDicasBlog(){ return []}
	},

    /* */
    methods: {




		/* */ 
        getImgBannerUrl(file){
            return process.env.VUE_APP_FOTOS_PATH+"destaques/"+file;
        },


        /* */
        goEspecialidade(epeci){
            this.$router.push('/servicos/'+epeci.url)
        },

        /* */
        saibaMais(destaque){
            this.$router.push('/empreendimento/'+destaque.url)
        },

        /* */ 
        getImgBanner(file){
            return process.env.VUE_APP_FOTOS_PATH+"banner/"+file.banner;
        },

        listadestaquessite(){

            this.$api.utils.listadestaquessite({})
            .then(response => { 
                this.destaques = response.data
            }) 
            .catch();  

		},
		
        getListaDestaquesSite(){

            this.$api.utils.listaDestaquesSite({})
            .then(response => { 
				this.listaDestaquesSite = this.$api.scl(response.data);
				this.$nextTick(() => { 
					this.initCarroussel();
				});
				
            }) 
            .catch();  

        },
		


        getSessoesSite()
        {
            this.$api.site.getSessoesSite()
            .then(response => { 
				var sessoesSite =  this.$api.scl(response.data);
				this.sessoesAtivasSite = [];

				sessoesSite.forEach(sessao=>{
					this.sessoesAtivasSite.push(require('./_'+sessao.codigo).default);
				})

            }) 
            .catch();  
		},
		
        content()
        {
            this.$api.site.content()
            .then(response => { 
				var dada =  this.$api.scl(response.data);
				var sessoes = dada.find(x=>{return x.key=='sessoesativas'});
				var sessoesSite =  this.$api.decode(sessoes.content);
				this.sessoesAtivasSite = [];
				sessoesSite.forEach(sessao=>{

					var params = dada.find(x=>{return x.key==sessao.codigo});
					this.sessoesAtivasSite.push({"component":require('./_'+sessao.codigo).default, "params": params ? this.$api.decode(params.content) : null});
					
				});

				var destaques = dada.find(x=>{return x.key=='destaques'});
				this.listaDestaquesSite = this.$api.decode(destaques.content);
				this.$nextTick(() => { 
					this.initCarroussel();
				});


            }) 
            .catch();  
		},



		initCarroussel(){
				// Main Slider Carousel
				if ($('.banner-carousel').length) {
					$('.banner-carousel').owlCarousel({
						animateOut: 'fadeOut',
						animateIn: 'fadeIn',
						loop:true,
						margin:0,
						nav:true,
						singleItem:true,
						smartSpeed: 500,
						autoplay: true,
						autoplayTimeout:6000,
						navText: [ '<span class="fas fa-angle-left"></span>', '<span class="fas fa-angle-right"></span>' ],
						responsive:{
							0:{
								items:1
							},
							600:{
								items:1
							},
							1024:{
								items:1
							}
						}
					});    		
				}			
		}

    },

    /* */
    created() {
     
    },    

    /* */
    mounted(){
        //this.listadestaquessite();				
		//this.getListaDestaquesSite();
		//this.getSessoesSite();
		this.content();
	}        
        
}
</script>