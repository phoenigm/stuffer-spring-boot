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
                                    <router-link v-if="isMyRequest" tag="img" :to="'/user/' + tripRequest.driver.id"
                                                 class="elevation-5"
                                                 :src="tripRequest.driver.avatarUrl">
                                    </router-link>
                                    <router-link v-else tag="img" :to="'/user/' + tripRequest.stuffer.id"
                                                 class="elevation-5"
                                                 :src="tripRequest.stuffer.avatarUrl">
                                    </router-link>
                                </v-list-tile-avatar>
                                <v-list-tile-content>
                                    <v-list-tile-title v-if="isMyRequest">{{tripRequest.driver.firstName}} {{tripRequest.driver.lastName}}
                                    </v-list-tile-title>
                                    <v-list-tile-title v-else>{{tripRequest.stuffer.firstName}} {{tripRequest.stuffer.lastName}}
                                    </v-list-tile-title>
                                </v-list-tile-content>
                            </v-list-tile>
                        </v-flex>
                        <v-flex class="title" ml-3>
                            {{tripRequest.requestText}}
                        </v-flex>

                    </v-layout>
                </v-flex>

                <v-flex shrink>
                    <v-spacer/>
                    <v-btn v-if="!isMyRequest" fab dark color="green" @click="processRequest(tripRequest.id, 'ACCEPTED')" >
                        <v-icon dark>fas fa-check</v-icon>
                    </v-btn>
                    <v-btn v-if="!isMyRequest" fab dark color="red" @click="processRequest(tripRequest.id, 'DECLINED')">
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
    import {AXIOS} from '../api/http-common'
    export default {
        name: "TripRequest",
        props: ['tripRequest', 'isMyRequest'],

        methods: {
            processRequest(id, status) {
                this.requestConfirmation = {
                    status: status,
                    tripRequestId: id
                };
                AXIOS.post('/api/request/process', this.requestConfirmation)
                    .then(response => {

                    }).catch(error => {
                    console.log(error);
                })
            }
        }
    }
</script>

<style scoped>

</style>
