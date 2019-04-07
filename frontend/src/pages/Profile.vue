<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout>
                    <v-toolbar
                            card
                            color="purple"
                    >
                        <v-icon>mdi-account</v-icon>
                        <v-toolbar-title class="font-weight-light">User Profile</v-toolbar-title>
                        <v-spacer></v-spacer>
                        <v-btn
                                color="purple darken-3"
                                fab
                                small
                                @click="isEditing = !isEditing"
                        >
                            <v-icon v-if="isEditing">mdi-close</v-icon>
                            <v-icon v-else>mdi-pencil</v-icon>
                        </v-btn>
                    </v-toolbar>
                    <v-card-text>
                        <v-text-field
                                :disabled="!isEditing"
                                color="white"
                                label="Name"
                        ></v-text-field>
                        <v-autocomplete
                                :disabled="!isEditing"
                                :items="states"
                                color="white"
                                item-text="name"
                                label="State"
                        ></v-autocomplete>
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                                :disabled="!isEditing"
                                color="success"
                                @click=""
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
                        Your profile has been updated
                    </v-snackbar>
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

    export default {
        name: "Profile",
        components: {NavigationBar, Footer, ChallengeCard},
        data() {
            return {
                user: {
                    name: 'John',
                    pictureURL: 'https://cdn.vuetifyjs.com/images/john.jpg'
                },
                states: [],
                hasSaved: false,
                isEditing: null,
                model: null,
            }
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
