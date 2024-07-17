<template>
    <b-card class="my-auto mx-auto mt-5 w-25">
        <template #header>
            Kullanıcı girişi
        </template>
        <b-alert :show="authFailed" variant="danger">Kullanıcı adı veya parola hatalı</b-alert>
        <b-form>
            <b-form-group label="e-posta">
                <b-form-input v-model="username" autofocus @keydown.enter="$refs.password.focus()"/>
            </b-form-group>
            <b-form-group label="Parola">
                <b-form-input ref="password" v-model="password" type="password" @keydown.enter="$refs.loginButton.click()"/>
            </b-form-group>
            <b-form-row>
                <b-col class="text-center mt-3">
                    <b-button ref="loginButton" size="lg" variant="primary" @click="login({username, password})">
                        <span class="mr-1"><b-icon-file-lock/></span>
                        Giriş
                    </b-button>
                </b-col>
            </b-form-row>
        </b-form>
    </b-card>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
    name: "LoginForm",
    data() {
        return {
            username: '',
            password: '',
        }
    },
    computed: {
        ...mapGetters('authentication', ['loggedIn', 'authFailed']),
    },
    methods: {
        ...mapActions('authentication', ['login']),
    }
}
</script>
