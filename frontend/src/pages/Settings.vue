<template>
    <v-app>
        <NavigationBar/>

        <v-container>
            <v-card
                    color="red lighten-2"
                    dark
            >
                <v-card-title class="headline red lighten-3">
                    Search for Public APIs
                </v-card-title>
                <v-card-text>
                    <v-autocomplete
                            v-model="model1"
                            :items="regions"
                            :loading="isLoading1"
                            :search-input.sync="search1"
                            color="white"
                            hide-no-data
                            hide-selected
                            item-text="Description"
                            item-value="API"
                            label="Public APIs"
                            placeholder="Start typing to Search"
                            prepend-icon="mdi-database-search"
                            return-object
                    ></v-autocomplete>

                    <v-autocomplete
                            v-model="model"
                            :items="localities"
                            :loading="isLoading"
                            :search-input.sync="search"
                            color="white"
                            hide-no-data
                            hide-selected
                            item-text="Description"
                            item-value="API"
                            label="Public APIs"
                            placeholder="Start typing to Search"
                            prepend-icon="mdi-database-search"
                            return-object
                    ></v-autocomplete>
                </v-card-text>

            </v-card>
        </v-container>


    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import {AXIOS} from '../api/http-common'

    export default {
        name: "Settings",
        components: {NavigationBar},

        data: () => ({
            departureLocalityData: [],
            departureRegionData: [],
            isLoading: false,
            model: null,
            search: null,
            isLoading1: false,
            model1: null,
            search1: null
        }),

        computed: {
            localities() {
                return this.departureLocalityData.map(e => e.name)
            },
            regions() {
                return this.departureRegionData.map(e => e.name)
            }
        },

        watch: {
            search(val) {

                // Items have already been requested
                if (this.isLoading) return

                this.isLoading = true

                // Lazily load input items
                AXIOS.get(`/api/trip/localities?q=${val}&region=${this.model1}`)
                    .then(res => {
                        this.localityData = res.data;
                    })
                    .catch(err => {
                        console.log(err)
                    })
                    .finally(() => (this.isLoading = false))
            },

            search1(val) {

                // Items have already been requested
                if (this.isLoading1) return

                this.isLoading1 = true

                // Lazily load input items
                AXIOS.get('/api/trip/regions?q=' + val)
                    .then(res => {
                        this.regionData = res.data;
                    })
                    .catch(err => {
                        console.log(err)
                    })
                    .finally(() => (this.isLoading1 = false))
            },
        },
    }
</script>

<style scoped>

</style>
