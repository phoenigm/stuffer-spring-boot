<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout row>
                    <v-flex xs4 mr-3>
                        <v-layout column>
                            <v-flex mb-3>
                                <v-avatar
                                        :tile="true"
                                        :size="370"
                                        color="yellow lighten-4"
                                >
                                    <img src="https://pp.userapi.com/c848536/v848536366/131886/EYolGIZVWhg.jpg"
                                         alt="avatar">
                                </v-avatar>

                            </v-flex>

                            <v-flex mb-3>
                                <v-btn dark block color="purple">
                                    Write message
                                </v-btn>

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
                            <v-flex mb-3>
                                <v-card
                                        class="hide-overflow"
                                        color="purple lighten-1"
                                        dark
                                >
                                    <v-card-text>
                                        <v-layout align-center justify-center row wrap>
                                            <v-flex>
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
                                            <v-flex shrink class="text-xs-center mx-3" v-for="stat in statistics">
                                                <v-layout column fill-height>
                                                    <v-flex class="headline">
                                                        {{stat.value}}
                                                    </v-flex>
                                                    <v-flex class="body-2">
                                                        {{stat.title}}
                                                    </v-flex>
                                                </v-layout>
                                            </v-flex>
                                        </v-layout>
                                    </v-card-text>
                                    <v-divider></v-divider>
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
                                        <v-toolbar-title class="font-weight-light">Trips</v-toolbar-title>
                                    </v-toolbar>
                                    <v-card-text>
                                        <v-layout column subheading>
                                            <v-flex xs12 my-1 v-for="i in 3" >
                                                <v-card
                                                        color="purple"
                                                >
                                                    <v-card-title>
                                                        <v-layout justify-center>
                                                            <span class="title font-weight-light">Уфа - Казань</span>
                                                        </v-layout>
                                                    </v-card-title>

                                                    <v-card-text class="subheading font-weight-bold">

                                                        <v-layout row>
                                                            <v-flex>
                                                                <v-layout column>
                                                                    <v-flex>
                                                                        <span>Delivery date:  23-03-2019 20:00</span>
                                                                    </v-flex>
                                                                    <v-flex>
                                                                        <span>Delivery date:  23-03-2019 20:00</span>
                                                                    </v-flex>
                                                                </v-layout>
                                                            </v-flex>

                                                            <v-flex>
                                                                <v-layout column>
                                                                    <v-flex>
                                                                        <span>From: Башкортостан, г Уфа, ул Первомайская, 49</span>
                                                                    </v-flex>
                                                                    <v-flex>
                                                                        <span>To: Татарстан, г Казань, ул  Пушкина, 32</span>
                                                                    </v-flex>
                                                                </v-layout>
                                                            </v-flex>
                                                        </v-layout>

                                                    </v-card-text>
                                                </v-card>
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
    import ChallengeCard from "../components/ChallengeCard";

    export default {
        name: "User",
        components: {ChallengeCard, NavigationBar},
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

                statistics: [
                    {title: 'Total trips', value: 2, icon: ''},
                    {title: 'Last trip', value: '20.12.2018', icon: ''},
                    {title: 'Reviews', value: 2, icon: ''},
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
