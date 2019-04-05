import Vue from 'vue'
import App from '@/App'
import router from '@/router/router'
import store from '@/store/store'
import {AXIOS} from '@/api/http-common'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont'

AXIOS.defaults.withCredentials = true;
Vue.config.productionTip = false;
Vue.prototype.$axios = AXIOS;

AXIOS.interceptors.response.use(response => {
        return Promise.resolve(response)
    },
    error => {
        if (error.response.status === 401) {
            console.log('Unauthorized, logging out ...');
            store.dispatch('userSignOut')
            router.replace('signIn')
            return Promise.reject(error)
        } else {
            return Promise.reject(error.response);
        }
    });

Vue.use(Vuetify);

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');

