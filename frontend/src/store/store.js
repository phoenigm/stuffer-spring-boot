import Vue from 'vue'
import Vuex from 'vuex'
import {AXIOS} from '../api/http-common'

Vue.use(Vuex);

let store = new Vuex.Store({
    state: {
        token: localStorage.getItem('token') || '',
        user: {},
        myRequests: [],
        requestsForMe: [],
        trips: [],
        myTrips: [],
        trip: {},
    },
    getters: {
        isAuthenticated: state => !!state.token,
        getUser: state => state.user,
        token: state => state.token,
        getTrips: state => state.trips,
        getMyTrips: state => state.myTrips,
        getMyRequests: state => state.myRequests,
        getRequestsForMe: state => state.requestsForMe,
        getTrip: state => state.trip,
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
        setMyRequests(state, payload) {
            state.myRequests = payload;
        },
        setRequestsForMe(state, payload) {
            state.requestsForMe = payload;
        },
        clearToken(state) {
            state.token = '';
            localStorage.removeItem('refresh_token');
            localStorage.removeItem('token')
        },
        changeAvatarUrl(state, payload) {
            state.user.avatarUrl = payload;
        },
        setTrip(state, payload) {
            state.trip = payload
        }
    },
    actions: {
        logout({commit}) {
            commit('clearToken');
        },
        async myProfile({commit}) {
            const response = await AXIOS.get('/api/profile');
            commit('setUser', response.data);
        },
        loadMyTrips: async ({commit}) => {
            const response = await AXIOS.get('/api/trip/my');
            if (response.status === 200) {
                commit('setMyTrips', response.data);
            }
        },
        uploadAvatar: async ({commit}, file) => {
            const formData  = new FormData();
            formData.append('avatar', file);

            const response = await AXIOS.put('/api/profile', formData);
            commit('changeAvatarUrl', response.data.url);
        },
        loadTrips: async ({commit}) => {
            const response = await AXIOS.get('/api/trip/all');
            commit('setTrips', response.data)
        },
    }
});

export default store;
