import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

let store = new Vuex.Store({
    state: {
        token: localStorage.getItem('token') || '',
        user: {},
        trips: []
    },
    getters: {
        isAuthenticated: state => !!state.token,
        getUser: state => state.user,
        token: state => state.token,
        getTrips: state => state.trips
    },
    mutations: {
        setUser(state, payload) {
            state.user = payload
        },
        setToken(state, payload) {
            state.token = payload;
        },
        setTrips(state, payload) {
            state.trips = payload
        },
        clearToken(state) {
            state.token = '';
            localStorage.removeItem('token')
        },
    },
    actions: {
        logout({commit}) {
            commit('clearToken');
        }
    }
});

export default store;
