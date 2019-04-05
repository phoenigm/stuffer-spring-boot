<template>
    <v-footer
            absolute
            height="auto"
            color="indigo lighten-1"
    >
        <v-layout
                justify-center
                row
                wrap
        >
            <v-flex

                    text-xs-center
                    white--text
                    xs12
            >

                <v-btn
                        v-for="icon in icons"
                        :key="icon"
                        class="mx-3 white--text"
                        icon
                >
                    <v-icon size="24px">{{ icon }}</v-icon>
                </v-btn>
            </v-flex>

            <v-btn
                    v-for="link in links"
                    :key="link"
                    color="white"
                    flat
                    round
            >
                {{ link }}
            </v-btn>
            <v-flex
                    lighten-2
                    py-2
                    text-xs-center
                    white--text
                    xs12
            >
                &copy; 2019 — <strong>Challenger Application</strong>
            </v-flex>
        </v-layout>
    </v-footer>
</template>

<script>

    export default {
        name: "Footer",
        data: () => ({
            icons: [
                'fab fa-facebook',
                'fab fa-twitter',
                'fab fa-google-plus',
                'fab fa-linkedin',
                'fab fa-instagram'
            ],
            links: [
                'Home',
                'About Us',
                'Team',
                'Services',
                'Blog',
                'Contact Us'
            ]
        })
    }
</script>

<style scoped>

</style>