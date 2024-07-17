<template>
    <b-modal cancel-title="Kapat" ok-title="Kaydet" ok-variant="success" title="Kullanıcı düzenleyici" visible
             @hidden="$emit('close')" @ok="onSaveClicked">
        <b-form>
            <b-form-group label="Kullanıcı adı">
                <b-form-input v-model="name"/>
            </b-form-group>
            <b-form-group label="E-mail">
                <b-form-input v-model="email"/>
            </b-form-group>
            <b-form-group label="Yetkiler">
                <b-form-checkbox-group v-model="permissions" :options="availablePermissions" stacked switches/>
            </b-form-group>
        </b-form>
    </b-modal>
</template>

<script>
import {mapActions} from "vuex";

export default {
    name: "UserEditor",
    props: {
        value: Object,
    },
    data() {
        return {
            id: null,
            name: '',
            email: '',
            permissions: [],
            availablePermissions: [
                "LOGIN",
                "USERS",
                "DASHBOARD",
                "CUSTOMERS",
                "OPPORTUNITIES",
                "CONTACTS",
                "PRODUCTS",
            ]
        }
    },
    created() {
        this.id = this.value.id || -1;
        this.name = this.value.name || '';
        this.email = this.value.email || '';
        this.permissions = this.value.permissions || [];
    },
    methods: {
        ...mapActions("users", ["createUser", "updateUser"]),
        onSaveClicked() {
            if (this.id === -1) {
                this.createUser({
                    name: this.name,
                    email: this.email,
                    permissions: this.permissions
                }).then(() => this.$emit("close"));
            } else {
                this.updateUser({
                    id: this.id,
                    name: this.name,
                    email: this.email,
                    permissions: this.permissions
                }).then(() => this.$emit("close"));
            }
        }
    }
}
</script>
