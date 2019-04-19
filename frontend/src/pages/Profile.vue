<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout row v-if="user">
                    <v-flex xs4 mr-3>
                        <v-layout column>
                            <v-flex>
                                <v-avatar
                                        :tile="true"
                                        :size="370"
                                        color="yellow lighten-4"
                                >
                                    <img :src="user.avatarUrl"
                                         :alt="user.firstName + ' ' + user.lastName">
                                </v-avatar>
                                <input
                                        v-show="false"
                                        type="file"
                                        ref="image"
                                        accept="image/*"
                                        @change="onFilePicked"
                                >
                            </v-flex>

                            <v-flex my-3>
                                <v-btn
                                        :loading="uploadAvatar"
                                        :disabled="uploadAvatar "
                                        color="purple"
                                        block
                                        class="white--text"
                                        @click="pickFile"
                                >
                                    Upload avatar
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
                                        <v-list style="background-color: #ab47bc" v-if="reviews">
                                            <v-list-tile
                                                    v-for="item in reviews"
                                                    :key="item.id"
                                                    avatar
                                                    @click.stop="dialog = true"
                                            >

                                                <v-dialog
                                                        v-model="dialog"
                                                        max-width="500"

                                                >
                                                    <v-card color="purple" dark>
                                                        <v-card-title class="headline">
                                                            <v-layout row justify-center align-center>
                                                                <v-flex shrink mr-2>
                                                                    <v-avatar>
                                                                        <img
                                                                                :src="item.reviewer.avatarUrl"
                                                                                :alt="item.reviewer.firstName"
                                                                        >
                                                                    </v-avatar>
                                                                </v-flex>
                                                                <v-flex>
                                                                    {{item.reviewer.firstName}}
                                                                    {{item.reviewer.lastName}}
                                                                </v-flex>
                                                            </v-layout>
                                                        </v-card-title>

                                                        <v-card-text>
                                                            <v-layout column>

                                                                <v-flex>
                                                                    <v-rating
                                                                            v-model="item.rating"
                                                                            background-color="white"
                                                                            color="yellow accent-4"
                                                                            dense
                                                                            :readonly="true"
                                                                            half-increments
                                                                            hover
                                                                            size="18"
                                                                    ></v-rating>
                                                                </v-flex>

                                                                <v-flex>
                                                                    <v-textarea
                                                                            box
                                                                            label="Review"
                                                                            auto-grow
                                                                            readonly
                                                                            :value="item.review"
                                                                    ></v-textarea>
                                                                </v-flex>
                                                            </v-layout>
                                                        </v-card-text>
                                                    </v-card>
                                                </v-dialog>
                                                <v-list-tile-avatar>
                                                    <img :src="item.reviewer.avatarUrl">
                                                </v-list-tile-avatar>

                                                <v-list-tile-content>
                                                    <v-list-tile-title
                                                            v-html="item.reviewer.firstName + ' ' + item.reviewer.lastName"></v-list-tile-title>
                                                </v-list-tile-content>

                                                <v-list-tile-action>
                                                    <v-icon color="yellow">chat_bubble</v-icon>
                                                </v-list-tile-action>
                                            </v-list-tile>


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
                                    <v-card-actions :class="isEditing ? '' : 'hidden-sm-and-up'">
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
                                                <span class="grey--text text--lighten-2 caption mr-2">{{ user.driverRating === null ? '(nobody yet rate this driver)' : user.driverRating  }}</span>
                                                <v-rating
                                                        v-model="user.driverRating"
                                                        background-color="white"
                                                        color="yellow accent-4"
                                                        dense
                                                        :readonly="true"
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
                                            <v-flex xs12 my-1 v-for="trip in user.lastTrips">
                                                <ChallengeCard :trip="trip" :text="'subheading'" :color="'purple'"/>
                                            </v-flex>

                                        </v-layout>
                                    </v-card-text>
                                    <v-divider></v-divider>
                                </v-card>
                            </v-flex>

                        </v-layout>

                    </v-flex>


                </v-layout>
                <v-layout align-center justify-center fill-height v-else>
                    <v-progress-circular
                            indeterminate
                            color="amber"
                    ></v-progress-circular>
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

                reviews: [],
                statistics: [],

                dialog: false,
                hasSaved: false,
                isEditing: null,
                model: null,
                loader: null,
                uploadAvatar: false,
                updateProfileMessage: '',
                response: null,

                imageName: '',
                imageUrl: '',
                imageFile: '',

                user: null,

                updateProfileForm: {
                    firstName: null,
                    lastName: null,
                    email: null,
                    phoneNumber: null,
                    password: null,
                    confirmedPassword: null,
                },

            }
        },
        watch: {
            loader() {
                const load = this.loader;
                this[load] = !this[load];
                setTimeout(() => (this[load] = false), 3000)
                this.loader = null;

            },

        },

        methods: {
            uploadAvatarToServer(file) {
                const formData  = new FormData();
                formData.append('avatar', file, this.imageName);

                AXIOS.put('/api/profile', formData)
                    .then(response => {
                        this.user.avatarUrl = response.data.url;
                        console.log(response.data);

                    }).catch(error => {
                    console.log(error)
                })
            },

            save() {
                this.isEditing = !this.isEditing;
                this.updateProfileForm.email = this.user.email;

                AXIOS.post('/api/profile', this.updateProfileForm)
                    .then(response => {
                        if (response.status === 200) {
                            this.$store.dispatch('myProfile', response.data);
                            this.hasSaved = true;
                        }
                    })
                    .catch(error => {
                        this.hasSaved = true;
                        this.updateProfileMessage = 'Error while updating your profile'
                    })
            },

            setStatistics(totalTrips, reviewsCount) {
                this.statistics.push({title: 'Total trips', value: totalTrips, icon: ''})
                this.statistics.push({title: 'Reviews', value: reviewsCount, icon: ''},)
            },

            pickFile() {
                this.$refs.image.click()
            },

            onFilePicked(e) {
                const files = e.target.files
                if (files[0] !== undefined) {
                    this.imageName = files[0].name
                    if (this.imageName.lastIndexOf('.') <= 0) {
                        return
                    }
                    const fr = new FileReader()
                    fr.readAsDataURL(files[0])
                    fr.addEventListener('load', () => {
                        this.imageUrl = fr.result
                        this.imageFile = files[0];
                        this.uploadAvatarToServer(this.imageFile);
                    })
                } else {
                    this.imageName = ''
                    this.imageFile = ''
                    this.imageUrl = ''
                }
            }
        },

        mounted() {
            AXIOS.get('/api/profile')
                .then(response => {
                    this.user = response.data;
                    this.$store.commit('setUser', response.data);
                    this.setStatistics(this.user.totalTrips, this.user.reviewsCount)
                    this.reviews = response.data.lastReviews;
                }).catch(error => {

                console.log(error);
                // this.$store.dispatch('logout');
            })
        }
    }
</script>

<style scoped>
</style>
