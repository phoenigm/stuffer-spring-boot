<template>
    <v-app>
        <v-parallax
                dark height="1080"
                :src="require('./../assets/home.jpg')"
        >
            <HomeToolbar/>
            <v-layout
                    align-center
                    column
                    justify-center
            >
                <h1 class="display-2 font-weight-thin mb-3">Stuffer</h1>
                <h3 class="subheading">We help people save time and money to stuff traffic</h3>
            </v-layout>
            <v-layout align-center justify-center row>
                <v-flex xs4>
                    <v-layout align-center justify-center column>
                        <v-flex display-1 mb-3>
                            Earn money
                        </v-flex>
                        <v-flex title class="offset-xs2 xs4">
                            Are you going to trip? Publish it to find stuffers and they will you to stuff delivery!
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex xs4>
                    <v-layout align-center justify-center column>
                        <v-flex display-1 mb-3>
                            Save time
                        </v-flex>
                        <v-flex title class=" xs4">
                            Don't waste time to deliver stuff
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex xs4>
                    <v-layout align-center justify-center column>
                        <v-flex display-1 mb-3>
                            Rating
                        </v-flex>
                        <v-flex title class="offset-xs2 xs4">
                            Rate drivers to help other stuffers choose the driver
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>
        </v-parallax>
        <Footer/>
    </v-app>
</template>

<script>
    import Footer from "../components/Footer";
    import HomeToolbar from "../components/HomeToolbar";

    export default {
        name: 'hello',
        components: {HomeToolbar, Footer},

        created() {
            document.title = "Home";
        }
    }
</script>

<style scoped>
</style>
