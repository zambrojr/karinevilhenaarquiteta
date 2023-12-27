import Vue          from 'vue';
import VueRouter    from 'vue-router';
import index         from '../modulos/publico/index';
import especialidade         from '../modulos/publico/nossaespecialidade/especialidade';
import especialidadeindec         from '../modulos/publico/nossaespecialidade/index';
import blog         from '../modulos/publico/blog/index';
import blogpost         from '../modulos/publico/blog/detail';
import processotrabalho         from '../modulos/publico/processotrabalho/index';

import apresentacao         from '../modulos/publico/apresentacao/apresentacao';

import todo         from '../modulos/publico/todo';
import about         from '../modulos/publico/about';

/*import oportunidades         from '../modulos/publico/oportunidades/index';
import anuncioFull         from '../modulos/publico/oportunidades/index';
import empreendimento         from '../modulos/publico/empreendimento/index';
*/

Vue.use(VueRouter);

const routes = [

    {                
        name: 'index',
        path: '/',
        component : index,
        props: true
    },

    {                
        name: 'especialidade',
        path: '/servicos/:epecialidade',
        component : especialidade,
        props: true
    },    

    {                
        name: 'especialidadeindec',
        path: '/servicos',
        component : especialidadeindec,
        props: true
    },    

    
    {                
        name: 'blog',
        path: '/blog',
        component : blog,
        props: true
    },

    {                
        name: 'about',
        path: '/about',
        component : about,
        props: true
    },    

    {                
        name: 'blogpost',
        path: '/blog/post/:refid',
        component : blogpost,
        props: true
    },    
    {                
        name: 'blogcategoria',
        path: '/blog/categoria/:refid',
        component : blog,
        props: true
    },        
    {                
        name: 'apresentacao',
        path: '/apresentacao/:refid',
        component : apresentacao,
        props: true
    },            
    {                
        name: 'blogautor',
        path: '/blog/autor/:userrefid',
        component : blog,
        props: true
    },        
    {                
        name: 'processotrabalho',
        path: '/processo_trabalho',
        component : processotrabalho,
        props: true
    },       
    
    {                
        name: 'about',
        path: '/about',
        component : todo,
        props: true
    },    
    {                
        name: 'sobre',
        path: '/sobre',
        component : todo,
        props: true
    },          
    {                
        name: 'projetos',
        path: '/projetos',
        component : todo,
        props: true
    },  
    {                
        name: 'contato',
        path: '/contato',
        component : todo,
        props: true
    },          

/*
    {                
        name: 'oportunidades',
        path: '/oportunidades',
        component : oportunidades,
        props: true
    },

    {                
        name: 'anuncio',
        path: '/oportunidades/:oportunidade',
        component : anuncioFull,
        props: true
    },

    {                
        name: 'empreendimento',
        path: '/empreendimento/:empreendimento',
        component : empreendimento,
        props: true
    },  

*/
    { path: "*", component: index }



];

const router = new VueRouter({
    routes,
    mode: 'history',
    linkActiveClass: '',
    linkExactActiveClass: '',
});


router.beforeEach( (to, from, next) => {
    //console.log(from,to);
    next();
    //this.$scrollTo({ container: 'body', easing: 'ease-in', lazy: false, offset: -60, force: true, x: false, y: true });
});

export default router;