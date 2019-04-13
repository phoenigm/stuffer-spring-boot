<template>
    <v-card
            color="purple lighten-1"
    >
        <v-card-title>
            <v-layout justify-center>
                <span class="title font-weight-light">Уфа - Казань</span>
            </v-layout>
        </v-card-title>

        <v-card-text class="headline font-weight-bold">

            <v-layout row>
                <v-flex>
                    <v-layout column>
                        <v-flex>
                            <span>Delivery date:  23-03-2019 20:00</span>
                        </v-flex>
                        <v-flex>
                            <span>Delivery date:  23-03-2019 20:00</span>
                        </v-flex>
                    </v-layout>
                </v-flex>

                <v-flex>
                    <v-layout column>
                        <v-flex>
                            <span>From: Башкортостан, г Уфа, ул Первомайская, 49</span>
                        </v-flex>
                        <v-flex>
                            <span>To: Татарстан, г Казань, ул  Пушкина, 32</span>
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>

        </v-card-text>


        <v-divider light></v-divider>

        <v-card-actions>
            <v-list-tile class="grow">
                <v-list-tile-avatar color="grey darken-3">
                    <v-img
                            class="elevation-5"
                            :src=trip.avatar
                    ></v-img>
                </v-list-tile-avatar>

                <v-list-tile-content>
                    <v-list-tile-title>Азат Мухаметзянов</v-list-tile-title>
                </v-list-tile-content>

            </v-list-tile>


        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        name: "ChallengeCard",
        data() {
            return {
                trip: {
                    author: 'phoenigm',
                    avatar: 'https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light',
                    name: 'Pull-ups',
                    description: 'I want to learn to pull up 15 times in 3 months',
                    tags: [
                        {name: 'Sport', color: 'red'},
                        {name: 'Willpower', color: 'blue'},
                        {name: 'Training', color: 'green'},
                    ],
                    deadline: '04-01-2019',
                    participants: [
                        {username: ' a', avatar: 'https://randomuser.me/api/portraits/men/30.jpg'},
                        {username: ' b', avatar: 'https://randomuser.me/api/portraits/men/31.jpg'},
                        {username: ' c', avatar: 'https://randomuser.me/api/portraits/men/35.jpg'}
                    ]
                }
            }
        }
    }
</script>

<style scoped>

</style>
