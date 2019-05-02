<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container fluid>
                <v-container fluid grid-list-sm>
                    <v-layout row>
                        <v-flex xs6>
                            <v-text-field
                                    v-on:input="searchDepartureLocality"
                                    v-model="departureLocality"
                                    :loading="isLoading1"

                                    hide-no-data
                                    hide-selected
                                    label="Departure locality"
                                    placeholder="Start typing locality"
                                    return-object
                            ></v-text-field>
                        </v-flex>
                        <v-flex xs6>
                            <v-text-field
                                    v-on:input="searchDeliveryLocality"
                                    v-model="deliveryLocality"
                                    :loading="isLoading3"
                                    hide-no-data
                                    hide-selected
                                    label="Delivery locality"
                                    placeholder="Start typing locality"
                                    return-object
                            ></v-text-field>
                        </v-flex>
                    </v-layout>
                    <v-layout row wrap>
                        <v-flex v-for="trip in searchResults" :key="trip.id" xs12>
                            <TripCard :trip="trip" :author="trip.author" :color="'purple'" :text="'headline'"/>
                        </v-flex>
                        <v-flex v-if="searchResults.length === 0">
                            <h4 class="title">Nothing was found by your parameters </h4>
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
    import {AXIOS} from "../api/http-common"

    export default {
        name: "AllTrips",
        components: {NavigationBar, TripCard},

        data() {
            return {
                searchResults: [],
                deliveryLocality: null,
                departureLocality: null,

                isLoading1: false,
                isLoading3: false,
            }
        },

        computed: {
            trips() {
                return this.$store.getters.getTrips;
            },
        },
        methods: {
            searchDeliveryLocality() {
                if (this.isLoading3) return;
                this.isLoading3 = true;

                AXIOS.get(`/api/trip/search?${this.departureLocality === null ? '' : 'from=' + this.departureLocality + '&'}to=${this.deliveryLocality}`)
                    .then(res => {
                        this.searchResults = res.data;
                    })
                    .catch(err => {
                    })
                    .finally(() => (this.isLoading3 = false))
            },

            searchDepartureLocality() {
                if (this.isLoading1) return;
                this.isLoading1 = true;

                AXIOS.get(`/api/trip/search?from=${this.departureLocality}${this.deliveryLocality === null ? '' : '&to=' + this.deliveryLocality}`)
                    .then(res => {
                        this.searchResults = res.data;
                    })
                    .catch(err => {
                    })
                    .finally(() => (this.isLoading1 = false))
            },
        },

        mounted() {
            this.$store.dispatch('loadTrips');
        }
    }
</script>

<style scoped>

</style>
