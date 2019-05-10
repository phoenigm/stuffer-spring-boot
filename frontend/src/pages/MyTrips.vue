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
                AXIOS.put('/api/requests', requestConfirmation)
                    .then(response => {

                    }).catch(error => {
                    console.log(error);
                })
            }
        },

        computed: {
            trips() {
                return this.$store.getters.getMyTrips;
            }
        },

        beforeMount() {
            this.$store.dispatch('loadMyTrips')
        },

        async mounted() {
            this.joinedTrips = await AXIOS.get('/api/trips?type=joined');
            this.requests = await AXIOS.get('/api/requests?filter=toMe');
            this.myRequests = await AXIOS.get('/api/requests?filter=my');
        }
    }
</script>

<style scoped>

</style>
