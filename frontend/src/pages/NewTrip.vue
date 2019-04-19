<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout align-start justify-center row fill-height>
                    <v-flex class="md8 xs12">
                        <v-card dark>
                            <v-form>
                                <v-container>
                                    <v-layout column justify-center wrap>
                                        <v-flex xs12 text-xs-center>
                                            <span class="display-2 ">Create new trip</span>
                                        </v-flex>
                                        <v-flex>
                                            <v-text-field
                                                    v-model="tripForm.departurePoint"
                                                    box
                                                    color="blue-grey lighten-2"
                                                    label="Departure point"
                                            ></v-text-field>
                                        </v-flex>
                                        <v-flex xs12>
                                            <v-text-field
                                                    v-model="tripForm.deliveryPoint"
                                                    box
                                                    color="blue-grey lighten-2"
                                                    label="Delivery point"
                                            ></v-text-field>


                                        </v-flex>
                                        <v-flex xs12>
                                            <v-textarea
                                                    v-model="tripForm.info"
                                                    auto-grow
                                                    box
                                                    color="deep-purple"
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
                                                        <v-date-picker v-model="date"
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
        name: "NewChallenge",
        components: {NavigationBar},
        data() {
            const srcs = {
                1: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
                2: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
                3: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
                4: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
                5: 'https://cdn.vuetifyjs.com/images/lists/5.jpg'
            };
            return {
                tripForm: {
                    info: null,
                    arrivalDate: new Date().toISOString().substr(0, 10),
                    arrivalTime: '2359',
                    departureDate: new Date().toISOString().substr(0, 10),
                    departureTime: new Date().getHours() > 9 ? '' : '0' + new Date().getHours() + '' + new Date().getMinutes(),
                    departurePoint: null,
                    deliveryPoint: null,
                    price: null
                },

                serverErrorMessage: null,

                date: new Date().toISOString().substr(0, 10),
                menu: false,
                modal: false,
                menu2: false,


                autoUpdate: true,
                friends: ['Sandra Adams', 'Britta Holt'],
                isUpdating: false,
                name: 'Midnight Crew',
                title: 'The summer breeze'
            }
        },
        watch: {
            isUpdating(val) {
                if (val) {
                    setTimeout(() => (this.isUpdating = false), 3000)
                }
            }
        },
        methods: {
            remove(item) {
                const index = this.friends.indexOf(item.name);
                if (index >= 0) this.friends.splice(index, 1)
            },

            createTrip() {
                const form = {
                    info: this.tripForm.info,
                    arrivalDate: this.tripForm.arrivalDate
                        + ' ' + this.tripForm.arrivalTime.substr(0, 2)
                        + ':' + this.tripForm.arrivalTime.substr(2, 4),
                    departureDate: this.tripForm.departureDate
                        + ' ' + this.tripForm.departureTime.substr(0, 2)
                        + ':' + this.tripForm.departureTime.substr(2, 4),
                    departurePoint: this.tripForm.departurePoint,
                    deliveryPoint: this.tripForm.deliveryPoint,
                    price: this.tripForm.price
                };
                AXIOS.post('/api/trip', form)
                    .then(response => {
                        const trip = response.data;
                        console.log(response.data);
                        this.$router.push('/trip/' + trip.id);
                    }).catch(error => {
                    console.log(error)
                })

            },

        },

    }
</script>

<style scoped>

</style>
