<template>
    <v-app>
        <NavigationBar/>

    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import {AXIOS} from '../api/http-common'

    export default {
        name: "Settings",
        components: {NavigationBar},
    }
</script>

<style scoped>

</style>
