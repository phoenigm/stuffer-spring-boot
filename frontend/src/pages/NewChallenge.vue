<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-app>
        <NavigationBar/>

        <v-content>
            <v-container>
                <v-layout align-start justify-center row fill-height>
                    <v-flex class="md8 xs12">
                        <v-card dark>
                            <v-form>
                                <v-container>
                                    <v-layout column justify-center wrap>
                                        <v-flex xs12 text-xs-center>
                                            <span class="display-3 ">Create new challenge</span>
                                        </v-flex>
                                        <v-flex xs12>

                                            <v-text-field
                                                    v-model="challengeForm.title"
                                                    box
                                                    color="blue-grey lighten-2"
                                                    label="Title"
                                            ></v-text-field>

                                        </v-flex>
                                        <v-flex xs12 ml-3>
                                            <v-radio-group v-model="challengeForm.checkType">
                                                <template v-slot:label>
                                                    <div>How do you want to confirm the challenge?</div>
                                                </template>
                                                <v-radio value="self">
                                                    <template v-slot:label>
                                                        <div>Self</div>
                                                    </template>
                                                </v-radio>
                                                <v-radio value="friend">
                                                    <template v-slot:label>
                                                        <div>Friend</div>
                                                    </template>
                                                </v-radio>
                                                <v-radio value="vote">
                                                    <template v-slot:label>
                                                        <div>Vote</div>
                                                    </template>
                                                </v-radio>
                                            </v-radio-group>
                                        </v-flex>
                                        <v-flex xs12 v-if="challengeForm.checkType==='friend'">
                                            <v-autocomplete
                                                    v-model="friends"
                                                    :disabled="isUpdating"
                                                    :items="people"
                                                    box
                                                    chips
                                                    color="blue-grey lighten-2"
                                                    label="Friends for confirming my challenge"
                                                    item-text="name"
                                                    item-value="name"
                                                    multiple
                                            >
                                                <template v-slot:selection="data">
                                                    <v-chip
                                                            :selected="data.selected"
                                                            close
                                                            class="chip--select-multi"
                                                            @input="remove(data.item)"
                                                    >
                                                        <v-avatar>
                                                            <img :src="data.item.avatar">
                                                        </v-avatar>
                                                        {{ data.item.name }}
                                                    </v-chip>
                                                </template>
                                                <template v-slot:item="data">
                                                    <template v-if="typeof data.item !== 'object'">
                                                        <v-list-tile-content v-text="data.item"></v-list-tile-content>
                                                    </template>
                                                    <template v-else>
                                                        <v-list-tile-avatar>
                                                            <img :src="data.item.avatar">
                                                        </v-list-tile-avatar>
                                                        <v-list-tile-content>
                                                            <v-list-tile-title
                                                                    v-html="data.item.name"></v-list-tile-title>
                                                            <v-list-tile-sub-title
                                                                    v-html="data.item.group"></v-list-tile-sub-title>
                                                        </v-list-tile-content>
                                                    </template>
                                                </template>
                                            </v-autocomplete>
                                        </v-flex>
                                        <v-flex xs12 ml-3>
                                            <v-switch
                                                    v-model="challengeForm.privacyType"
                                                    label="Public"
                                                    :value="challengeForm.privacyType == null ? 'private' : 'public'"
                                            ></v-switch>
                                        </v-flex>
                                        <v-flex xs12>
                                            <v-textarea
                                                    v-model="challengeForm.description"
                                                    auto-grow
                                                    box
                                                    color="deep-purple"
                                                    label="Description"
                                                    rows="3"
                                            ></v-textarea>
                                        </v-flex>

                                        <v-flex xs12>
                                            <v-dialog
                                                    ref="dialog"
                                                    v-model="modal"
                                                    :return-value.sync="date"
                                                    persistent
                                                    lazy
                                                    full-width
                                                    width="290px"
                                            >
                                                <template v-slot:activator="{ on }">
                                                    <v-text-field
                                                            v-model="challengeForm.deadline"
                                                            label="Deadline"
                                                            prepend-icon="event"
                                                            readonly
                                                            v-on="on"
                                                    ></v-text-field>
                                                </template>
                                                <v-date-picker v-model="challengeForm.deadline" scrollable>
                                                    <v-spacer></v-spacer>
                                                    <v-btn flat color="primary" @click="modal = false">Cancel</v-btn>
                                                    <v-btn flat color="primary" @click="$refs.dialog.save(date)">OK
                                                    </v-btn>
                                                </v-date-picker>
                                            </v-dialog>
                                        </v-flex>

                                        <v-flex>
                                            <span class="subheading mb-2">Conditions</span>
                                            <v-text-field
                                                    label="Condition"
                                                    box
                                                    append-icon="fas fa-plus"
                                                    @click="createCondition"
                                            ></v-text-field>

                                        </v-flex>
                                    </v-layout>
                                </v-container>
                            </v-form>
                            <v-divider></v-divider>
                            <v-card-actions>

                                <v-spacer></v-spacer>
                                <v-btn
                                        color="primary"
                                        depressed
                                        @click=""
                                >
                                    Create challenge
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";

    export default {
        name: "NewChallenge",
        components: {NavigationBar},
        data() {
            const srcs = {
                1: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
                2: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
                3: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
                4: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
                5: 'https://cdn.vuetifyjs.com/images/lists/5.jpg'
            }
            return {
                challengeForm: {
                    title: null,
                    description: null,
                    privacyType: 'private',
                    checkType: null,
                    deadline: new Date().toISOString().substr(0, 10),
                    conditions: []
                },

                date: new Date().toISOString().substr(0, 10),
                menu: false,
                modal: false,
                menu2: false,


                autoUpdate: true,
                friends: ['Sandra Adams', 'Britta Holt'],
                isUpdating: false,
                name: 'Midnight Crew',
                people: [
                    {header: 'Group 1'},
                    {name: 'Sandra Adams', group: 'Group 1', avatar: srcs[1]},
                    {name: 'Ali Connors', group: 'Group 1', avatar: srcs[2]},
                    {name: 'Trevor Hansen', group: 'Group 1', avatar: srcs[3]},
                    {name: 'Tucker Smith', group: 'Group 1', avatar: srcs[2]},
                    {divider: true},
                    {header: 'Group 2'},
                    {name: 'Britta Holt', group: 'Group 2', avatar: srcs[4]},
                    {name: 'Jane Smith ', group: 'Group 2', avatar: srcs[5]},
                    {name: 'John Smith', group: 'Group 2', avatar: srcs[1]},
                    {name: 'Sandra Williams', group: 'Group 2', avatar: srcs[3]}
                ],
                title: 'The summer breeze'
            }
        },
        watch: {
            isUpdating(val) {
                if (val) {
                    setTimeout(() => (this.isUpdating = false), 3000)
                }
            }
        },
        methods: {
            remove(item) {
                const index = this.friends.indexOf(item.name);
                if (index >= 0) this.friends.splice(index, 1)
            },

            addChallenge() {
                console.log(this.challengeForm);
            },

        },

    }
</script>

<style scoped>

</style>
