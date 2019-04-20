<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-tabs color="purple darken-2"
                        dark grow
                        slider-color="red">
                    <v-tab>
                        My trips
                    </v-tab>
                    <v-tab>
                        Requests to stuff
                    </v-tab>
                    <v-tab-item>
                        <v-layout row wrap>
                            <v-flex v-for="trip in trips" :key="trip.id" xs12 mt-2>
                                <ChallengeCard :trip="trip" :color="'purple'" :text="'headline'"/>
                            </v-flex>
                        </v-layout>

                    </v-tab-item>
                    <v-tab-item>
                        <v-layout row>
                            <v-flex v-for="req in requests" mt-2>
                                <TripRequest :trip-request="req"/>
                            </v-flex>
                        </v-layout>
                    </v-tab-item>
                </v-tabs>

            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import ChallengeCard from "../components/ChallengeCard";
    import {AXIOS} from "../api/http-common";
    import TripRequest from "../components/TripRequest";

    export default {
        name: "MyChallenges",
        components: {TripRequest, ChallengeCard, NavigationBar},

        data() {
            return {
                tab: null,
                trips: null,
                requests: null,
            }
        },

        methods: {
            processRequest() {
                AXIOS.post('/api/request/process')
                    .then(response => {

                    }).catch(error => {
                        console.log(error);
                })
            }
        },

        mounted() {
            AXIOS.get('/api/trip/my')
                .then(response => {
                    console.log(response.data);
                    this.$store.commit('setTrips', response.data);
                    this.trips = response.data;
                }).catch(error => {
                console.log(error);
            });

            AXIOS.get('/api/request/toMe')
                .then(response => {
                    console.log(response.data)
                    this.requests = response.data;
                }).catch(error => {
                console.log(error);
            })
        }

    }
</script>

<style scoped>

</style>
