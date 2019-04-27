<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container fluid>
                <v-container fluid grid-list-sm>
                    <v-layout row wrap>

                        <v-flex v-for="trip in trips" :key="trip.id" xs12>
                            <TripCard :trip="trip" :author="trip.author" :color="'purple'" :text="'headline'"/>
                        </v-flex>
                    </v-layout>
                </v-container>

            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import TripCard from "../components/TripCard";
    import {AXIOS} from "../api/http-common";
    import {mapGetters} from "vuex";

    export default {
        name: "AllChallenges",
        components: {NavigationBar, TripCard},

        computed: {
            ...mapGetters({
                trips: 'getTrips'
            })
        },

        mounted() {
            AXIOS.get('/api/trip/all')
                .then(response => {
                    console.log(response.data);
                    this.$store.commit('setTrips', response.data);
                    this.trips = response.data;
                }).catch(error => {
                console.log(error);
            })
        }
    }
</script>

<style scoped>

</style>
