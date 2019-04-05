<template>
    <v-app>

        <v-jumbotron
                dark
                height="100%"
                :src="require('./../assets/home.jpg')"
        >
            <HomeToolbar/>
            <v-container fill-height>
                <v-layout align-center>
                    <v-flex text-xs-center>
                        <h3 class="display-3">Gradient over Image</h3>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-jumbotron>
        <v-container tag="section">
            <ServiceFeatures/>
        </v-container>



        <Footer/>
    </v-app>
</template>

<script>
    import Jumbotron from "../components/Jumbotron";
    import Footer from "../components/Footer";
    import ServiceFeatures from "../components/ServiceFeatures";
    import HomeToolbar from "../components/HomeToolbar";

    export default {
        name: 'hello',
        components: {HomeToolbar, ServiceFeatures, Footer, Jumbotron},

        created() {
            document.title = "Home";
        }
    }
</script>

<style scoped>
    .background {
        background-image: url('./../assets/home.jpg') !important;
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center;
    }
</style>
