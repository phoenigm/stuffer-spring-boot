<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <v-navigation-drawer
                fixed
                clipped
                class="grey lighten-4"
                app
                v-model="drawer"
        >
            <v-list
                    dense
                    class="grey lighten-4"
            >
                <template v-for="(item, i) in items">
                    <v-layout
                            row
                            v-if="item.heading"
                            align-center
                            :key="i"
                    >
                        <v-flex xs6>
                            <v-subheader v-if="item.heading">
                                {{ item.heading }}
                            </v-subheader>
                        </v-flex>
                    </v-layout>
                    <v-divider
                            dark
                            v-else-if="item.divider"
                            class="my-3"
                            :key="i"
                    ></v-divider>
                    <v-list-tile
                            :key="i"
                            v-else
                            @click="goToPage(item.page)"
                    >
                        <v-list-tile-action>
                            <v-icon>{{ item.icon }}</v-icon>
                        </v-list-tile-action>
                        <v-list-tile-content>
                            <v-list-tile-title class="grey--text">
                                {{ item.text }}
                            </v-list-tile-title>
                        </v-list-tile-content>
                    </v-list-tile>
                </template>
            </v-list>
        </v-navigation-drawer>
        <v-toolbar color="amber" fixed app clipped-left>
            <v-toolbar-side-icon @click.native="drawer = !drawer"></v-toolbar-side-icon>
            <span class="title ml-3 mr-5">Stuffer&nbsp;<span class="text"></span></span>
            <v-layout align-center justify-center row fill-height>
                <v-flex>
                    <v-text-field
                            solo-inverted
                            flat
                            label="Search"
                            prepend-icon="search"
                    ></v-text-field>
                </v-flex>
            </v-layout>

            <v-spacer></v-spacer>

            <v-menu
                    v-model="menu"
                    :close-on-content-click="false"
                    :nudge-width="300"
                    offset-y
            >
                <template v-slot:activator="{ on }">
                    <v-btn icon
                           v-on="on">
                        <v-icon>notifications</v-icon>
                    </v-btn>

                </template>
                <div>
                    <v-list two-line>
                        <template v-for="(item, index) in notifications">
                            <v-subheader
                                    v-if="item.header"
                                    :key="item.header"
                            >
                                {{ item.header }}
                            </v-subheader>

                            <v-list-tile
                                    v-else
                                    :key="item.title"
                                    avatar
                                    @click=""
                            >
                                <v-list-tile-avatar>
                                    <v-img :src="item.avatar"/>
                                </v-list-tile-avatar>

                                <v-list-tile-content>
                                    <v-list-tile-title v-html="item.title"></v-list-tile-title>
                                    <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
                                </v-list-tile-content>
                            </v-list-tile>
                        </template>
                    </v-list>
                </div>
            </v-menu>


            <v-menu bottom left>
                <template v-slot:activator="{ on }">
                    <v-btn icon large v-on="on">
                        <v-avatar size="32px">
                            <img
                                    :src="user.avatarUrl"
                                    :alt="user.firstName"
                            >
                        </v-avatar>
                    </v-btn>
                </template>

                <v-list dense>
                    <v-list-tile
                            v-for="(item, i) in menuItems"
                            :key="i"
                            @click="goToPage(item.page)"
                    >
                        <v-divider
                                v-if="item.divider"
                                :key="index"
                                :inset="item.inset"
                        ></v-divider>
                        <v-list-tile-action>
                            <v-icon>{{ item.icon }}</v-icon>
                        </v-list-tile-action>
                        <v-list-tile-content>
                            <v-list-tile-title class="grey--text">
                                {{ item.text }}
                            </v-list-tile-title>
                        </v-list-tile-content>
                    </v-list-tile>
                </v-list>
            </v-menu>

        </v-toolbar>
    </div>
</template>

<script>
    export default {
        name: "NavigationMenu",
        data() {
            return {
                menu: false,
                drawer: null,
                items: [
                    {heading: 'Activity'},
                    {icon: 'fas fa-car', text: 'My trips', page: '/trips'},
                    {icon: 'fas fa-suitcase', text: 'Search', page: '/catalogue'},
                    {icon: 'fas fa-plus', text: 'Add new trip', page: '/new'},
                    {divider: true},
                    {icon: 'fas fa-cog', text: 'Settings', page: '/settings'},
                ],

                menuItems: [
                    {icon: 'fas fa-user', text: 'Profile', page: '/profile'},
                    {icon: 'fas fa-sign-out-alt', text: 'Quit', page: '/quit'},
                ],

                notifications: [
                    {header: 'Today'},
                    {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
                        title: 'Brunch this weekend?',
                        subtitle: "<span class='text--primary'>Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?"
                    },
                    {divider: true, inset: true},
                    {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
                        title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
                        subtitle: "<span class='text--primary'>to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend."
                    },
                    {divider: true, inset: true},
                    {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
                        title: 'Oui oui',
                        subtitle: "<span class='text--primary'>Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?"
                    }
                ],
            }
        },

        computed: {
            user() {
                return this.$store.getters['getUser'];
            }
        },

        methods: {
            goToPage(page) {
                if (page === '/quit') {
                    this.$store.dispatch('logout');
                }
                this.$router.push(page);
            },
            logout() {
                this.$store.dispatch('logout');
                this.$router.replace('/login');
            }
        },

        beforeMount() {
            if (this.$store.state.user.avatarUrl === undefined) {
                this.$store.dispatch('myProfile')
            }
        }
    }
</script>

<style scoped>
</style>
