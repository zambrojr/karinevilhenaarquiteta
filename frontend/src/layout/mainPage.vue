<template>
    <div>
        <section>
            <!-- Side Navbar -->   
            <left-panel ref="leftPanel" :listaMenu="listaMenu" :nome="nome" :avatar="avatar" :roles="roles" v-show="moduloSelecionado"></left-panel> 
            
            <div v-bind:class="{ 'w-100': !moduloSelecionado }" class="page" >
                <!-- navbar -->
                <layuot-header :nome="nome" :nomeModulo="nomeModulo"></layuot-header>
                <b-overlay :show="showOverlay" rounded="sm">
                    <router-view></router-view>
                </b-overlay>
                <layuot-footer></layuot-footer>
            </div>
        </section>

    </div>

</template>
 
<script>

require('../lib/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar')
import {moduleBarraLateral} from '../mainBarraLateral';
import layoutHeader from './header.vue';
import leftPanel from './leftPanel.vue';
import layoutFooter from './footer.vue';

export default {

    /* */
    components:{
        'layuot-header':layoutHeader,
        'left-panel': leftPanel,
        'layuot-footer': layoutFooter 
    },

    /* */
    data() {
        return {
        }
    },

    /* */
    computed:{
        moduloSelecionado(){        return  this.$root.moduloSelecionado },        
        listaMenu(){                return this.$root.getListaMenu(); }  ,
        nome(){                     return this.$root.getPessoa()   ?   this.$root.getPessoa().nomeCompleto :   ''},
        avatar(){                   return this.$root.getPessoa()   ?   this.$root.getPessoa().img_perfil :   ''},
        roles(){                    return this.$root.getRoles()},
        nomeModulo(){               return this.$root.nomeModuloAtual ? " - "+this.$root.nomeModuloAtual : ""   }    ,
        showOverlay(){  return this.$root.showOverlay},
    },

    /* */
    methods: {
    },    

    /* */
    mounted(){
        moduleBarraLateral.initBarraLateral();
    } ,    

}
</script>