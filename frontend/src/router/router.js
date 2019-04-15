import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store/store'

import Home from '@/pages/Home'
import Login from '@/pages/Login'
import Profile from '@/pages/Profile'
import Registration from '@/pages/Registration'
import Trip from '@/pages/Trip'
import AllTrips from '@/pages/AllTrips'
import MyTrips from '@/pages/MyTrips'
import NewTrip from '@/pages/NewTrip'
import Messages from '@/pages/Messages'
import Settings from '@/pages/Settings'
import User from '@/pages/User'
import NotFoundPage from '@/pages/NotFoundPage'

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home,
            meta: {nonRequiresAuth: true}
        },
        {
            path: '/profile',
            name: 'Profile',
            component: Profile,
        },
        {
            path: '/login',
            name: 'Login',
            component: Login,
            meta: {nonRequiresAuth: true}
        },
        {
            path: '/registration',
            name: 'Registration',
            component: Registration,
            meta: {nonRequiresAuth: true}
        },
        {
            path: '/trip/:tripId',
            name: 'Trip',
            component: Trip,
            meta: {title: "Trip"},
            props: true
        },
        {
            path: '/catalogue',
            name: 'AllTrips',
            component: AllTrips,
            meta: {title: "Catalogue"}
        },
        {
            path: '/trips',
            name: 'MyTrips',
            component: MyTrips,
            meta: {title: "MyTrips"}
        },
        {
            path: '/new',
            name: 'NewTrip',
            component: NewTrip
        },
        {
            path: '/messages',
            name: 'Messages',
            component: Messages,
        },
        {
            path: '/settings',
            name: 'Settings',
            component: Settings
        },
        {
            path: '/user/:userId',
            name: 'User',
            component: User,
            props: true
        },
        {
            path: '/404',
            name: 'NotFoundPage',
            component: NotFoundPage
        }

    ]
});

router.beforeEach((to, from, next) => {
    const requiresAuth = !to.matched.some(record => record.meta.nonRequiresAuth);
    const isAuthenticated = store.getters['isAuthenticated'];

    console.log('require auth: ' + requiresAuth + '\nis auth:  ' + isAuthenticated);
    if (requiresAuth && !isAuthenticated) {
        next('/login')
    } else if (!requiresAuth && isAuthenticated) {
        next('/profile')
    } else {
        next()
    }
});

export default router


