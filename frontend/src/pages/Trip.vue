<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-app>
        <NavigationBar/>
        <v-content>
            <v-container>
                <v-layout row>
                    <v-flex xs7 mr-3>
                        <v-card dark color="purple">
                            <v-toolbar
                                    card
                                    color="purple"
                            >
                                <v-icon>fas fa-suitcase</v-icon>
                                <v-toolbar-title class="font-weight-light">Information about trip</v-toolbar-title>
                                <v-spacer/>
                                <v-tooltip bottom v-for="i in statusList">
                                    <template v-slot:activator="{ on }" v-if="i.status === trip.status">
                                        <v-icon color="red" dark v-on="on">{{i.icon}}</v-icon>
                                    </template>
                                    <span>{{i.title}}</span>
                                </v-tooltip>
                            </v-toolbar>
                            <v-form>
                                <v-container>
                                    <v-layout column justify-center wrap>
                                        <v-flex v-if="!edit">
                                            <v-timeline>
                                                <v-timeline-item
                                                        color="red lighten-2"
                                                        fill-dot
                                                        right
                                                >
                                                    <v-card>
                                                        <v-card-title class="red lighten-2">
                                                            <v-icon
                                                                    dark
                                                                    size="42"
                                                                    class="mr-3"
                                                            >
                                                                mdi-magnify
                                                            </v-icon>
                                                            <h2 class="headline white--text font-weight-light">
                                                                Departure</h2>
                                                        </v-card-title>
                                                        <v-container>
                                                            <v-layout column>
                                                                <v-flex>
                                                                    <v-icon small>fas fa-map-marked-alt</v-icon>
                                                                    {{trip.departureLocality.region.name}},
                                                                    {{trip.departureLocality.name}}
                                                                </v-flex>
                                                                <v-flex>
                                                                    <v-icon small>fas fa-map-marker-alt</v-icon>
                                                                    {{trip.departureAddress}}
                                                                </v-flex>
                                                                <v-flex>
                                                                    <v-icon small>fas fa-calendar</v-icon>
                                                                    {{trip.departureDate}}
                                                                </v-flex>
                                                            </v-layout>
                                                        </v-container>
                                                    </v-card>
                                                </v-timeline-item>

                                                <v-timeline-item
                                                        color="amber lighten-1"
                                                        fill-dot
                                                        left
                                                        small
                                                >
                                                    <v-card>
                                                        <v-card-title class="amber lighten-1 justify-end">
                                                            <h2 class="headline mr-3 white--text font-weight-light">
                                                                Delivery</h2>
                                                            <v-icon
                                                                    dark
                                                                    size="42"
                                                            >mdi-home-outline
                                                            </v-icon>
                                                        </v-card-title>
                                                        <v-container>
                                                            <v-layout column>
                                                                <v-flex>
                                                                    <v-icon small>fas fa-map-marked-alt</v-icon>
                                                                    {{trip.deliveryLocality.region.name}},
                                                                    {{trip.deliveryLocality.name}}
                                                                </v-flex>
                                                                <v-flex>
                                                                    <v-icon small>fas fa-map-marker-alt</v-icon>
                                                                    {{trip.deliveryAddress}}
                                                                </v-flex>
                                                                <v-flex>
                                                                    <v-icon small>fas fa-calendar-check</v-icon>
                                                                    {{trip.arrivalDate}}
                                                                </v-flex>
                                                            </v-layout>
                                                        </v-container>
                                                    </v-card>
                                                </v-timeline-item>
                                            </v-timeline>
                                        </v-flex>
                                        <v-flex v-if="edit">
                                            <v-text-field
                                                    v-model="tripForm.departureAddress"
                                                    box
                                                    :readonly="!edit"
                                                    color="blue-grey lighten-2"
                                                    label="Departure point"
                                            ></v-text-field>
                                        </v-flex>
                                        <v-flex v-if="edit">
                                            <v-text-field
                                                    v-model="tripForm.deliveryAddress"
                                                    box
                                                    :readonly="!edit"
                                                    color="blue-grey lighten-2"
                                                    label="Delivery point"
                                            ></v-text-field>
                                        </v-flex>

                                        <v-flex xs12>
                                            <v-textarea
                                                    v-model="tripForm.info"
                                                    auto-grow
                                                    box
                                                    :readonly="!edit"
                                                    color="blue-grey lighten-3"
                                                    label="Additional information"
                                                    rows="3"
                                            ></v-textarea>
                                        </v-flex>

                                        <v-flex xs12>

                                            <v-text-field
                                                    v-model="tripForm.price"
                                                    box
                                                    :readonly="!edit"
                                                    color="blue-grey lighten-3"
                                                    label="Price"
                                            ></v-text-field>


                                        </v-flex>

                                        <v-flex v-if="edit">
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
                                                                    v-model="tripForm.departureDate"
                                                                    label="Departure date   "
                                                                    prepend-icon="event"
                                                                    :readonly="!edit"
                                                                    v-on="on"
                                                            ></v-text-field>
                                                        </template>
                                                        <v-date-picker v-model="tripForm.departureDate"
                                                                       @input="menu2 = false"></v-date-picker>
                                                    </v-menu>
                                                </v-flex>

                                                <v-flex>
                                                    <v-text-field v-model="tripForm.departureTime"
                                                                  mask="time"
                                                                  :readonly="!edit"
                                                                  label="Departure time"></v-text-field>
                                                </v-flex>
                                            </v-layout>
                                        </v-flex>

                                        <v-flex v-if="edit">
                                            <v-layout row>
                                                <v-flex>
                                                    <v-menu
                                                            v-model="menu"
                                                            :close-on-content-click="false"
                                                            :nudge-right="40"
                                                            lazy
                                                            transition="scale-transition"
                                                            :readonly="!edit"
                                                            offset-y
                                                            full-width
                                                            min-width="290px"
                                                    >
                                                        <template v-slot:activator="{ on }">
                                                            <v-text-field
                                                                    v-model="tripForm.arrivalDate"
                                                                    label="Arrival date"
                                                                    prepend-icon="event"
                                                                    :readonly="!edit"
                                                                    v-on="on"
                                                            ></v-text-field>
                                                        </template>
                                                        <v-date-picker v-model="tripForm.arrivalDate"
                                                                       @input="menu = false"></v-date-picker>
                                                    </v-menu>
                                                </v-flex>

                                                <v-flex>
                                                    <v-text-field v-model="tripForm.arrivalTime"
                                                                  mask="time"
                                                                  :readonly="!edit"
                                                                  label="Arrival time"></v-text-field>
                                                </v-flex>
                                            </v-layout>
                                        </v-flex>

                                        <v-flex v-if="trip.stuffers !== undefined && trip.stuffers.length !== 0">
                                            <v-autocomplete
                                                    v-model="tripForm.stuffers"
                                                    :disabled="!edit"
                                                    :items="trip.stuffers"
                                                    box
                                                    chips
                                                    color="blue-grey lighten-2"
                                                    label="Their stuff in trip"
                                                    multiple
                                                    :readonly="!edit"
                                            >
                                                <template v-slot:selection="data">
                                                    <v-chip
                                                            :selected="data.selected"
                                                            :close="edit"
                                                            class="chip--select-multi"
                                                            @input="remove(data.item)"
                                                    >
                                                        <v-avatar>
                                                            <img :src="data.item.avatarUrl">
                                                        </v-avatar>
                                                        {{ data.item.firstName }} {{ data.item.lastName }}
                                                    </v-chip>
                                                </template>
                                                <template v-slot:item="data">
                                                    <template>
                                                        <v-list-tile-avatar>
                                                            <img :src="data.item.avatarUrl">
                                                        </v-list-tile-avatar>
                                                        <v-list-tile-content>
                                                            <v-list-tile-title
                                                                    v-html="data.item.firstName +' ' + data.item.lastName"></v-list-tile-title>
                                                        </v-list-tile-content>
                                                    </template>
                                                </template>
                                            </v-autocomplete>
                                        </v-flex>

                                        <v-flex v-if="edit">
                                            <span>Status</span>
                                            <v-radio-group v-model="tripForm.status" row>
                                                <v-radio label="In trip" value="IN_TRIP"></v-radio>
                                                <v-radio label="Full" value="FULL"></v-radio>
                                                <v-radio label="Completed" value="COMPLETED"></v-radio>
                                            </v-radio-group>
                                        </v-flex>

                                    </v-layout>
                                </v-container>
                            </v-form>
                            <v-divider></v-divider>
                            <v-card-actions v-if="isEditable">

                                <v-spacer></v-spacer>
                                <v-btn
                                        color="primary"
                                        depressed
                                        v-if="!edit"
                                        @click="edit = !edit"
                                >
                                    Edit
                                </v-btn>

                                <v-btn
                                        color="warning"
                                        depressed
                                        v-if="edit"
                                        @click="cancelEditForm"
                                >
                                    Cancel
                                </v-btn>
                                <v-btn
                                        color="primary"
                                        depressed
                                        v-if="edit"
                                        @click="updateTrip"
                                >
                                    Update
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-flex>
                    <v-flex xs5>
                        <v-layout column>
                            <v-flex mb-3>
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
                                        <v-toolbar-title class="font-weight-light">Information about driver
                                        </v-toolbar-title>
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
                                                            <img :src="trip.author.avatarUrl"
                                                                 :alt="trip.author.firstName + ' ' + trip.author.lastName">
                                                        </v-avatar>
                                                    </v-flex>
                                                </v-layout>
                                            </v-flex>
                                            <v-flex ml-2>
                                                <v-layout>
                                                    <v-layout column>

                                                        <v-flex my-2>
                                                            Driver rating:
                                                            <span class="grey--text text--lighten-2 caption mr-2">{{ trip.author.driverRating }}</span>
                                                            <v-rating
                                                                    v-model="trip.author.driverRating"
                                                                    background-color="white"
                                                                    color="yellow accent-4"
                                                                    readonly
                                                                    dense
                                                                    half-increments
                                                                    hover
                                                                    size="18"
                                                            ></v-rating>
                                                        </v-flex>

                                                        <v-flex my-2>
                                                            Number of trips: {{ trip.author.totalTrips}}
                                                        </v-flex>

                                                        <v-flex my-2>
                                                            Number of reviews: {{trip.author.reviewsCount}}
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
                                        <v-btn :to="'/user/' + trip.author.id" color="red">
                                            <span class="mr-2">Go to profile</span>
                                            <v-icon> fas fa-angle-right</v-icon>
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-flex>
                            <v-flex v-if="isReviewable">
                                <v-dialog v-model="reviewDialog" persistent max-width="500">
                                    <template v-slot:activator="{ on }">
                                        <v-btn color="primary" dark v-on="on">Review</v-btn>
                                    </template>
                                    <v-card dark color="purple">
                                        <v-form>
                                            <v-card-title class="headline">
                                                <v-layout row justify-center align-center>
                                                    <v-flex shrink mr-2>
                                                        <v-avatar>
                                                            <img
                                                                    :src="trip.author.avatarUrl"
                                                                    :alt="trip.author.firstName"
                                                            >
                                                        </v-avatar>
                                                    </v-flex>
                                                    <v-flex>
                                                        {{trip.author.firstName}} {{trip.author.lastName}}
                                                    </v-flex>
                                                </v-layout>
                                            </v-card-title>

                                            <v-card-text>
                                                <v-layout column>

                                                    <v-flex>
                                                        <v-rating
                                                                v-model="reviewForm.rating"
                                                                background-color="white"
                                                                color="yellow accent-4"
                                                                dense
                                                                half-increments
                                                                hover
                                                                size="18"
                                                        ></v-rating>
                                                    </v-flex>

                                                    <v-flex>
                                                        <v-textarea
                                                                v-model="reviewForm.review"
                                                                box
                                                                label="Review"
                                                                auto-grow
                                                        ></v-textarea>
                                                    </v-flex>
                                                </v-layout>
                                            </v-card-text>
                                            <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn color="yellow darken-1" dark flat
                                                       @click="reviewDialog = !reviewDialog">Cancel
                                                </v-btn>
                                                <v-btn color="primary darken-1" dark @click="sendReview">Review</v-btn>
                                            </v-card-actions>
                                        </v-form>
                                    </v-card>
                                </v-dialog>

                            </v-flex>
                            <v-flex v-if="isStuffed && !isCompleted">
                                <v-btn color="red" dark @click="cancelTripRequest">Refuse to trip</v-btn>
                            </v-flex>
                            <v-flex v-if="isRequestable">
                                <v-dialog
                                        v-model="request"
                                        width="500"
                                >
                                    <template v-slot:activator="{ on }">
                                        <v-btn
                                                color="red lighten-2"
                                                dark
                                                v-on="on"
                                        >
                                            Request to trip
                                        </v-btn>
                                    </template>

                                    <v-card color="purple lighten-2" dark>
                                        <v-card-title class="headline">
                                            <v-layout row justify-center align-center>
                                                <v-flex shrink mr-2>
                                                    <v-avatar>
                                                        <img
                                                                :src="trip.author.avatarUrl"
                                                                :alt="trip.author.firstName"
                                                        >
                                                    </v-avatar>
                                                </v-flex>
                                                <v-flex>
                                                    {{trip.author.firstName}} {{trip.author.lastName}}
                                                </v-flex>
                                            </v-layout>
                                        </v-card-title>

                                        <v-card-text>
                                            <v-textarea
                                                    box
                                                    label="Request text"
                                                    placeholder="Please describe your stuff to trip"
                                                    color="white"
                                                    auto-grow
                                                    v-model="requestForm.requestText"
                                            ></v-textarea>
                                        </v-card-text>

                                        <v-divider></v-divider>

                                        <v-card-actions>
                                            <v-spacer></v-spacer>
                                            <v-btn
                                                    color="red lighten-1"
                                                    dark
                                                    @click="sendTripRequest"
                                            >
                                                Send request
                                            </v-btn>
                                        </v-card-actions>
                                    </v-card>
                                </v-dialog>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import {AXIOS} from "../api/http-common";
    import Review from "../components/Review";

    export default {
        name: "Trip",
        components: {Review, NavigationBar},
        props: {
            disabled: Boolean,
        },

        computed: {
            isEditable: function () {
                return this.isMyTrip && !this.isCompleted;
            },
            isMyTrip: function () {
                return this.$store.state.user.id === this.trip.author.id
            },
            isCompleted: function () {
                return this.trip.status === 'COMPLETED'
            },
            isStuffed: function () {
                const userId = this.$store.state.user.id;
                for (let i = 0; i < this.trip.stuffers.length; i++) {
                    if (this.trip.stuffers[i].id === userId) {
                        return true
                    }
                }
                return false;
            },
            isReviewable: function () {
                return this.isStuffed && this.isCompleted;
            },
            isRequestable: function () {
                console.log('isRE ' + this.trip.status === 'READY' && !this.isMyTrip)
                return this.trip.status === 'READY' && !this.isMyTrip
            },

            user() {
                return this.$store.getters.getUser;
            },
        },

        data() {
            return {
                edit: false,
                menu: false,
                menu2: false,
                request: false,
                reviewDialog: false,

                reviewForm: {
                    driverId: null,
                    tripId: null,
                    rating: 0,
                    review: null
                },
                requestForm: {
                    tripId: null,
                    requestText: null,
                },

                stuffers: {},
                tripForm: {},

                trip: {},

                statusList: [
                    {status: 'IN_TRIP', title: 'In trip', icon: 'fas fa-car'},
                    {status: 'READY', title: 'Ready', icon: 'fas fa-calendar-day'},
                    {status: 'COMPLETED', title: 'Completed', icon: 'fas fa-check-double'},
                    {status: 'FULL', title: 'No places', icon: 'fas fa-user-slash'}
                ]
            }
        },


        methods: {
            remove(item) {
                if (!this.edit) return;
                let index = -1;
                for (let i = 0; i < this.tripForm.stuffers.length; i++) {
                    if (i.id === item.id) {
                        index = i;
                    }
                }
                if (index >= 0) this.tripForm.stuffers.splice(index, 1)
            },

            cancelEditForm() {
                this.edit = !this.edit;
                this.tripForm = {
                    info: this.trip.info,
                    status: this.trip.status,
                    arrivalDate: this.trip.arrivalDate.substring(0, 10),
                    arrivalTime: this.trip.arrivalDate.substring(11, 16),
                    departureDate: this.trip.departureDate.substring(0, 10),
                    departureTime: this.trip.departureDate.substring(11, 16),
                    departureAddress: this.trip.departureAddress,
                    deliveryAddress: this.trip.deliveryAddress,
                    stuffers: this.trip.stuffers,
                    price: this.trip.price
                }
            },

            updateTrip() {
                this.edit = !this.edit;
                const tripForm = JSON.parse(JSON.stringify(this.tripForm));
                tripForm.arrivalDate = this.tripForm.arrivalDate
                    + ' ' + this.tripForm.arrivalTime.substr(0, 2)
                    + ':' + this.tripForm.arrivalTime.substr(2, 4);
                tripForm.departureDate = this.tripForm.departureDate
                    + ' ' + this.tripForm.departureTime.substr(0, 2)
                    + ':' + this.tripForm.departureTime.substr(2, 4);

                AXIOS.put('/api/trips/' + this.$route.params.tripId, tripForm)
                    .then(response => {
                        console.log('upd:');
                        console.log(response.data);
                        this.stuffers = response.data.stuffers;
                        this.trip = response.data;
                    }).catch(error => {
                    console.log('error while upd: ' + error)
                })
            },

            sendTripRequest() {
                this.request = false;
                this.requestForm.tripId = this.trip.id;
                AXIOS.post('/api/request/send', this.requestForm)
                    .then(response => {
                        console.log(response)
                    }).catch(error => {
                    console.log(error);
                })
            },

            cancelTripRequest() {
                const data = new FormData();
                data.set('tripId', this.trip.id);

                AXIOS.post('/api/request/cancel', data)
                    .then(response => {
                        console.log(response);
                    }).catch(error => {
                    console.log(error);
                })
            },

            sendReview() {
                this.reviewDialog = !this.reviewDialog;
                this.reviewForm.driverId = this.trip.author.id;
                this.reviewForm.tripId = this.trip.id;

                AXIOS.post('/api/review', this.reviewForm)
                    .then(response => {
                    })
            }
        },

        async beforeMount() {
            await AXIOS.get('/api/trips/' + this.$route.params.tripId)
                .then(response => {
                    console.log(response.data);
                    this.stuffers = response.data.stuffers;
                    this.trip = response.data;
                    this.tripForm = {
                        info: this.trip.info,
                        arrivalDate: this.trip.arrivalDate.substring(0, 10),
                        arrivalTime: this.trip.arrivalDate.substring(11, 16).replace(':', ''),
                        departureDate: this.trip.departureDate.substring(0, 10),
                        departureTime: this.trip.departureDate.substring(11, 16).replace(':', ''),
                        departureAddress: this.trip.departureAddress,
                        deliveryAddress: this.trip.deliveryAddress,
                        stuffers: this.trip.stuffers,
                        price: this.trip.price,
                        status: this.trip.status
                    }
                }).catch(error => {
                    console.log(error)
                });
        }
    }
</script>

<style scoped>

</style>
