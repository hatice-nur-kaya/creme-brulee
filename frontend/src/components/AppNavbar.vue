<template>
    <b-navbar v-if="currentUser.id" class="border-bottom" fixed="top" sticky>
        <b-navbar-brand target="/">
            <b-icon-cup-straw font-scale="2"/>
            Creme Brulee
        </b-navbar-brand>
        <b-navbar-nav>
            <b-nav-item to="/Dashboard">
                <b-icon-graph-up/>
                Göstergeler
            </b-nav-item>
            <b-nav-item to="/Companies">
                <b-icon-building/>
                Şirketler
            </b-nav-item>
            <b-nav-item to="/Contacts">
                <b-icon-file-person/>
                Kişiler
            </b-nav-item>
            <b-nav-item to="/Products">
                <b-icon-gift/>
                Ürünler
            </b-nav-item>
            <b-nav-item to="/Opportunities">
                <b-icon-hand-index-thumb/>
                Fırsatlar
            </b-nav-item>
            <b-nav-item to="/Reports">
                <b-icon-file-bar-graph/>
                Raporlar
            </b-nav-item>
            <b-nav-item to="/Users">
                <b-icon-people/>
                Kullanıcılar
            </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown right>
                <template #button-content>
                    <b-icon-person-badge/>
                </template>
                <b-dropdown-header>
                    <b-icon-at/>
                    {{ currentUser.name }} - {{ currentUser.email }}
                </b-dropdown-header>
                <b-dropdown-divider/>
                <b-dropdown-item-button size="sm">
                    <b-button size="sm" variant="info">
                        <b-icon-gear/>
                        Tercihler
                        <!-- TODO Implement preferences dialog -->
                    </b-button>
                </b-dropdown-item-button>
                <b-dropdown-divider/>
                <b-dropdown-item-button size="sm">
                    <b-button size="sm" variant="warning">
                        <b-icon-lock/>
                        Parola değiştir
                        <!-- TODO Implement password change dialog -->
                    </b-button>
                    <b-button size="sm" variant="danger" @click="onButtonLogoutClicked">
                        <b-icon-door-open/>
                        Çıkış
                    </b-button>
                </b-dropdown-item-button>
            </b-nav-item-dropdown>
        </b-navbar-nav>
    </b-navbar>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
    name: "AppNavbar",
    computed: {
        ...mapGetters('authentication', ['currentUser']),
    },
    methods: {
        ...mapActions('authentication', ['logout']),
        onButtonLogoutClicked() {
            this.logout();
        }
    }
}
</script>
