<template>
    <v-tabs
            centered
            color="gray"
            dark
            icons-and-text
    >
        <v-tabs-slider color="blue"></v-tabs-slider>

        <v-tab href="#tab-1">
            Archive
            <v-icon>archive</v-icon>
        </v-tab>

        <v-tab href="#tab-2">
            Current
            <v-icon>grade</v-icon>
        </v-tab>

        <v-tab href="#tab-3">
            Search
            <v-icon>search</v-icon>
        </v-tab>

        <v-tab-item
                v-for="i in 3"
                :key="i"
                :value="'tab-' + i"
        >
            <v-container fluid grid-list-sm>
                <v-layout row wrap>
                    <v-flex v-for="i in 7" :key="i" xs4>
                        <ChallengeCard ></ChallengeCard>
                    </v-flex>
                </v-layout>
            </v-container>

        </v-tab-item>
    </v-tabs>
</template>

<script>
    import ChallengeCard from "./ChallengeCard";
    export default {
        name: "Challenges",
        components: {ChallengeCard},
        data() {
            return {
                text: 'Help me'
            }
        }
    }
</script>

<style scoped>

</style>
