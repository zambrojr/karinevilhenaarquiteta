import Vue          from 'vue';
import VueRouter    from 'vue-router';
import home         from '../mainpages/home';
import login        from '../mainpages/login';
import routerViewInterno from '../layout/mainPage.vue'
import store from '../store'

Vue.use(VueRouter);

const routes = [
    {
        path: '/intra',
        redirect: '/intra/home'
    }    ,
    {
        name: 'login',
        path: '/login',
        component: login,
        props: true
    },            
    {
        path: '/intra/home',
        component: routerViewInterno,
        children:[
            {
                name: 'home',
                path: '',
                component: home,
                props: true
            },
        ]       
    },
];

const router = new VueRouter({
    routes,
    mode: 'history',
    linkActiveClass: '',
    linkExactActiveClass: '',
});


router.beforeEach( (to, from, next) => {
    
    const publicPages   = ['/login'];
    const authRequired  = !publicPages.includes(to.path);
    const loggedIn      = store.getters.isLoggedIn;

    if (authRequired && !loggedIn) {
      return next('/login');
    }
  
    next();

  });

export default router;