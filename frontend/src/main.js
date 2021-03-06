import Vue from 'vue'
import App from '@/App'
import router from '@/router/router'
import store from '@/store/store'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont'

Vue.config.productionTip = false;

Vue.use(Vuetify);

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');

