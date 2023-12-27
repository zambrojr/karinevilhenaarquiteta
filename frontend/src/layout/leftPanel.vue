<template>

    <nav class="side-navbar">
      <div class="side-navbar-wrapper">
        <!-- Sidebar Header    -->
        <div class="sidenav-header d-flex align-items-center justify-content-center">
          <!-- User Info-->
          <div class="sidenav-header-inner text-center"><img :src="imgAvatar" alt="person" class="img-fluid rounded-circle" />
            <h2 class="h5">{{nome}}</h2><span>{{roles}}</span>
          </div>
          <!-- Small Brand information, appears on minimized sidebar-->
          <div class="sidenav-header-logo"><a href="index.html" class="brand-small text-center"> <strong>B</strong><strong class="text-primary">D</strong></a></div>
        </div>
        <!-- Sidebar Navigation Menus-->


        <main-menu :listaMenu="listaMenu"></main-menu>


      </div>
    </nav>


</template>

<script>

import mainMenu from './menuVertical';

export default {
  
    /* */
    components: {
        'main-menu' :mainMenu,
    },

    /* */
    props: {
        listaMenu:{
            type: Array,
            default: function(){ return []}
        },
        nome: {type:String, default: function(){ return ''}},
        avatar: {type:String, default: function(){ return ''}},
        roles: {type:String, default: function(){ return ''}},

    },

    /* */
    methods: {
    },    

    /* */
    computed:{
        imgAvatar(){  return  process.env.VUE_APP_IMAGES_PATH+'avatar/' + (this.avatar  ? this.avatar : 'default.jpg') },                      
    }

};
</script>

<style>

</style>
