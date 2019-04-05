<template>
    <v-card
            class="mx-auto"
            color="#ffc107"
            max-width="400"
    >
        <v-card-title>
            <v-layout justify-center>
                <span class="title font-weight-light">{{challenge.name}}</span>
            </v-layout>
        </v-card-title>

        <v-card-text class="headline font-weight-bold">
            <v-chip v-for="i in challenge.tags" :color="i.color" :key="i.name" text-color="white">{{i.name}}</v-chip>
        </v-card-text>

        <v-layout>
            <v-flex ml-3>
                <v-icon class="mr-1">fas fa-hourglass-start</v-icon>
                <span>23-03-2019</span>
            </v-flex>
            <v-flex>
                <v-icon class="mr-1">fas fa-hourglass-end</v-icon>
                <span>{{challenge.deadline}}</span>
            </v-flex>
        </v-layout>

        <v-divider light></v-divider>

        <v-card-actions>
            <v-list-tile class="grow">
                <v-list-tile-avatar color="grey darken-3">
                    <v-img
                            class="elevation-5"
                            :src=challenge.avatar
                    ></v-img>
                </v-list-tile-avatar>

                <v-list-tile-content>
                    <v-list-tile-title>{{challenge.author}}</v-list-tile-title>
                </v-list-tile-content>

                <v-layout
                        align-center
                        justify-end
                >
                    <v-avatar class="mx-auto" v-for="p in challenge.participants">
                        <img :src="p.avatar" :alt="p.username">
                    </v-avatar>
                    <span>+3</span>
                </v-layout>
            </v-list-tile>


        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        name: "ChallengeCard",
        data() {
            return {
                challenge: {
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
