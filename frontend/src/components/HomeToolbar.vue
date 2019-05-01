<template>
    <v-toolbar flat color="transparent">

        <v-img
                :src="require('./../assets/logo.png')"
                max-height="90px" max-width="90px" contain
        />
        <v-spacer></v-spacer>
        <v-toolbar-items class="hidden-sm-and-down">
            <v-btn class="toolbar-btn" right round color="primary" append to="/login">Sign in</v-btn>
            <v-btn class="toolbar-btn" outline round color="primary" append to="/registration">Join</v-btn>
        </v-toolbar-items>
    </v-toolbar>
</template>

<script>
    export default {
        name: "HomeToolbar"
    }
</script>

<style scoped>
    .toolbar-btn {
        height: 40px !important;
    }
</style>
