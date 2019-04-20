<template>
    <v-hover>
    <v-card color="#ffc107" slot-scope="{hover}">

        <v-card-text>
            <v-layout row>
                <v-flex>
                    <v-layout column>
                        <v-flex>
                            <v-list-tile class="grow">
                                <v-list-tile-avatar color="grey darken-3">
                                    <router-link tag="img" :to="'/user/' + tripRequest.stuffer.id"
                                                 class="elevation-5"
                                                 :src="tripRequest.stuffer.avatarUrl">
                                    </router-link>
                                </v-list-tile-avatar>
                                <v-list-tile-content>
                                    <v-list-tile-title>{{tripRequest.stuffer.firstName}} {{tripRequest.stuffer.lastName}}
                                    </v-list-tile-title>
                                </v-list-tile-content>
                                {{tripRequest.requestText}}
                            </v-list-tile>
                        </v-flex>
                        <v-flex class="title" ml-3>
                            {{tripRequest.requestText}}
                        </v-flex>

                    </v-layout>
                </v-flex>

                <v-flex shrink>
                    <v-spacer/>
                    <v-btn fab dark color="green">
                        <v-icon dark>fas fa-check</v-icon>
                    </v-btn>
                    <v-btn fab dark color="red">
                        <v-icon dark>fas fa-times</v-icon>
                    </v-btn>
                </v-flex>
            </v-layout>
        </v-card-text>
        <v-expand-transition>
            <v-btn color="purple" dark block v-if="hover" :to="'/trip/' + tripRequest.trip.id">
                Go to trip
            </v-btn>
        </v-expand-transition>
    </v-card>
    </v-hover>
</template>

<script>
    export default {
        name: "TripRequest",
        props: ['tripRequest']
    }
</script>

<style scoped>

</style>
