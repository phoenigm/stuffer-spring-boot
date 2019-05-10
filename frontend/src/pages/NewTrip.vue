<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout align-start justify-center row fill-height>
                    <v-flex class="md8 xs12">
                        <v-card dark color="purple">
                            <v-form>
                                <v-container>
                                    <v-layout column justify-center wrap>
                                        <v-flex xs12 text-xs-center>
                                            <span class="display-2 ">Create new trip</span>
                                        </v-flex>
                                        <v-flex>
                                            <v-layout row>
                                                <v-flex xs6>
                                                    <v-autocomplete
                                                            v-model="departureRegion"
                                                            :items="departureRegions"
                                                            :loading="isLoading"
                                                            :search-input.sync="searchDepartureRegion"
                                                            color="white"
                                                            hide-no-data
                                                            hide-selected
                                                            label="Departure region"
                                                            placeholder="Start typing region you start trip"
                                                            prepend-icon="fas fa-globe-europe"
                                                            return-object
                                                            dense
                                                            no-data-text="Such region not found"
                                                    ></v-autocomplete>
                                                </v-flex>
                                                <v-flex xs6>
                                                    <v-autocomplete
                                                            v-model="departureLocality"
                                                            :items="departureLocalities"
                                                            :loading="isLoading1"
                                                            :search-input.sync="searchDepartureLocality"
                                                            color="white"
                                                            hide-selected
                                                            label="Departure locality"
                                                            placeholder="Start typing locality"
                                                            return-object
                                                            dense
                                                            no-data-text="Such locality not found"
                                                    ></v-autocomplete>
                                                </v-flex>
                                            </v-layout>
                                        </v-flex>
                                        <v-flex>
                                            <v-text-field
                                                    v-model="tripForm.departureAddress"
                                                    box
                                                    color="blue-grey lighten-2"
                                                    label="Departure address"
                                            ></v-text-field>
                                        </v-flex>

                                        <v-flex>
                                            <v-layout row>
                                                <v-flex xs6>
                                                    <v-autocomplete
                                                            v-model="deliveryRegion"
                                                            :items="deliveryRegions"
                                                            :loading="isLoading2"
                                                            :search-input.sync="searchDeliveryRegion"
                                                            color="white"
                                                            hide-no-data
                                                            hide-selected
                                                            label="Delivery region"
                                                            placeholder="Start typing region you go"
                                                            prepend-icon="fas fa-globe-europe"
                                                            return-object
                                                            dense
                                                            no-data-text="Such region not found"
                                                    ></v-autocomplete>
                                                </v-flex>
                                                <v-flex xs6>
                                                    <v-autocomplete
                                                            v-model="deliveryLocality"
                                                            :items="deliveryLocalities"
                                                            :loading="isLoading3"
                                                            :search-input.sync="searchDeliveryLocality"
                                                            color="white"
                                                            hide-no-data
                                                            hide-selected
                                                            label="Delivery locality"
                                                            placeholder="Start typing locality"
                                                            return-object
                                                            dense
                                                            no-data-text="Such locality not found"
                                                    ></v-autocomplete>
                                                </v-flex>
                                            </v-layout>
                                        </v-flex>

                                        <v-flex xs12>
                                            <v-text-field
                                                    v-model="tripForm.deliveryAddress"
                                                    box
                                                    color="blue-grey lighten-2"
                                                    label="Delivery address"
                                            ></v-text-field>


                                        </v-flex>
                                        <v-flex xs12>
                                            <v-textarea
                                                    v-model="tripForm.info"
                                                    auto-grow
                                                    box
                                                    color="blue-grey lighten-2"
                                                    label="Additional information"
                                                    rows="3"
                                            ></v-textarea>
                                        </v-flex>
                                        <v-flex xs12>

                                            <v-text-field
                                                    v-model="tripForm.price"
                                                    box
                                                    color="blue-grey lighten-2"
                                                    label="Price"
                                            ></v-text-field>


                                        </v-flex>

                                        <v-flex>
                                            <v-layout row>
                                                <v-flex>
                                                    <v-menu
                                                            v-model="menu"
                                                            :close-on-content-click="false"
                                                            :nudge-right="40"
                                                            lazy
                                                            transition="scale-transition"
                                                            offset-y
                                                            full-width
                                                            min-width="290px"
                                                    >
                                                        <template v-slot:activator="{ on }">
                                                            <v-text-field
                                                                    v-model="tripForm.departureDate"
                                                                    label="Departure date"
                                                                    prepend-icon="event"
                                                                    readonly
                                                                    v-on="on"
                                                            ></v-text-field>
                                                        </template>
                                                        <v-date-picker v-model="tripForm.departureDate"
                                                                       @input="menu = false"></v-date-picker>
                                                    </v-menu>
                                                </v-flex>

                                                <v-flex>
                                                    <v-text-field v-model="tripForm.departureTime" mask="time"
                                                                  label="Departure time"></v-text-field>
                                                </v-flex>
                                            </v-layout>
                                        </v-flex>


                                        <v-flex>
                                            <v-layout row>
                                                <v-flex>
                                                    <v-menu
                                                            v-model="menu2"
                                                            :close-on-content-click="false"
                                                            :nudge-right="40"
                                                            lazy
                                                            transition="scale-transition"
                                                            offset-y
                                                            full-width
                                                            min-width="290px"
                                                    >
                                                        <template v-slot:activator="{ on }">
                                                            <v-text-field
                                                                    v-model="tripForm.arrivalDate"
                                                                    label="Arrival date"
                                                                    prepend-icon="event"
                                                                    readonly
                                                                    v-on="on"
                                                            ></v-text-field>
                                                        </template>
                                                        <v-date-picker v-model="tripForm.arrivalDate"
                                                                       @input="menu2 = false"></v-date-picker>
                                                    </v-menu>
                                                </v-flex>

                                                <v-flex>
                                                    <v-text-field v-model="tripForm.arrivalTime" mask="time"
                                                                  label="Arrival time"></v-text-field>
                                                </v-flex>
                                            </v-layout>
                                        </v-flex>

                                    </v-layout>
                                </v-container>
                            </v-form>
                            <v-divider></v-divider>
                            <v-card-actions>

                                <v-spacer></v-spacer>
                                <v-btn
                                        color="primary"
                                        depressed
                                        @click="createTrip"
                                >
                                    Create trip
                                </v-btn>
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
        name: "NewTrip",
        components: {NavigationBar},
        data() {
            return {
                tripForm: {
                    info: null,
                    arrivalDate: new Date().toISOString().substr(0, 10),
                    arrivalTime: '2359',
                    departureDate: new Date().toISOString().substr(0, 10),
                    departureTime: '2358',
                    departureLocalityId: null,
                    deliveryLocalityId: null,
                    departureAddress: null,
                    deliveryAddress: null,
                    price: null
                },
                departureRegion: null,
                departureLocality: null,
                deliveryRegion: null,
                deliveryLocality: null,

                serverErrorMessage: null,

                date: new Date().toISOString().substr(0, 10),
                menu: false,
                modal: false,
                menu2: false,

                departureLocalityData: [],
                departureRegionData: [],
                deliveryLocalityData: [],
                deliveryRegionData: [],

                searchDepartureRegion: null,
                searchDepartureLocality: null,
                searchDeliveryRegion: null,
                searchDeliveryLocality: null,
                isLoading: false,
                isLoading1: false,
                isLoading2: false,
                isLoading3: false,

            }
        },
        computed: {
            departureLocalities() {
                return this.departureLocalityData.map(e => e.name)
            },
            departureRegions() {
                return this.departureRegionData.map(e => e.name)
            },
            deliveryLocalities() {
                return this.deliveryLocalityData.map(e => e.name)
            },
            deliveryRegions() {
                return this.deliveryRegionData.map(e => e.name)
            },
            deliveryLocalityId() {
                const val = this.deliveryLocalityData.find(e => e.name === this.deliveryLocality);
                return val === null ? -1 : val.id;
            },
            departureLocalityId() {
                const val = this.departureLocalityData.find(e => e.name === this.departureLocality);
                return val === null ? -1 : val.id;
            }
        },
        watch: {
            searchDeliveryLocality(val) {
                if (this.isLoading3) return;
                this.isLoading3 = true;

                AXIOS.get(`/api/localities?q=${val}&region=${this.deliveryRegion}`)
                    .then(res => {
                        this.deliveryLocalityData = res.data;
                    })
                    .catch(err => {
                    })
                    .finally(() => (this.isLoading3 = false))
            },

            searchDeliveryRegion(val) {
                if (this.isLoading2) return;
                this.isLoading2 = true;

                AXIOS.get('/api/regions?q=' + val)
                    .then(res => {
                        this.deliveryRegionData = res.data;
                    })
                    .catch(err => {
                    })
                    .finally(() => (this.isLoading2 = false))
            },

            searchDepartureLocality(val) {
                if (this.isLoading1) return;
                this.isLoading1 = true;

                AXIOS.get(`/api/localities?q=${val}&region=${this.departureRegion}`)
                    .then(res => {
                        this.departureLocalityData = res.data;
                    })
                    .catch(err => {
                    })
                    .finally(() => (this.isLoading1 = false))
            },

            searchDepartureRegion(val) {
                if (this.isLoading) return;
                this.isLoading = true;

                AXIOS.get('/api/regions?q=' + val)
                    .then(res => {
                        this.departureRegionData = res.data;
                    })
                    .catch(err => {
                    })
                    .finally(() => (this.isLoading = false))
            },
        },
        methods: {

            createTrip() {
                const form = {
                    info: this.tripForm.info,
                    arrivalDate: this.tripForm.arrivalDate
                        + ' ' + this.tripForm.arrivalTime.substr(0, 2)
                        + ':' + this.tripForm.arrivalTime.substr(2, 4),
                    departureDate: this.tripForm.departureDate
                        + ' ' + this.tripForm.departureTime.substr(0, 2)
                        + ':' + this.tripForm.departureTime.substr(2, 4),
                    departureLocalityId: this.departureLocalityId,
                    deliveryLocalityId: this.deliveryLocalityId,
                    departureAddress: this.tripForm.departureAddress,
                    deliveryAddress: this.tripForm.deliveryAddress,
                    price: this.tripForm.price
                };
                AXIOS.post('/api/trips', form)
                    .then(response => {
                        const trip = response.data;
                        console.log(response.data);
                        this.$router.push('/trip/' + trip.id);
                    }).catch(error => {
                })

            },

        },

    }
</script>

<style scoped>

</style>
