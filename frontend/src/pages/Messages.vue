<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout row justify-center>
                    <v-flex xs12 sm9>

                        <v-list two-line>
                            <template v-for="item in items" >
                                <v-divider/>
                                <v-list-tile
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
                    {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
                        title: 'Azat Mukhametzyanov',
                    },
                    {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
                        title: 'Azat Mukhametzyanov',
                    },
                    {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
                        title: 'Azat Mukhametzyanov',
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>
