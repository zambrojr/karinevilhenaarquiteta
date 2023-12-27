<template>

<div class="sidebar-widget sidebar-blog-category">
    <div class="sidebar-title">
        <h2>Categorias</h2>
    </div>
    <ul class="cat-list">
        <li v-for="categoria in categorias" :key="categoria.id"><a  @click="goBlogPost(categoria)">{{categoria.nome}}</a></li>
    </ul>
</div>

</template>
   

<script>

//import {debounce} from 'lodash';

export default {

    /* */
    data() {
            return {
                categorias:[]
            }
        },


    /* */
    methods: {

		goBlogPost(categoria){
            this.$emit("categoriaSelecionada", categoria, 1)			
		},

        listaCategorias(){

            this.$api.utils.listaCategorias({})
            .then(response => { 
                this.categorias = this.$api.scl(response.data);
            }) 
            .catch();  

        },     

    },

    /* */
    mounted(){
        this.listaCategorias();
	}    

}

</script>