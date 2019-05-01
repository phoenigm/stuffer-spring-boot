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
                        Trips I'm in
                    </v-tab>
                    <v-tab>
                        My requests
                    </v-tab>
                    <v-tab>
                        Requests for me
                    </v-tab>
                    <v-tab-item>
                        <v-layout row wrap>
                            <v-flex v-for="trip in trips" :key="trip.id" xs12 mt-2>
                                <TripCard :trip="trip" :color="'purple'" :text="'headline'"/>
                            </v-flex>
                        </v-layout>
                    </v-tab-item>

                    <v-tab-item>
                        <v-layout row wrap>
                            <v-flex v-for="trip in joinedTrips" :key="trip.id" xs12 mt-2>
                                <TripCard :trip="trip" :color="'purple'" :text="'headline'" :author="trip.author"/>
                            </v-flex>
                        </v-layout>
                    </v-tab-item>
                    <v-tab-item>
                        <v-layout column>
                            <v-flex v-for="req in myRequests" mt-2>
                                <TripRequest :trip-request="req" :is-my-request="true"/>
                            </v-flex>
                        </v-layout>
                    </v-tab-item>

                    <v-tab-item>
                        <v-layout column>
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
    import TripCard from "../components/TripCard";
    import {AXIOS} from "../api/http-common";
    import TripRequest from "../components/TripRequest";

    export default {
        name: "MyTrips",
        components: {TripRequest, TripCard, NavigationBar},

        data() {
            return {
                tab: null,
                trips: this.$store.getters.getMyTrips,
                joinedTrips: null,
                requests: null,
                myRequests: null,
            }
        },

        methods: {
            processRequest(id, status) {
                const requestConfirmation = {
                    status: id,
                    tripRequestId: status
                };
                AXIOS.post('/api/request/process', requestConfirmation)
                    .then(response => {

                    }).catch(error => {
                    console.log(error);
                })
            }
        },

       /* computed: {
            trips() {
                return this.$store.getters.getMyTrips;
            }
        },*/

        beforeMount() {
            this.$store.dispatch('loadMyTrips')
        },

        mounted() {

            AXIOS.get('/api/trip/joined')
                .then(response => {
                    console.log(response.data);
                    this.joinedTrips = response.data;
                }).catch(error => {
                console.log(error);
            });

            AXIOS.get('/api/request/toMe')
                .then(response => {
                    console.log(response.data)
                    this.requests = response.data;
                }).catch(error => {
                console.log(error);
            });

            AXIOS.get('/api/request/my')
                .then(response => {
                    console.log(response.data)
                    this.myRequests = response.data;
                }).catch(error => {
                console.log(error);
            })
        }

    }
</script>

<style scoped>

</style>
