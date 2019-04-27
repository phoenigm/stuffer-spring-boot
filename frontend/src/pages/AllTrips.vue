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
    import {mapGetters} from "vuex";

    export default {
        name: "AllTrips",
        components: {NavigationBar, TripCard},

        computed: {
            trips() {
                return this.$store.getters.getTrips;
            }

        },

        mounted() {
            this.$store.dispatch('loadTrips');
        }
    }
</script>

<style scoped>

</style>
