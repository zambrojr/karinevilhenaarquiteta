<template>
<div>

<!--Sidebar Page Container-->
<div class="sidebar-page-container" layout:fragment="content" style="margin-top:50px">
	<div class="auto-container">
		<div class="row clearfix">
			
			<!--Content Side / Blog Classic -->
			<div class="content-side col-xl-9 col-lg-8 col-md-12 col-sm-12">
				<div class="blog-single padding-right">
					<div class="inner-box">
						<div class="image-box">
							<figure class="image"><img src="/images/resource/news-4.jpg" alt=""></figure>
							<span class="date">{{dataElegant(post.data)}}</span>
						</div>
						<div class="lower-content">
							<div class="post-meta">
								<ul class="post-info clearfix">
									<li><a href="blog-detail.html">Por : {{post.autor.nomeCompleto}}</a></li>
									<li><a @click="getCategoriaSelecionada(post.categoria)">{{post.categoria.nome}}</a></li>
								</ul>
							</div>
							<div class="lower-box">
								<h3>{{post.titulo}}</h3>
								<div class="text" v-html="post.conteudo" />
							</div>
						</div>
					</div>
					
					
				</div>
			</div>
			
			<!--Sidebar Side-->
			<div class="sidebar-side col-xl-3 col-lg-4 col-md-12 col-sm-12">
				<aside class="sidebar">
					
					<!-- Search -->
					<div class="sidebar-widget search-box">
						<form method="post" action="contact.html">
							<div class="form-group">
								<input type="search" name="search-field" value="" placeholder="Enter Search Keywords" required>
								<button type="submit"><span class="icon fa fa-search"></span></button>
							</div>
						</form>
					</div>
					
					<!--Blog Category Widget-->
					<menu-categorias v-on:categoriaSelecionada="getCategoriaSelecionada"></menu-categorias>
					
					<!-- Popular Posts -->
					<div class="sidebar-widget popular-posts">
						<div class="sidebar-title"><h2>Recent News</h2></div>

						<article class="post">
							<figure class="post-thumb"><a href="blog-detail.html"><img src="/images/resource/post-thumb-1.jpg" alt=""></a></figure>
							<div class="text"><a href="blog-detail.html">Hanging fruit to identify a ballpark value added ...</a></div>
							<div class="post-info">12 April. 2019</div>
						</article>

						<article class="post">
							<figure class="post-thumb"><a href="blog-detail.html"><img src="/images/resource/post-thumb-2.jpg" alt=""></a></figure>
							<div class="text"><a href="blog-detail.html">Organically grow the holistic world view ...</a></div>
							<div class="post-info">12 April. 2019</div>
						</article>
						
						<article class="post">
							<figure class="post-thumb"><a href="blog-detail.html"><img src="/images/resource/post-thumb-3.jpg" alt=""></a></figure>
							<div class="text"><a href="blog-detail.html">Bring to the table in the win-win survival ...</a></div>
							<div class="post-info">12 April. 2019</div>
						</article>
						
						<article class="post">
							<figure class="post-thumb"><a href="blog-detail.html"><img src="/images/resource/post-thumb-4.jpg" alt=""></a></figure>
							<div class="text"><a href="blog-detail.html">Override the digital divide with additional ...</a></div>
							<div class="post-info">12 April. 2019</div>
						</article>

					</div>
					
					<!-- Popular Tags -->
					<div class="sidebar-widget popular-tags">
						<div class="sidebar-title"><h2>Tags</h2></div>
						<a href="#">Interior Tips</a>
						<a href="#">Lighting</a>
						<a href="#">Designs</a>
						<a href="#">Vintage Look</a>
						<a href="#">Kitchens</a>
						<a href="#">Kitchens</a>
					</div>
					
				</aside>
			</div>
			
		</div>
	</div>
</div>


</div>
</template>
   

<script>

//import {debounce} from 'lodash';
import helpers from '../../../helpers'
import menuCategorias from "../../../components/sistema/menuCategorias"
export default {

    /* */
    components: {
		"menu-categorias":menuCategorias
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
				blogPosts:{},
				post:{}
            }
        },

    /* */
	computed:{
	},

    /* */
    methods: {

		getCategoriaSelecionada(post){
			this.$router.push('/blog/categoria/'+post.ref_id).catch(()=>{});
		},


        /* */ 
        dataElegant(vlaor){ 
            return helpers.dataElegant(vlaor);  
        },

        getBlogPosts(){

            this.$api.site.getBlogPosts({})
            .then(response => { 
                this.blogPosts = response.data;
            }) 
            .catch();  

        },




        getBlogPost(url){
            
            this.$api.site.getBlogPost({"url": url})
            .then(response => { 
              
                this.post    =   response.data;
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
            this.getBlogPost(this.$route.params.refid);
            //this.listaEspecialidades();
        }

	}        
        
}
</script>