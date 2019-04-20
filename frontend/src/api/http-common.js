import axios from 'axios'
import router from '@/router/router'
import store from '@/store/store'

export const AXIOS = axios.create({
    baseURL: 'http://phoenigm:phoenigm@localhost:8088',
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8080',
    },
});

AXIOS.interceptors.request.use(function (config) {
    const isAuth = store.getters['isAuthenticated'];
    config.headers['Access-Control-Allow-Origin'] = 'http://localhost:8080';
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

const interceptor = AXIOS.interceptors.response.use(function (response) {
    console.log('response interceptor: success');
    console.log(response);
    return response;
}, function (error) {
    console.log('response interceptor: error' + error);
    console.log(error.response);
    const status = error.response ? error.response.status : null;

    if (status === 401) {
        const form = new FormData();
        form.append('grant_type', 'refresh_token');
        form.append('refresh_token', localStorage.getItem('refresh_token'));

        axios.interceptors.response.eject(interceptor);
        console.log('Token refreshing: ' + localStorage.getItem('refresh_token'));
        return AXIOS.post('/oauth/token', form)
            .then(response => {
                const token = response.data['access_token'];
                localStorage.removeItem('token');
                localStorage.setItem('token', token);
                localStorage.setItem('refresh_token', response.data['refresh_token']);
                this.$store.commit('setToken', token);
                error.response.config.headers['Authorization'] = 'Bearer ' + token;
                console.log('new Token!' + token);
                return axios(error.response.config);
            }).catch(error => {
                console.log('Unauthorized, logging out ...');
                store.dispatch('logout');
                router.replace('/login');
                return Promise.reject(error);
            })
    }

    return Promise.reject(error.response);
});



