<template>
<div>
    <!--Page Title-->
    <section class="page-title" style="background-image:url(/images/main-slider/paralimpico_m.jpg)">
    <div class="auto-container">
        <h2>{{apresentacao.titulo}}</h2>
            <ul class="page-breadcrumb">
            <li><a href="/">home</a></li>
				<li><a href="/servicos">Apresentação</a></li>
                <li>{{apresentacao.titulo}}</li>
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
								<p v-html="apresentacao.conteudo" />
							</div>
						</div>
					</div>
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

                apresentacao:{}

            }
        },

    /* */
	computed:{


	},


    /* */
    methods: {






        getApresentacao(url){
            
            this.$api.site.getApresentacao({"url": url})
            .then(response => { 
              
                this.apresentacao    =   response.data;
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

        if(this.$route.params.refid){
            this.getApresentacao(this.$route.params.refid);
        }

	}        
        
}
</script>