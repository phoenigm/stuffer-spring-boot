<template>

    <v-dialog
            v-model="dialog"
            max-width="500"

    >
        <v-card color="purple" dark>
            <v-card-title display-1>
                <v-layout row justify-center>
                    <v-flex>
                        <v-avatar>
                            <img
                                    :src="item.reviewer.avatarUrl"
                                    :alt="item.reviewer.firstName"
                            >
                        </v-avatar>
                    </v-flex>
                    <v-flex>
                        {{item.reviewer.firstName}} {{item.reviewer.lastName}}
                    </v-flex>
                </v-layout>
            </v-card-title>

            <v-card-text>
                <v-layout column>
                    <v-flex>
                        <v-avatar>
                            <img
                                    :src="item.reviewer.avatarUrl"
                                    :alt="item.reviewer.firstName"
                            >
                        </v-avatar>
                    </v-flex>
                    <v-flex>
                        {{item.reviewer.firstName}} {{item.reviewer.lastName}}
                    </v-flex>

                    <v-flex>
                        {{item.review}}
                    </v-flex>
                </v-layout>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
    export default {
        name: "ReviewDialog",
        props: ['review', 'dialog'],
        data() {
            return {
            }
        }
    }
</script>

<style scoped>

</style>
