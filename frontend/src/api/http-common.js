import axios from 'axios'
import router from '@/router/router'
import store from '@/store/store'

export const AXIOS = axios.create({
    baseURL: process.env.NODE_ENV === 'development' ? 'http://phoenigm:phoenigm@localhost:8088' : 'https://stufffer.herokuapp.com',
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8080',
    },
    withCredentials: true
});

AXIOS.interceptors.request.use(function (config) {
    const isAuth = store.getters['isAuthenticated'];

    console.log('Token is: ' + localStorage.getItem('token'));

    if (isAuth) {
        config.headers.authorization = 'Bearer ' + localStorage.getItem('token');
    }
    console.log('req intercept success: ');
    console.log(config);

    return config;
}, function (error) {
    console.log('req intercept error: ');
    console.log(error);
    return Promise.reject(error);
});

AXIOS.interceptors.response.use(function (response) {
    console.log('response interceptor: success');
    console.log(response);
    return response;
}, function (error) {
    console.log('response interceptor: error' + error);
    console.log(error.response);

    if (error.response.status === 401) {
        console.log('Unauthorized, logging out ...');
        store.dispatch('logout');
        router.replace('/login');
    }

    return Promise.reject(error.response);
});



