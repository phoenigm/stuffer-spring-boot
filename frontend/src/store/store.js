import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

let store = new Vuex.Store({
    state: {
        token: localStorage.getItem('token') || '',
        user: {},
        trips: [],
        myTrips: []
    },
    getters: {
        isAuthenticated: state => !!state.token,
        getUser: state => state.user,
        token: state => state.token,
        getTrips: state => state.trips,
        getMyTrips: state => state.myTrips
    },
    mutations: {
        setUser(state, payload) {
            state.user = payload
        },
        setToken(state, payload) {
            state.token = payload
        },
        setTrips(state, payload) {
            state.trips = payload
        },
        setMyTrips(state, payload) {
          state.myTrips = payload
        },
        clearToken(state) {
            state.token = '';
            localStorage.removeItem('refresh_token');
            localStorage.removeItem('token')
        },
    },
    actions: {
        logout({commit}) {
            commit('clearToken');
        },
        myProfile({commit}, payload) {
            commit('setUser', payload)
        }
    }
});

export default store;
