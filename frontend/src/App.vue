<template>

    <router-view/>

</template>

<script>

    export default {
        name: 'app',
        data() {
            return {
                right: null
            }
        },
        created() {
            document.title = "Look Ma!";
        }
    }
</script>

<style lang="scss">
    @import url("https://fonts.googleapis.com/css?family=Poppins");

    body {
        font-family: 'Poppins', sans-serif;
    }
</style>
