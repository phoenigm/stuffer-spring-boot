<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout row>
                    <v-flex xs4>
                        <v-layout column>
                            <v-flex>
                                <v-avatar
                                        :tile="true"
                                        :size="340"
                                        color="yellow lighten-4"
                                >
                                    <img src="https://pp.userapi.com/c848536/v848536366/131886/EYolGIZVWhg.jpg"
                                         alt="avatar">
                                </v-avatar>
                                <v-btn
                                        :loading="uploadAvatar"
                                        :disabled="uploadAvatar"
                                        color="purple"
                                        class="white--text"
                                        @click="loader = 'uploadAvatar'"
                                >
                                    Upload avatar
                                </v-btn>
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
                                        <v-spacer></v-spacer>
                                        <v-btn
                                                color="purple darken-3"
                                                fab
                                                small
                                                @click="isEditing = !isEditing"
                                        >
                                            <v-icon v-if="isEditing">fas fa-times</v-icon>
                                            <v-icon v-else>fas fa-pen</v-icon>
                                        </v-btn>
                                    </v-toolbar>
                                    <v-card-text>
                                        <v-layout>
                                            <v-flex>
                                                <v-text-field
                                                        :disabled="!isEditing"
                                                        color="white"
                                                        label="First name"
                                                        v-model="user.firstName"
                                                ></v-text-field>
                                            </v-flex>
                                            <v-flex>
                                                <v-text-field
                                                        :disabled="!isEditing"
                                                        color="white"
                                                        label="Last name"
                                                        v-model="user.lastName"
                                                ></v-text-field>
                                            </v-flex>
                                        </v-layout>

                                        <v-layout>
                                            <v-flex>
                                                <v-text-field
                                                        :disabled="!isEditing"
                                                        color="white"
                                                        label="Email"
                                                        v-model="user.email"
                                                ></v-text-field>
                                            </v-flex>
                                            <v-flex>
                                                <v-text-field
                                                        :disabled="!isEditing"
                                                        color="white"
                                                        label="Phone number"
                                                        v-model="user.phoneNumber"
                                                ></v-text-field>
                                            </v-flex>
                                        </v-layout>

                                        <v-layout>
                                            <v-flex>
                                                <v-text-field
                                                        :disabled="!isEditing"
                                                        color="white"
                                                        label="Password"
                                                        v-model="updateProfileForm.password"
                                                ></v-text-field>
                                            </v-flex>
                                            <v-flex>
                                                <v-text-field
                                                        :disabled="!isEditing"
                                                        color="white"
                                                        label="Confirm password"
                                                        v-model="updateProfileForm.confirmedPassword"
                                                ></v-text-field>
                                            </v-flex>
                                        </v-layout>

                                    </v-card-text>
                                    <v-divider></v-divider>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn
                                                :disabled="!isEditing"
                                                color="success"
                                                @click="save"
                                        >
                                            Save
                                        </v-btn>
                                    </v-card-actions>
                                    <v-snackbar
                                            v-model="hasSaved"
                                            :timeout="2000"
                                            absolute
                                            bottom
                                            left
                                    >
                                        {{updateProfileMessage}}
                                    </v-snackbar>
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
    import ChallengeCard from "../components/ChallengeCard"
    import Footer from "../components/Footer";
    import NavigationBar from "../components/NavigationBar";
    import {AXIOS} from "../api/http-common";
    import AvatarCropper from "vue-avatar-cropper";

    export default {
        name: "Profile",
        components: {NavigationBar, Footer, ChallengeCard, AvatarCropper},
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
        },
        watch: {
            loader() {
                const l = this.loader
                this[l] = !this[l]

                setTimeout(() => (this[l] = false), 3000)

                this.loader = null
            }
        },
        methods: {
            save() {
                this.isEditing = !this.isEditing;
                this.updateProfileForm.email = this.user.email;

                AXIOS.post('/api/profile', this.updateProfileForm)
                    .then(response => {
                        if (response.status === 200) {
                            this.$store.commit('setUser', response.data);
                            this.hasSaved = true;
                        }
                    })
                    .catch(error => {
                        this.hasSaved = true;
                        this.updateProfileMessage = 'Error while updating your profile'
                    })
            },

        },
        created() {
            document.title = "Profile";
        },
        mounted() {
            AXIOS.get('/api/profile')
                .then(response => {
                    this.$store.commit('setUser', response.data);
                }).catch(error => {

                console.log(error);
                this.$store.dispatch('logout');
            })
        }
    }
</script>

<style scoped>
</style>
