import Vue from 'vue'
import Vuex from 'vuex'
import {AXIOS} from '../api/http-common'
import router from '@/router/router'
import {EventBus} from '../event-bus.js'

Vue.use(Vuex);

let store = new Vuex.Store({
    state: {
        token: localStorage.getItem('token') || '',
        status: '',
        isAuthenticated: !!localStorage.getItem('token'),
        user: {
            username: '',
            email: '',
            pictureURL: 'https://cdn.vuetifyjs.com/images/john.jpg',
            password: '',

        }
    },
    getters: {
        isAuthenticated: state => state.isAuthenticated,
        getUser: state => state.user
    },
    mutations: {
        setUser(state, payload) {
            state.user = payload
        },
        clearAuth() {
            localStorage.removeItem('token')
        },
        isAuthenticated(state) {
            state.isAuthenticated = true;
        }

    },
    actions: {
        userSignIn({commit}, payload) {
            var form = new FormData();
            form.append('grant_type', 'password');
            form.append('username', payload.email);
            form.append('password', payload.password);

            AXIOS.post('/oauth/token', form,
                {
                    headers: {
                        'Access-Control-Allow-Origin': 'http://localhost:8080',
                        'Access-Control-Allow-Credentials': 'true'
                    }
                })
                .then(response => {
                    console.log(response.data);
                    console.log(response.status);
                    const token = response.data;
                    console.log(token);
                    localStorage.setItem('token', token);

                    if (response.status === 200) {
                        console.log(this.state.isAuthenticated);
                        this.commit('isAuthenticated');
                        console.log(this.state.isAuthenticated);
                        router.push('/profile');
                    }
                })
                .catch(error => {
                    console.log('error');
                    console.log(error.response);
                    console.log(error.message);
                    commit('clearAuth');
                    console.log("isAuth: " + this.state.isAuthenticated)
                })
        },
        userSignOut({commit}) {
            commit('clearAuth');
            EventBus.$emit('authenticated', 'User not authenticated')
            router.push('/login')
        }
    }
});

export default store;
