

var $ = require('jquery');
global.$ = global.jQuery = $;

window.sjcl = require('./lib/sjcl/sjcl');
window.Vue = require('vue').default;


require('./bootstrap');

import router from './routes/public.js'
import Vue from 'vue'

// Install BootstrapVue
// Optionally install the BootstrapVue icon components plugin
 
import { ToastPlugin  } from 'bootstrap-vue'
/*Vue.use(FormGroupPlugin)
Vue.use(PaginationNavPlugin )*/
Vue.use(ToastPlugin )
//Vue.use(IconsPlugin)

import VueScrollTo from 'vue-scrollto'
Vue.use(VueScrollTo)

import Vuelidate from 'vuelidate'
Vue.use(Vuelidate)

import VueTheMask from 'vue-the-mask'
Vue.use(VueTheMask)

//import helpers from './helpers'
//Vue.prototype.$helper   = helpers;

import mainPage from './mainpages/routerView';
Vue.component('main-page', mainPage); 

import api from './http/apipublic'
Vue.prototype.$api      = api;


import './sass/karine.scss'

import Game from './components/general/space/GameSpace.vue';

window.inicia = function(url)
{

  if(window.instanciaVue != undefined){
    return window.instanciaVue.$router.push(url).catch(()=>{});
  }

  window.instanciaVue = new Vue({

    /* */
    data() {
        return {
          seque:''
        }
    },

    /* */
    methods:{    

      over(instance){ instance.$destroy(); this.$refs.containerG.removeChild(instance.$el);},
         
      initG(){var ComponentClass = Vue.extend(Game); var instance = new ComponentClass(); instance.$on('over', this.over); instance.$mount(); this.$refs.containerG.appendChild(instance.$el);            },      

    },

    /* */
    computed:{
        
    },

    /* */       
    mounted() {

console.log("dddddddddddd")
        window.addEventListener("keypress", e => {
          console.log(e.keyCode)
          this.seque += e.keyCode;
          this.seque = this.seque.substr(-14);
          if(this.seque == '97989799979898')this.initG();
      });     

    },        

    /* */
    el: '#app',
    /* */
    //render:  h  =>  h(mainPage),

    /* */
    router,
  });


  window.instanciaVue.$router.push(url).catch(()=>{});

}




import objTip from './components/general/_tipsPersonagem';

new Vue({

  /* */
  data() {
      return {

      }
  },

  /* */
  methods:{    

  },

  /* */
  computed:{
      
  },

  /* */       
  mounted() {

  },        

  /* */
  el: '#sectionTip',
  /* */
  render:  h  =>  h(objTip),


});

