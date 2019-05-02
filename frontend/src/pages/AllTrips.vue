<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container fluid>
                <v-container fluid grid-list-sm>
                    <v-layout row>
                        <v-flex xs6>
                            <v-autocomplete
                                    v-model="departureLocality"
                                    :loading="isLoading1"
                                    :search-input.sync="searchDepartureLocality"
                                    hide-no-data
                                    hide-selected
                                    label="Departure locality"
                                    placeholder="Start typing locality"
                                    return-object
                            ></v-autocomplete>
                        </v-flex>
                        <v-flex xs6>
                            <v-autocomplete
                                    v-model="deliveryLocality"
                                    :loading="isLoading3"
                                    :search-input.sync="searchDeliveryLocality"
                                    hide-no-data
                                    hide-selected
                                    label="Delivery locality"
                                    placeholder="Start typing locality"
                                    return-object
                            ></v-autocomplete>
                        </v-flex>
                    </v-layout>
                    <v-layout row wrap>
                        <v-flex v-if="searchResults.length !== 0" v-for="trip in searchResults" :key="trip.id" xs12>
                            <TripCard :trip="trip" :author="trip.author" :color="'purple'" :text="'headline'"/>
                        </v-flex>
                        <v-flex v-else>
                            Ничего не найдено
                        </v-flex>
                    </v-layout>
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
    import {AXIOS} from "../api/http-common"

    export default {
        name: "AllTrips",
        components: {NavigationBar, TripCard},

        data() {
            return {
                departureLocality: null,
                deliveryLocality: null,

                searchResults: [],

                searchDepartureLocality: null,
                searchDeliveryLocality: null,
                isLoading1: false,
                isLoading3: false,
            }
        },

        computed: {
            trips() {
                return this.$store.getters.getTrips;
            },
        },

        watch: {
            searchDeliveryLocality(val) {
                if (this.isLoading3) return;
                this.isLoading3 = true;

                AXIOS.get(`/api/trip/search?${this.departureLocality === null ? '' : 'from=' + this.departureLocality + '&'}to=${val}`)
                    .then(res => {
                        this.searchResults = res.data;
                    })
                    .catch(err => {
                    })
                    .finally(() => (this.isLoading3 = false))
            },

            searchDepartureLocality(val) {
                if (this.isLoading1) return;
                this.isLoading1 = true;

                AXIOS.get(`/api/trip/search?from=${val}${this.deliveryLocality === null ? '' : '&to=' + this.deliveryLocality}`)
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
