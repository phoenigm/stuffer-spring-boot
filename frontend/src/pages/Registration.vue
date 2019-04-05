<template>
    <v-app class="background">
        <v-container grid-list-lg grid-list-md>
            <v-layout row justify-center align-center fill-height>
                <v-flex lg5 md8>
                    <v-container white grid-list-lg class="border-container" text-xs-center>
                        <h1>Not yet registered? Create an account</h1>
                        <v-layout row justify-center align-center fill-height>
                            <v-flex lg6>
                                Features
                            </v-flex>
                            <v-flex lg6>
                                <v-alert
                                        :value="serverMessage != null"
                                        type="error"
                                >
                                    {{serverMessage}}
                                </v-alert>
                                <form>
                                    <v-text-field
                                            v-model="userForm.firstName"
                                            :rules="[rules.required]"
                                            :error-messages="errors"
                                            label="First name"
                                            name="firstName"
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="userForm.lastName"
                                            :rules="[rules.required]"
                                            :error-messages="errors"
                                            label="Last name"
                                            name="Last name"
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="userForm.email"
                                            :rules="[rules.required]"
                                            :error-messages="errors"
                                            label="Email"
                                            name="email"
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="userForm.phoneNumber"
                                            :rules="[rules.required]"
                                            :error-messages="errors"
                                            label="Phone number"
                                            name="phoneNumber"
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="userForm.password"
                                            :append-icon="show1 ? 'visibility' : 'visibility_off'"
                                            :rules="[rules.required, rules.min]"
                                            :type="show1 ? 'text' : 'password'"
                                            :error-messages="errors"
                                            name="password"
                                            label="Password"
                                            hint="At least 6 characters"
                                            counter
                                            @click:append="show1 = !show1"
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="userForm.confirmedPassword"
                                            :rules="[rules.required, rules.min]"
                                            :error-messages="errors"
                                            name="confirmedPassword"
                                            type="password"
                                            label="Confirm password"
                                            hint="Confirm password"
                                            class="input-group--focused"
                                    ></v-text-field>


                                    <v-checkbox
                                            v-model="userForm.agreement"
                                            label="I agree with terms and conditions"
                                    ></v-checkbox>
                                    <v-btn @click="register()" color="primary">Register</v-btn>
                                </form>
                            </v-flex>
                        </v-layout>
                    </v-container>
                </v-flex>
            </v-layout>
        </v-container>
    </v-app>
</template>

<script>
    import {UserService} from "../api/user";

    const userApi = new UserService();

    export default {
        name: "Registration",
        data() {
            return {
                show1: false,
                rules: {
                    required: value => !!value || 'Required.',
                    min: v => v.length >= 6 || 'Min 6 characters',
                },

                serverMessage: null,
                response: null,
                errors: [],
                userForm: {
                    firstName: '',
                    lastName: '',
                    phoneNumber: '',
                    email: '',
                    password: '',
                    confirmedPassword: '',
                    agreement: false
                },
                user: {
                    id: -1
                }
            }
        },
        created() {
            document.title = "Registration";
        },
        methods: {
            register() {
                userApi.register(this.userForm)
                    .then(response => {
                        this.serverMessage = null;
                        this.response = response;
                        this.user = response.data.id;
                        console.log(this.user.id);

                        const status = response.status;
                        if (status === 200) {
                            this.$store.commit('loadUser', response.data);
                            this.$router.push({path: '/login'});
                        }
                    })
                    .catch(error => {
                        if (typeof error.response !== 'undefined') {
                            this.serverMessage = error.response.data.message;
                        }
                        this.errors.push(error);
                    });
            }
        }
    }
</script>

<style scoped>
    .background {
        background: #3A1C71 !important;
        background: -webkit-linear-gradient(to right, #FFAF7B, #D76D77, #3A1C71) !important;
        background: linear-gradient(to right, #FFAF7B, #D76D77, #3A1C71) !important;
    }

    .border-container {
        border-radius: 5px;
    }
</style>
