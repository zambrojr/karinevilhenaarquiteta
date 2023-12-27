<template>
<div id="top">

    <!--Page Title-->
    <section class="page-title page-title-alt" :style="'background-image:url('+getImgUrl('paralimpico_m.jpg')+')'">
    <div class="auto-container">
        <h2>Blog<span v-if="hasCategoriaSelecionada"> - {{categoriaSelecionada.nome}}</span><span v-if="hasUserSelecionada"> - {{formatString(autorSelecionado.nomeCompleto)}}</span></h2>
            <ul class="page-breadcrumb">
            <li><a href="/">home</a></li>
				<li><a v-if="hasCategoriaSelecionada || hasUserSelecionada" href="/blog">Blog</a><span v-else>Blog</span></li>
                <li v-if="hasCategoriaSelecionada">{{categoriaSelecionada.nome}}</li>
				<li v-if="hasUserSelecionada">{{formatString(autorSelecionado.nomeCompleto)}}</li>
            </ul>
        </div>
    </section> 
    <!--End Page Title-->

	<!--Sidebar Page Container-->
    <div class="sidebar-page-container" layout:fragment="content">
	<div class="auto-container">
		<div class="row clearfix">
            <!--Content Side / Blog Classic -->
			<div class="content-side col-xl-9 col-lg-8 col-md-12 col-sm-12">
				<div class="blog-classic padding-right">

					<!-- News Block Three-->
					<div class="news-block-three" v-for="post in blogPosts" :key="post.id">
						<div class="inner-box">
							<div class="image-box" v-if="post.imagem!=null">
								<figure class="image"><a @click="goBlogPost(post)"><img :src="getImgUrl(post.imagem)" alt=""></a></figure>
								<span class="date">{{dataElegant(post.data)}}</span>
							</div>
							<div class="lower-content">
								<div class="post-meta">
									<ul class="post-info clearfix">
										<li>Em : {{dataElegant(post.data)}}</li>
										<li><a @click="getUserSelecionado(post.autor, 1)">Por : {{post.autor.nomeCompleto}}</a></li>
										<li><a @click="getCategoriaSelecionada(post.categoria, 1)">{{post.categoria.nome}}</a></li>
									</ul>
								</div>
								<h3><a  @click="goBlogPost(post)">{{post.titulo}}</a></h3>
								<div class="text" v-html="post.resumo" />
							<div class="link-box"><a @click="goBlogPost(post)" class="theme-btn read-more">Continue lendo</a></div>
							</div>
						</div>
					</div>
					
					
				</div>
					
					<b-pagination-nav
							v-model="filtro.page"
							v-if="blogPosts.length>0"
							:number-of-pages="filtro.totalPages"
							base-url="#"
							@change="mudaPagina()"
						></b-pagination-nav>
						<div v-else>
							Nenhum post encontrado com os filtros especificados.
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

import helpers from '../../../helpers'
import menuCategorias from "../../../components/sistema/menuCategorias"
export default {

    /* */
    components: {
		"menu-categorias":menuCategorias,
		BPaginationNav: () => import('bootstrap-vue').then(module => module.BPaginationNav),
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
				categoriaSelecionada:{},
				autorSelecionado:{},
                filtro:{
                    page: 1,
					size:5,
					categoria:null,
					user:null,
                    totalElements: 0,
                    totalPages: 0,
                },				
            }
        },

    /* */
	computed:{

		hasCategoriaSelecionada(){
			return Object.prototype.hasOwnProperty.call(this.categoriaSelecionada,"id") && this.categoriaSelecionada.id!=null;
		},

		hasUserSelecionada(){
			return Object.prototype.hasOwnProperty.call(this.autorSelecionado,"id_pessoa") && this.autorSelecionado.id_pessoa!=null;
		},
		
	},

    /* */
    methods: {

        /* */ 
        getImgUrl(file){
            return process.env.VUE_APP_FOTOS_PATH+file;
        },


		getUserSelecionado(user, page){
			this.categoriaSelecionada = {};
			this.filtro.categoria = null;
			this.$router.push('/blog/autor/'+user.ref_id).catch(()=>{});
            this.$api.site.getAutor({ref_id:user.ref_id})
            .then(response => { 
				this.autorSelecionado = this.$api.scl(response.data);
				this.filtro.page = page;
				this.filtro.user = this.autorSelecionado.ref_id;				
				this.$nextTick(() => { 
					this.getBlogPosts();
				});			   
            }) 
            .catch();
		},


		getCategoriaSelecionada(post, page){
			this.autorSelecionado = {};
			this.filtro.user = null;
			this.$router.push('/blog/categoria/'+post.ref_id).catch(()=>{});
            this.$api.site.getCategoria({ref_id:post.ref_id})
            .then(response => { 
				this.categoriaSelecionada = this.$api.scl(response.data);
				this.filtro.page = page;
				this.filtro.categoria = this.categoriaSelecionada.ref_id;				
				this.$nextTick(() => { 
					this.getBlogPosts();
				});			   
            }) 
            .catch();

		},

        /* */ 
        dataElegant(vlaor){ 
            return helpers.dataElegant(vlaor);  
        },

		goBlogPost(post){
			this.$router.push('/blog/post/'+post.refId).catch(()=>{});
			this.$scrollTo({ container: 'body', easing: 'ease-in', lazy: true, offset: -60, force: true, x: false, y: true });
		},


        formatString(words) {
            var separateWord = words.toLowerCase().split(' ');
            for (var i = 0; i < separateWord.length; i++) {
                separateWord[i] = separateWord[i].charAt(0).toUpperCase() +
                separateWord[i].substring(1);
            }
            return separateWord.join(' ');
        },  

        mudaPagina(){
            this.getBlogPosts();
        },

        getBlogPosts(){

            this.$api.site.getBlogPosts(this.filtro)
            .then(response => { 
				var temp = response.data;
				
                this.blogPosts = temp.content;
                this.filtro.page = temp.number+1;
                this.filtro.totalElements = temp.totalElements
				this.filtro.totalPages = temp.totalPages;           
				
				this.$scrollTo({ container: 'body', easing: 'ease-in', lazy: true, offset: -60, force: true, x: false, y: true });
            }) 
            .catch();  

        },




    },

    /* */
    created() {
     
    },    

    /* */
    mounted(){

		var page;
        if(this.$route.params.refid){
			page = this.$route.query.page ? this.$route.query.page : 1;
            this.getCategoriaSelecionada({ref_id:this.$route.params.refid}, page);
        } if(this.$route.params.userrefid){
			page = this.$route.query.page ? this.$route.query.page : 1;
            this.getUserSelecionado({ref_id:this.$route.params.userrefid}, page);
        } else {
			this.getBlogPosts();
		}
	}        
        
}
</script>