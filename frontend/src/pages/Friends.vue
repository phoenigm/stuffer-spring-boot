<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout row justify-center >
                    <v-flex xs12 sm8>
                        <v-tabs
                                centered
                                color="cyan"
                                dark
                                grow
                        >
                            <v-tabs-slider color="yellow"></v-tabs-slider>

                            <v-tab href="#tab-1">
                                All friends
                            </v-tab>

                            <v-tab href="#tab-2">
                                Online friends
                            </v-tab>


                            <v-tab-item
                                    v-for="i in 2"
                                    :key="i"
                                    :value="'tab-' + i"
                            >
                                    <v-list subheader>
                                        <v-list-tile
                                                v-for="item in items"
                                                :key="item.title"
                                                avatar
                                                @click=""
                                        >
                                            <v-list-tile-avatar>
                                                <v-img :src="item.avatar" sizes="" ></v-img>
                                            </v-list-tile-avatar>

                                            <v-list-tile-content>
                                                <v-list-tile-title v-html="item.title"></v-list-tile-title>
                                            </v-list-tile-content>

                                            <v-list-tile-action>
                                                <v-icon :color="item.active ? 'teal' : 'grey'">chat_bubble</v-icon>
                                            </v-list-tile-action>
                                        </v-list-tile>
                                    </v-list>


                            </v-tab-item>
                        </v-tabs>

                    </v-flex>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";

    export default {
        name: "Friends",
        components: {NavigationBar},
        data() {
            return {
                items: [
                    {active: true, title: 'Jason Oner', avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg'},
                    {active: true, title: 'Ranee Carlson', avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg'},
                    {title: 'Cindy Baker', avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg'},
                    {title: 'Ali Connors', avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg'}
                ],
            }
        }
    }
</script>

<style scoped>

</style>
