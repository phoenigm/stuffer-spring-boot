<template>
    <v-app>
        <NavigationBar/>
        <v-content>
            <v-container>
                <v-layout justify-center>
                    <span class="display-4"> Coming soon...</span>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    export default {
        name: "BattleCry",
        components: {NavigationBar}
    }
</script>

<style scoped>

</style>
