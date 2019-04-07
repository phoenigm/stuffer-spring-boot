<template>
    <v-app class="background">
        <v-container fluid fill-height>
            <v-layout align-center justify-center>
                <v-flex xs11 sm6 md2>
                    <v-card class="elevation-12">
                        <v-toolbar dark color="blue darken-2">
                            <v-spacer></v-spacer>
                            <v-toolbar-title>Login</v-toolbar-title>
                            <v-spacer></v-spacer>
                        </v-toolbar>
                        <v-card-text>
                            <v-alert
                                    :value="!!serverMessage"
                                    type="error"
                            >
                                {{serverMessage}}
                            </v-alert>
                            <v-form>
                                <v-text-field v-model="loginForm.email" prepend-icon="person" name="login"
                                              label="Login"
                                              type="text"></v-text-field>
                                <v-text-field v-model="loginForm.password" prepend-icon="lock" name="password"
                                              label="Password" id="password"
                                              type="password"></v-text-field>
                            </v-form>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="primary" @click="login">Login</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
    </v-app>
</template>

<script>
    import {AXIOS} from "../api/http-common";


    export default {
        name: "Login",

        data() {
            return {
                loginForm: {
                    email: null,
                    password: null
                },
                serverMessage: null
            }
        },

        created() {
            document.title = "Login";

        },
        methods: {
            login() {
                const form = new FormData();
                form.append('grant_type', 'password');
                form.append('username', this.loginForm.email);
                form.append('password', this.loginForm.password);

                AXIOS.post('/oauth/token', form)
                    .then(response => {
                        const token = response.data['access_token'];
                        console.log(token);

                        localStorage.setItem('token', token);
                        this.$store.commit('setToken', token);

                        if (response.status === 200) {
                            this.$router.push('/profile');
                        }
                    })
                    .catch(error => {
                        if (error.status === 400 ) {
                            this.serverMessage = error.data['error_description'];
                        }
                        console.log( 'login error');
                        console.log(error);
                        console.log(error.data);
                        this.$store.dispatch('logout');
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
</style>
