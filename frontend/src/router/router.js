import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store/store'

import Home from '@/pages/Home'
import Login from '@/pages/Login'
import Profile from '@/pages/Profile'
import Registration from '@/pages/Registration'
import Challenge from '@/pages/Challenge'
import AllChallenges from '@/pages/AllChallenges'
import MyChallenges from '@/pages/MyChallenges'
import NewChallenge from '@/pages/NewChallenge'
import Friends from '@/pages/Friends'
import Messages from '@/pages/Messages'
import BattleCry from '@/pages/BattleCry'
import Settings from '@/pages/Settings'
import Help from '@/pages/Help'
import User from '@/pages/User'
import LeaderBoard from '@/pages/LeaderBoard'
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
            path: '/trip/:challengeId',
            name: 'Challenge',
            component: Challenge,
            meta: {title: "Challenge"}
        },
        {
            path: '/catalogue',
            name: 'AllChallenges',
            component: AllChallenges,
            meta: {title: "Catalogue"}
        },
        {
            path: '/challenges',
            name: 'MyChallenges',
            component: MyChallenges,
            meta: {title: "My challenges"}
        },
        {
            path: '/new',
            name: 'NewChallenge',
            component: NewChallenge
        },
        {
            path: '/friends',
            name: 'Friends',
            component: Friends,
        },
        {
            path: '/messages',
            name: 'Messages',
            component: Messages,
        },
        {
            path: '/help',
            name: 'Help',
            component: Help
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


