<template>
    <v-app class="background">
        <v-container grid-list-lg grid-list-md>
            <v-layout row justify-center align-center fill-height>
                <v-flex lg5 md8>
                    <v-container white grid-list-lg class="border-container" text-xs-center>
                        <h1>Not yet registered? Create an account</h1>
                        <form>
                            <v-layout column fill-height>
                                <v-flex>
                                    <v-alert
                                            :value="serverMessage != null"
                                            type="error"
                                    >
                                        {{serverMessage}}
                                    </v-alert>
                                </v-flex>
                                <v-flex>
                                    <v-layout align-center justify-center row fill-height>
                                        <v-flex>
                                            <v-text-field
                                                    v-model="userForm.firstName"
                                                    :rules="[rules.required]"
                                                    :error-messages="validationMessages.firstName"
                                                    label="First name"
                                                    name="firstName"
                                            ></v-text-field>
                                        </v-flex>
                                        <v-flex>
                                            <v-text-field
                                                    v-model="userForm.lastName"
                                                    :rules="[rules.required]"
                                                    :error-messages="validationMessages.lastName"
                                                    label="Last name"
                                                    name="Last name"
                                            ></v-text-field>
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                                <v-flex>
                                    <v-layout align-center justify-center row fill-height>
                                        <v-flex>
                                            <v-text-field
                                                    v-model="userForm.email"
                                                    :rules="[rules.required]"
                                                    :error-messages="validationMessages.email"
                                                    label="Email"
                                                    name="email"
                                            ></v-text-field>
                                        </v-flex>
                                        <v-flex>
                                            <v-text-field
                                                    v-model="userForm.phoneNumber"
                                                    :rules="[rules.required]"
                                                    :error-messages="validationMessages.phoneNumber"
                                                    label="Phone number"
                                                    name="phoneNumber"
                                            ></v-text-field>
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                                <v-flex>
                                    <v-layout align-center justify-center row fill-height>
                                        <v-flex>
                                            <v-text-field
                                                    v-model="userForm.password"
                                                    :append-icon="show1 ? 'visibility' : 'visibility_off'"
                                                    :rules="[rules.required, rules.min]"
                                                    :type="show1 ? 'text' : 'password'"
                                                    :error-messages="validationMessages.password"
                                                    name="password"
                                                    label="Password"
                                                    hint="At least 6 characters"
                                                    counter
                                                    @click:append="show1 = !show1"
                                            ></v-text-field>
                                        </v-flex>
                                        <v-flex>
                                            <v-text-field
                                                    v-model="userForm.confirmedPassword"
                                                    :rules="[rules.required, rules.min]"
                                                    :error-messages="validationMessages.confirmedPassword"
                                                    name="confirmedPassword"
                                                    type="password"
                                                    label="Confirm password"
                                                    hint="Confirm password"
                                                    class="input-group&#45;&#45;focused"
                                            ></v-text-field>
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                                <v-flex>
                                    <v-checkbox
                                            v-model="userForm.agreement"
                                            label="I agree with terms and conditions"
                                            :error-messages="validationMessages.agreement"
                                    ></v-checkbox>
                                    <v-btn @click="register()" color="primary">Register</v-btn>
                                </v-flex>
                            </v-layout>
                        </form>
                    </v-container>
                </v-flex>
            </v-layout>
        </v-container>
    </v-app>
</template>

<script>
    import {UserService} from "../api/user";
    import {AXIOS} from '../api/http-common'

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
                validationMessages: {},
                userForm: {
                    firstName: '',
                    lastName: '',
                    phoneNumber: '',
                    email: '',
                    password: '',
                    confirmedPassword: '',
                    agreement: false
                },

            }
        },
        created() {
            document.title = "Registration";
        },
        methods: {
            register() {
                AXIOS.post('api/auth/registration', this.userForm,
                    {
                        withCredentials: false
                    })
                    .then(response => {
                        this.serverMessage = null;
                        this.response = response;

                        const status = response.status;
                        if (status === 201) {
                            this.$router.push({path: '/login'});
                        }
                    })
                    .catch(error => {
                        this.validationMessages = {};
                        this.response = error;

                        if (error.status === 400) {
                            this.validationMessages = error.data;
                        }
                        if (error.status === 409) {
                            this.serverMessage = error.data;
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
