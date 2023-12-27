<template>
<section class="news-section">
			<div class="auto-container">
				<!-- Sec Title -->
				<div class="sec-title">
					<h2>Procura por dicas? Leia o nosso Blog</h2>
					<div class="text">Stay updated with latest trends, inspiration, expert tips, DIYs and more</div>
				</div>
				
				<div class="row clearfix">
					
					<!-- News Block -->
					<div class="news-block col-lg-4 col-md-6 col-sm-12"  v-for="post in blogPosts" :key="post.id">
						<div class="inner-box wow fadeInRight" data-wow-delay="0ms" data-wow-duration="1500ms">
							<div class="image">
								<a href="blog-detail.html"><img :src="getImgUrl(post.imagem)" alt="" /></a>
							</div>
							<div class="lower-content">
								<ul class="post-meta">
									<li><a @click="getUserSelecionado(post.autor)">Por : {{post.autor.nomeCompleto}}</a></li>
									<li><a @click="getCategoriaSelecionada(post.categoria)">{{post.categoria.nome}}</a></li>
								</ul>
								<h3><a  @click="goBlogPost(post)">{{post.titulo}}</a></h3>
								<a  @click="goBlogPost(post)" class="read-more">Continue lendo <span class="icon flaticon-right-arrow-1"></span></a>
							</div>
						</div>
					</div>
										
				</div>
				
			</div>
		</section>

</template>
   

<script>


export default {
	name: 'dicasBlog',
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
				blogPosts:[],
            }
        },

    /* */
	computed:{
	},

    /* */
    methods: {

        /* */ 
        getImgUrl(file){
            return process.env.VUE_APP_FOTOS_PATH+file;
		},
		
		getUserSelecionado(autor){
			this.$router.push('/blog/autor/'+autor.ref_id).catch(()=>{});
		},


		getCategoriaSelecionada(cat){
			this.$router.push('/blog/categoria/'+cat.ref_id).catch(()=>{});
		},				

		goBlogPost(post){
			this.$router.push('/blog/post/'+post.refId).catch(()=>{});
		},


		getBlogPosts(){

            this.$api.site.getBlogPosts({page: 1,size:3})
            .then(response => { 
				var temp = response.data;
                this.blogPosts = temp.content;
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
            this.blogPosts = this.params.content;
        } else {
            this.getBlogPosts();
        }        
	}        
        
}
</script>