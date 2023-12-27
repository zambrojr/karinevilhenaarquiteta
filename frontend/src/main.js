
/**
 * First we will load all of this project's JavaScript dependencies which
 * includes Vue and other libraries. It is a great starting point when
 * building robust, powerful web applications using Vue and Laravel.
 */
var $ = require('jquery');
global.$ = global.jQuery = $;

window._ = require('lodash');

require('./bootstrap');

import Vue from 'vue';
import router from './routes/routes.js'
import Vuelidate from 'vuelidate'
import moment from 'moment'
import VueTheMask from 'vue-the-mask'
import VueScrollTo from 'vue-scrollto'
import {BootstrapVue, BootstrapVueIcons} from 'bootstrap-vue'
import api from './http/api'
import constantes from './constantes'
import helpers from './helpers'
import store from './store'
//import VueBus from 'vue-bus';

import './sass/app.scss'

Vue.use(Vuelidate)
Vue.use(VueScrollTo)
Vue.use(VueTheMask)
Vue.use(BootstrapVue);
//Vue.use(VueBus);

//Icones
Vue.use(BootstrapVueIcons);

import mainPage from './mainpages/routerView';
Vue.component('main-page', mainPage); 

import boxPadrao from './components/general/boxPadrao.vue';
Vue.component('box-padrao', boxPadrao); 

moment.locale('pt-BR');
Vue.prototype.$api      = api;
Vue.prototype.$c        = constantes;
Vue.prototype.$helper   = helpers;

import routerViewInterno from './layout/mainPage.vue'
import routerViewDefault from './mainpages/routerView'

new Vue({

    /* */
    data() {
        return {
            labelMsg:'',
            listaModulos:[],
            user:{nema:"sdvsd"},
            modulo:{},
            showOverlay:false
        }
    },

    /* */
    methods:{    

        /* */
        showWait(labelMsg) {
            this.labelMsg   =   labelMsg;
            this.$bvModal.show("modalWait")
        },

        /* */
        closeWait() {
            this.$bvModal.hide("modalWait")
        },

        /* */
        loadUseLoggedIn(redirect){

            this.$api.usuario.loadUserLoggedIn()
            .then(response => {                 
                var user    = response.data;
                var token   = localStorage.getItem('token');
                this.user   = user;

                this.$store.dispatch('login', { token, user })

                this.carregaRotasDinamicas();
                this.loadModuloAtual();

                if(redirect) this.$router.push('/intra/home');                
            }) 
            .catch(() => {         
                this.$router.push('/login');               
            });            

        },

        /* */
        loadModuloAtual(){
            this.modulo  = localStorage.getItem('objModulo')    ?   JSON.parse(localStorage.getItem('objModulo'))   :   {};
        },        

        /* */
        getListaModulos(){
            this.$api.estrutura.getmodulosResources()
            .then(response => {
                this.listaModulos  = response.data;
            })
            .catch();
        },   

        /* */
        getListaMenu(){
            return Object.prototype.hasOwnProperty.call(this.modulo,"resource")   ?   this.modulo.resource : [];
        },

        /* */
        setModulo(objModulo) {
            let objModuloJson = JSON.stringify(objModulo)
            localStorage.setItem('objModulo',  objModuloJson);
            this.modulo = objModulo;
            this.$nextTick(() => { 
                this.$router.push({ name: objModulo.link+"_home" })
            });              
        },        

        /* */
        getUser(){
            return this.user    ?   this.user   :   {};
        },

        /* */
        getRoles(){
            return this.user    ?   this.user.listaRoles    :   [];
        },      
        
        /* */
        getPessoa(){
            return this.user    ?   this.user.pessoa    :   {};
        },         

        /* */
        rotasComponentes(arrayResources, modulo, rota, objRouter){
            arrayResources.forEach((resource) => {                    
                var objMenu = { name: modulo+"_"+resource.nome, path: resource.nome,  props: true,    children:[] };
                
                var component=  resource.children.length == 0 ? require('./modulos/'+rota+"/"+resource.nome+"/index").default : routerViewDefault;
                if(component != null){  objMenu.component = component;  }

                if(resource.children.length>0){
                    this.rotasComponentes(resource.children, modulo, rota+"/"+resource.nome, objMenu);
                }                
                objRouter.children.push(objMenu);
            });
        },

        /* */
        carregaRotasDinamicas(){
            this.$api.estrutura.getmodulosResources()
            .then(response => {
                response.data.forEach((modulo) => {
                    var objRouter = {   path: '/intra/'+modulo.link,  component: routerViewInterno,   children:[] };
                    this.rotasComponentes(modulo.resource, modulo.link, modulo.link, objRouter);
                    this.$router.addRoutes([objRouter]);
                });

                this.getListaModulos();
            });

        }

    },

    /* */
	computed:{
        nomeModuloAtual(){
            return Object.prototype.hasOwnProperty.call(this.modulo,"nome")   ?   this.modulo.nome : '';
        },        
        moduloSelecionado(){
            return Object.prototype.hasOwnProperty.call(this.modulo,"id")     ?   true : false;
        },         
    },

    /* */       
    mounted() {
        if(store.getters.isLoggedIn){
            this.loadUseLoggedIn(false);
        }            
    },        

    /* */
    el: '#app',
    /* */
    render:  h  =>  h(mainPage),
    /* */
    store,
    /* */
    router,
});

