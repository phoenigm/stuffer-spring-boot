<template>
    <router-link tag="a" style="text-decoration: none" :to="'/trip/' + trip.id">

    <v-card
            :color="color"
    >
        <v-card-title>
            <v-layout justify-center>
                <span class="title font-weight-light">{{trip.departurePoint.split(',')[0]}} - {{trip.deliveryPoint.split(',')[0]}}</span>
            </v-layout>
        </v-card-title>

        <v-card-text class=" font-weight-bold" :class="text">

            <v-layout row wrap>
                <v-flex md6 xs12>
                    <v-layout column>
                        <v-flex>
                            <span>Departure date:  {{ trip.departureDate }}</span>
                        </v-flex>
                        <v-flex>
                            <span>Arrival date:  {{ trip.arrivalDate }}</span>
                        </v-flex>
                    </v-layout>
                </v-flex>

                <v-flex md6 xs12>
                    <v-layout column>
                        <v-flex>
                            <span>From: {{ trip.departurePoint }}</span>
                        </v-flex>
                        <v-flex>
                            <span>To: {{trip.deliveryPoint}}</span>
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>

        </v-card-text>


        <v-divider light></v-divider>

        <v-card-actions v-if="author">
            <v-list-tile class="grow">
                <v-list-tile-avatar color="grey darken-3">
                    <v-img
                            class="elevation-5"
                            :src=author.avatarUrl
                    ></v-img>
                </v-list-tile-avatar>

                <v-list-tile-content>
                    <v-list-tile-title>{{author.firstName}} {{author.lastName}}</v-list-tile-title>
                </v-list-tile-content>

            </v-list-tile>


        </v-card-actions>
    </v-card>
    </router-link>
</template>

<script>
    export default {
        name: "TripCard",
        props: ['trip', 'text', 'color', 'author'],
    }
</script>

<style scoped>

</style>
