<template>
    <div id="container">
        <app-navbar/>
        <b-container id="content" class="mt-2" fluid>
            <router-view v-if="loggedIn"/>
            <LoginForm v-else/>
        </b-container>
    </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import LoginForm from "@/components/LoginForm.vue";
import AppNavbar from "@/components/AppNavbar.vue";

export default {
    name: 'App',
    components: {LoginForm, AppNavbar},
    data() {
        return {
            currentPage: 'dashboard',
        }
    },
    created() {
        this.refreshAuthentication();
    },
    computed: {
        ...mapGetters('authentication', ['loggedIn', 'currentUserPermissions']),
    },
    methods: {
        ...mapActions('authentication', ['refreshAuthentication']),
    }
}
</script>
