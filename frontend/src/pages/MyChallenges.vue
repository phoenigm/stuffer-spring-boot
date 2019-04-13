<template>
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-tabs-items>
                    <v-tab-item
                            key="A"
                    >
                        <v-card flat>
                            <v-card-text></v-card-text>
                        </v-card>
                    </v-tab-item>

                    <v-tab-item
                            key="A"
                    >
                        <v-card flat>
                            <v-card-text></v-card-text>
                        </v-card>
                    </v-tab-item>

                    <v-tab-item
                            key="A"
                    >
                        <v-card flat>
                            <v-card-text></v-card-text>
                        </v-card>
                    </v-tab-item>
                </v-tabs-items>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";

    export default {
        name: "MyChallenges",
        components: {NavigationBar},

    }
</script>

<style scoped>

</style>
