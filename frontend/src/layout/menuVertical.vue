<template>

  <div>


      <div class="main-menu">
          <h5 class="sidenav-heading">Principal</h5>
          <ul id="side-main-menu" class="side-menu list-unstyled">                  
            

            <li v-for="objMenu in listaMenu">
              
                <router-link v-if="objMenu.children.length == 0 && objMenu.hasOwnProperty('menu') " class="nav-link" :to="linkTo(objMenu)" exact><i class="fa fa-align-justify"></i>{{objMenu.menu}}</router-link>

                <a v-if="objMenu.children.length > 0 && objMenu.hasOwnProperty('menu') " :href="idElementoMenu(objMenu, true)" aria-expanded="false" data-toggle="collapse"> <i class="fa fa-align-justify"></i>{{objMenu.menu}}</a>
                <ul v-if="objMenu.children.length > 0 && objMenu.hasOwnProperty('menu') " :id="idElementoMenu(objMenu, false)" class="collapse list-unstyled ">
                  <li v-for="objMenuC in objMenu.children"><router-link class="nav-link" :to="linkTo(objMenuC)"><i class="fa fa-align-justify"></i>{{objMenuC.menu}}</router-link></li>                       
                </ul>

            </li>


          </ul>
        </div>


        <div class="admin-menu">
          <h5 class="sidenav-heading">Módulos</h5>
          <ul id="side-admin-menu" class="side-menu list-unstyled"> 
            <li><router-link class="nav-link" to="/home"><i class="fa fa-home"></i>Home</router-link></li>
            <li v-for="objModulo in this.$root.listaModulos"><a @click="setModulo(objModulo)"><i class="fa fa-align-justify"></i>{{objModulo.nome}}</a></li>                       
          </ul>
        </div>

   
  </div>


</template>

<script>
export default {

    /* */  
    props: {
        listaMenu:{
            type: Array,
            default: []
        },
    },

    /* */
    methods: {

        /* */
        setModulo(objModulo){
            this.$root.setModulo(objModulo) 
        },

        /* */
        idElementoMenu(objMenuC, hash){
            return (hash ? "#" : "")+"itemmenu"+objMenuC.id+"dropdownDropdown";
        },

        /* */
        linkTo(objModulo){
          return { name: this.$root.modulo.link+'_'+(objModulo.hasOwnProperty("nome") ? objModulo.nome : 'home')}
        } 

    },    

  
};
</script>

<style>

</style>
