<template>
    <v-toolbar flat color="transparent">

        <v-img
                src="http://www.pngall.com/wp-content/uploads/2/Runner-PNG-Free-Download.png"
                max-height="30px" max-width="30px" contain
        />
        <v-toolbar-title class="blue--text text--darken-3">CHALLENGER</v-toolbar-title>
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
