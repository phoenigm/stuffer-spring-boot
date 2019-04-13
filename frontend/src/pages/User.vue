<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout row>
                    <v-flex xs4>
                        <v-layout column>
                            <v-flex mb-3>
                                <v-avatar
                                        :tile="true"
                                        :size="340"
                                        color="yellow lighten-4"
                                >
                                    <img src="https://pp.userapi.com/c848536/v848536366/131886/EYolGIZVWhg.jpg"
                                         alt="avatar">
                                </v-avatar>

                            </v-flex>
                            <v-flex>
                                <v-card
                                        class="hide-overflow"
                                        color="purple lighten-1"
                                        dark
                                        max-width="340"
                                >
                                    <v-toolbar
                                            card
                                            color="purple"
                                    >
                                        <v-icon>mdi-account</v-icon>
                                        <v-toolbar-title class="font-weight-light">Reviews
                                        </v-toolbar-title>
                                    </v-toolbar>
                                    <v-card-text>
                                        <v-list style="background-color: #ab47bc">
                                            <v-list-tile
                                                    v-for="item in items"
                                                    :key="item.title"
                                                    avatar
                                                    @click=""
                                            >
                                                <v-list-tile-avatar>
                                                    <img :src="item.avatar">
                                                </v-list-tile-avatar>

                                                <v-list-tile-content>
                                                    <v-list-tile-title v-html="item.title"></v-list-tile-title>
                                                </v-list-tile-content>

                                                <v-list-tile-action>
                                                    <v-icon color="yellow">chat_bubble</v-icon>
                                                </v-list-tile-action>
                                            </v-list-tile>

                                            <v-card-actions>
                                                <v-spacer/>
                                                <span class="text-sm-center subheading mr-2">Go to all reviews</span>
                                                <v-icon> fas fa-angle-right</v-icon>
                                            </v-card-actions>
                                        </v-list>

                                    </v-card-text>

                                </v-card>
                            </v-flex>
                        </v-layout>
                    </v-flex>

                    <v-flex xs8>
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
                                        <v-icon>mdi-account</v-icon>
                                        <v-toolbar-title class="font-weight-light">Personal information
                                        </v-toolbar-title>

                                    </v-toolbar>
                                    <v-card-text>
                                        <v-layout>
                                            <v-flex>
                                                <v-text-field
                                                        readonly
                                                        color="white"
                                                        label="First name"
                                                        v-model="user.firstName"
                                                ></v-text-field>
                                            </v-flex>
                                            <v-flex>
                                                <v-text-field
                                                        readonly
                                                        color="white"
                                                        label="Last name"
                                                        v-model="user.lastName"
                                                ></v-text-field>
                                            </v-flex>
                                        </v-layout>

                                        <v-layout>
                                            <v-flex>
                                                <v-text-field
                                                        readonly
                                                        color="white"
                                                        label="Email"
                                                        v-model="user.email"
                                                ></v-text-field>
                                            </v-flex>
                                            <v-flex>
                                                <v-text-field
                                                        readonly
                                                        color="white"
                                                        label="Phone number"
                                                        v-model="user.phoneNumber"
                                                ></v-text-field>
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
                                        <v-icon>mdi-account</v-icon>
                                        <v-toolbar-title class="font-weight-light">Activity</v-toolbar-title>
                                    </v-toolbar>
                                    <v-card-text>
                                        <v-layout row subheading>
                                            <v-flex md6 offset-xs1>
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
                                            </v-flex>

                                            <v-flex md6>
                                                <v-layout column>

                                                </v-layout>
                                            </v-flex>

                                        </v-layout>
                                    </v-card-text>
                                    <v-divider></v-divider>
                                </v-card>
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

    export default {
        name: "User",
        components: {NavigationBar},
        props: {

        },

        data() {
            return {
                items: [
                    {active: true, title: 'Jason Oner', avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg'},
                    {active: true, title: 'Ranee Carlson', avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg'},
                    {title: 'Cindy Baker', avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg'},
                ],
                items2: [
                    {title: 'Travis Howard', avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg'}
                ],


                hasSaved: false,
                isEditing: null,
                model: null,
                rating: 4.11,
                numberOfTrips: 0,

                user: null
            }
        },

        mounted() {

            AXIOS.get('/api/profile/' + this.$route.params.userId)
                .then(response => {
                    this.user = response.data;
                    console.log(response.data);
                }).catch(error => {
                console.log(error);

                if (error.status === 404) {
                    this.$router.push("/404");
                } else {
                    this.$store.dispatch('logout');
                }


            })
        }
    }
</script>

<style scoped>

</style>
