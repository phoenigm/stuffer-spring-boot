<template>
    <v-app>
        <NavigationBar/>
        <v-content>
            <v-container>
                <v-layout row>
                    <v-flex xs7 mr-3>
                        <v-card
                                class="hide-overflow"
                                color="purple lighten-1"
                                dark
                        >
                            <v-toolbar
                                    card
                                    color="purple"
                            >
                                <v-icon>fas fa-suitcase</v-icon>
                                <v-toolbar-title class="font-weight-light">Information about trip</v-toolbar-title>

                            </v-toolbar>
                            <v-card-text class="title">
                                <v-layout row>
                                    <v-flex>
                                        <v-layout column>
                                            <v-flex my-2>
                                                <span>Departure date:  {{trip.departureDate}}</span>
                                            </v-flex>
                                            <v-flex my-2>
                                                <span>Arrival date:  {{trip.arrivalDate}}</span>
                                            </v-flex>
                                            <v-flex my-2>
                                                <span>From:  {{trip.departurePoint}}</span>
                                            </v-flex>
                                            <v-flex my-2>
                                                <span>To: {{trip.deliveryPoint}}</span>
                                            </v-flex>
                                        </v-layout>
                                    </v-flex>

                                </v-layout>
                                <v-divider>

                                </v-divider>
                                <v-layout>
                                    <v-flex my-2>
                                        <span>
                                            <v-icon>fas fa-money-bill-wave</v-icon>
                                            Price: {{ trip.price }}
                                        </span>

                                    </v-flex>
                                </v-layout>
                                <v-divider/>

                                <v-layout>
                                    <v-flex>
                                        <v-card color="purple lighten-2">
                                            <v-card-title>
                                                Description
                                            </v-card-title>
                                            <v-card-text>
                                                {{ trip.info }}
                                            </v-card-text>
                                        </v-card>
                                    </v-flex>
                                </v-layout>


                            </v-card-text>

                        </v-card>
                    </v-flex>
                    <v-flex>
                        <v-card
                                class="hide-overflow"
                                color="purple lighten-1"
                                dark
                        >
                            <v-toolbar
                                    card
                                    color="purple"
                            >
                                <v-icon>fas fa-id-card</v-icon>
                                <v-toolbar-title class="font-weight-light">Information about driver</v-toolbar-title>
                            </v-toolbar>
                            <v-card-text>
                                <div class="text-sm-center mb-2">

                                <span class="headline">
                                    {{trip.author.firstName}} {{trip.author.lastName}}
                                </span>
                                </div>
                                <v-layout row>
                                    <v-flex>
                                        <v-layout column>
                                            <v-flex>
                                                <v-avatar
                                                        :tile="true"
                                                        :size="240"
                                                        color="yellow lighten-4"
                                                >
                                                    <img src="https://pp.userapi.com/c848536/v848536366/131886/EYolGIZVWhg.jpg"
                                                         alt="avatar">
                                                </v-avatar>
                                            </v-flex>
                                        </v-layout>
                                    </v-flex>
                                    <v-flex ml-2>
                                        <v-layout>
                                            <v-layout column>

                                                <v-flex my-2>
                                                    Driver rating:
                                                    <span class="grey--text text--lighten-2 caption mr-2">{{ rating }}</span>
                                                    <v-rating
                                                            v-model="rating"
                                                            background-color="white"
                                                            color="yellow accent-4"
                                                            readonly="true"
                                                            dense
                                                            half-increments
                                                            hover
                                                            size="18"
                                                    ></v-rating>
                                                </v-flex>


                                                <v-flex my-2>
                                                    Last trip: 20.03.2019
                                                </v-flex>

                                                <v-flex my-2>
                                                    Number of trips: 6
                                                </v-flex>

                                                <v-flex my-2>
                                                    Number of reviews: 6
                                                </v-flex>

                                                <v-flex my-2>
                                                    Email: {{ trip.author.email}}
                                                </v-flex>

                                                <v-flex my-2>
                                                    Phone number: {{ trip.author.phoneNumber}}
                                                </v-flex>

                                            </v-layout>
                                        </v-layout>
                                    </v-flex>
                                </v-layout>

                            </v-card-text>
                            <v-divider></v-divider>
                            <v-card-actions>
                                <v-spacer/>
                                <router-link class="" tag="v-btn" :to="'/user/' + trip.author.id" >
                                    Go to profile
                                    <v-icon> fas fa-angle-right</v-icon>
                                </router-link>

                            </v-card-actions>
                        </v-card>
                    </v-flex>


                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import {AXIOS} from "../api/http-common";

    export default {
        name: "Challenge",
        components: {NavigationBar},
        props: {
            disabled: Boolean,
        },

        data() {
            return {
                hasSaved: false,
                isEditing: null,
                model: null,
                rating: 4.11,
                numberOfTrips: 0,

                challenge: null,


                trip: {}
            }
        },

        mounted() {
            AXIOS.get('/api/trip/' + this.$route.params.tripId)
                .then(response => {
                    console.log(response.data);
                    this.trip = response.data;
                }).catch(error => {
                console.log(error)
            })
        }
    }
</script>

<style scoped>

</style>
