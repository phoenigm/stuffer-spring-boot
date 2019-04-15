<template>
    <v-card
            color="purple lighten-1"
    >
        <v-card-title>
            <v-layout justify-center>
                <span class="title font-weight-light">Уфа - Казань</span>
            </v-layout>
        </v-card-title>

        <v-card-text class="headline font-weight-bold">

            <v-layout row>
                <v-flex>
                    <v-layout column>
                        <v-flex>
                            <span>Departure date:  {{ trip.departureDate }}</span>
                        </v-flex>
                        <v-flex>
                            <span>Arrival date:  {{ trip.arrivalDate }}</span>
                        </v-flex>
                    </v-layout>
                </v-flex>

                <v-flex>
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

        <v-card-actions>
            <v-list-tile class="grow">
                <v-list-tile-avatar color="grey darken-3">
                    <v-img
                            class="elevation-5"
                            :src=trip.avatar
                    ></v-img>
                </v-list-tile-avatar>

                <v-list-tile-content>
                    <v-list-tile-title>Азат Мухаметзянов</v-list-tile-title>
                </v-list-tile-content>

            </v-list-tile>


        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        name: "ChallengeCard",
        props: ['trip'],
    }
</script>

<style scoped>

</style>
