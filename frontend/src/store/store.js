import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

let store = new Vuex.Store({
    state: {
        token: localStorage.getItem('token') || '',
        user: {}
    },
    getters: {
        isAuthenticated: state => !!state.token,
        getUser: state => state.user,
        token: state => state.token
    },
    mutations: {
        setUser(state, payload) {
            state.user = payload
        },
        setToken(state, payload) {
            state.token = payload;
        },
        clearToken(state) {
            state.token = '';
            localStorage.removeItem('token')
        }
    },
    actions: {
        logout({commit}) {
            commit('clearToken');
        }
    }
});

export default store;
