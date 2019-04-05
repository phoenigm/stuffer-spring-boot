<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout row justify-center>
                    <v-flex xs12 sm8>

                        <v-list two-line>
                            <template v-for="(item, index) in items">
                                <v-subheader
                                        v-if="item.header"
                                        :key="item.header"
                                >
                                    {{ item.header }}
                                </v-subheader>

                                <v-divider
                                        v-else-if="item.divider"
                                        :key="index"
                                        :inset="item.inset"
                                ></v-divider>

                                <v-list-tile
                                        v-else
                                        :key="item.title"
                                        avatar
                                        @click=""
                                >
                                    <v-list-tile-avatar>
                                        <img :src="item.avatar">
                                    </v-list-tile-avatar>

                                    <v-list-tile-content>
                                        <v-list-tile-title v-html="item.title"></v-list-tile-title>
                                        <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
                                    </v-list-tile-content>
                                </v-list-tile>
                            </template>
                        </v-list>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-content>

    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";

    export default {
        name: "Messages",
        components: {NavigationBar},
        data() {
            return {
                items: [
                    {header: 'Today'},
                    {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
                        title: 'phoenigm',
                        subtitle: " I'll be in your neighborhood doing errands this weekend. Do you want to hang out?"
                    },
                    {divider: true, inset: true},
                    {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
                        title: 'flisoch <span class="grey--text text--lighten-1">4</span>',
                        subtitle: " Wish I could come, but I'm out of town this weekend."
                    },
                    {divider: true, inset: true},
                    {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
                        title: 'celmas',
                        subtitle: "Do you have Paris recommendations? Have you ever been?"
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>
