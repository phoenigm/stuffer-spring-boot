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
                                                <span>Delivery date:  23-03-2019 20:00</span>
                                            </v-flex>
                                            <v-flex my-2>
                                                <span>Delivery date:  23-03-2019 20:00</span>
                                            </v-flex>
                                            <v-flex my-2>
                                                <span>From: Башкортостан, г Уфа, ул Первомайская, 49</span>
                                            </v-flex>
                                            <v-flex my-2>
                                                <span>To: Татарстан, г Казань, ул  Пушкина, 32</span>
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
                                            Price: 1000
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
                                                Lorem ipsum dolor sit amet, brute iriure accusata ne mea. Eos suavitate
                                                referrentur ad, te duo agam libris qualisque, utroque quaestio
                                                accommodare no qui. Et percipit laboramus usu, no invidunt verterem
                                                nominati mel. Dolorem ancillae an mei, ut putant invenire splendide mel,
                                                ea nec propriae adipisci. Ignota salutandi accusamus in sed, et per
                                                malis fuisset, qui id ludus appareat.
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
                                    Azat Mukhametzyanov
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
                                    <v-flex>
                                        <v-layout>
                                            <v-layout column>

                                                <v-flex my-2>
                                                    Driver rating:
                                                    <span class="grey--text text--lighten-2 caption mr-2">{{ rating }}</span>
                                                    <v-rating
                                                            v-model="rating"
                                                            background-color="white"
                                                            color="yellow accent-4"
                                                            dense
                                                            half-increments
                                                            hover
                                                            size="18"
                                                    ></v-rating>
                                                </v-flex>

                                                <v-flex my-2>
                                                    Last trip rating:
                                                    <span class="grey--text text--lighten-2 caption mr-2">{{ rating }}</span>
                                                    <v-rating
                                                            v-model="rating"
                                                            background-color="white"
                                                            color="yellow accent-4"
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
                                            </v-layout>
                                        </v-layout>
                                    </v-flex>
                                </v-layout>

                            </v-card-text>
                            <v-divider></v-divider>
                            <v-card-actions>
                                <v-spacer/>
                                <span class="text-sm-center subheading mr-2">Go to profile</span>
                                <v-icon> fas fa-angle-right</v-icon>
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

    export default {
        name: "Challenge",
        components: {NavigationBar},

        data() {
            return {
                hasSaved: false,
                isEditing: null,
                model: null,
                rating: 4.11,
                numberOfTrips: 0,

                user: this.$store.getters['getUser'],

                loader: null,
                uploadAvatar: false,


                updateProfileForm: {
                    firstName: null,
                    lastName: null,
                    email: null,
                    phoneNumber: null,
                    password: null,
                    confirmedPassword: null,
                },

                updateProfileMessage: '',
            }
        }
    }
</script>

<style scoped>

</style>
