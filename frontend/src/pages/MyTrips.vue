<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container fluid>
                <v-container fluid grid-list-sm>
                    <v-layout row wrap>

                        <v-flex v-for="trip in trips" :key="trip.id" xs12>
                            <ChallengeCard :trip="trip" :color="'purple'" :text="'headline'"/>
                        </v-flex>
                    </v-layout>
                </v-container>

            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import ChallengeCard from "../components/ChallengeCard";
    import {AXIOS} from "../api/http-common";

    export default {
        name: "MyChallenges",
        components: {ChallengeCard, NavigationBar},

        data() {
            return {
                trips: this.$store.getters['getUser']
            }
        },

        mounted() {
            AXIOS.get('/api/trip/my')
                .then(response => {
                    console.log(response.data)
                    this.$store.commit('setTrips', response.data);
                    this.trips = response.data;
                }).catch(error => {
                console.log(error);
            })
        }

    }
</script>

<style scoped>

</style>
