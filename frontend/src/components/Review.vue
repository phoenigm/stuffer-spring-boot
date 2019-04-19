<template>
    <v-dialog
            v-model="dialog"
            max-width="500"

    >
        <v-card color="purple" dark>
            <v-card-title class="headline">
                <v-layout row justify-center align-center>
                    <v-flex shrink mr-2>
                        <v-avatar>
                            <img
                                    :src="user.avatarUrl"
                                    :alt="user.firstName"
                            >
                        </v-avatar>
                    </v-flex>
                    <v-flex>
                        {{user.firstName}} {{user.lastName}}
                    </v-flex>
                </v-layout>
            </v-card-title>

            <v-card-text>
                <v-layout column>

                    <v-flex>
                        <v-rating
                                v-model="rating"
                                background-color="white"
                                color="yellow accent-4"
                                dense
                                :readonly="true"
                                half-increments
                                hover
                                size="18"
                        ></v-rating>
                    </v-flex>

                    <v-flex>
                        <v-textarea
                                box
                                label="Review"
                                auto-grow
                                readonly
                                :value="review"
                        ></v-textarea>
                    </v-flex>
                </v-layout>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
    export default {
        name: "Review",
        props: ['user', 'review', 'rating']
    }
</script>

<style scoped>

</style>
