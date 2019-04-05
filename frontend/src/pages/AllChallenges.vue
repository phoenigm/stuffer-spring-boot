<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container fluid>
                <v-container fluid grid-list-sm>
                    <v-layout row wrap>

                        <v-flex v-for="i in 31" :key="i" xs12 sm6 md4 lg3>
                            <ChallengeCard/>
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

    export default {
        name: "AllChallenges",
        components: {NavigationBar, ChallengeCard}
    }
</script>

<style scoped>

</style>
