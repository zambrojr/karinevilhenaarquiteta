// store.js
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        accessToken:  localStorage.getItem('token') ||  '',
        usuario: null,
    }, 
    //$on =====> corresponde a event listeners
    mutations: {

        set_usuario(state, usuario) {
            this.state.usuario = usuario;
        },
        auth_success(state, token, user){
            state.accessToken = token
            state.usuario = user
          },        
        logout(state){
            state.usuario = ''
            state.accessToken = ''
          },        

    },
    //$emit =====> corresponde a event emitters
    actions: {

        setUsuario({ commit }, obj) {
            commit('set_usuario', obj);
        },
        login({ commit }, token, user) {
            commit('auth_success', token, user);
        },      
        logout({ commit }) {
            commit('logout');
        },           

    },
    getters: {
        usuario: state => state.usuario,
        isLoggedIn: state => !!state.accessToken,
    },
});