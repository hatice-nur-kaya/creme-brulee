<template>
    <div>
        <h1>Kullanıcılar</h1>
        <datatable-button create-button label="Yeni kullanıcı oluştur" @click="editedUser.id = -1"/>
        <b-table :fields="fields" :items="userList" bordered class="mt-2" hover striped>
            <template #cell(permissions)="row">
                <ul>
                    <li v-for="permission in row.item.permissions" :key="row.item.id + '_perm_' + permission">{{ permissionNames[permission] }}</li>
                </ul>
            </template>
            <template #cell(actions)="row">
                <datatable-button edit-button @click="Object.assign(editedUser, userList.find(u => u.id === row.item.id))"/>
                <datatable-button delete-button @click="deleteUser(userList.find(u => u.id === row.item.id))"/>
            </template>
        </b-table>
        <user-editor v-if="editedUser.id" v-model="editedUser" @close="editedUser.id = null"/>
    </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import UserEditor from "@/components/UserEditor.vue";
import DatatableButton from "@/components/DatatableButton.vue";

export default {
    name: "UserList",
    components: {DatatableButton, UserEditor},
    data() {
        return {
            fields: [
                {key: 'id', label: 'No'},
                {key: 'name', label: 'Kullanıcı adı'},
                {key: 'email', label: 'E-posta'},
                {key: 'permissions', label: 'Yetkiler'},
                {key: 'actions', label: 'Eylemler', tdClass: 'text-right', thClass: 'text-right'}
            ],
            permissionNames: {
                LOGIN: "Giriş yapabilir",
                DASHBOARD: "Grafikleri görebilir",
                CUSTOMERS: "Müşterileri yönetebilir",
                USERS: "Kullanıcıları yönetebilir",
                OPPORTUNITIES: "Fırsatları yönetebilir",
                CONTACTS: "İletişim kurulacak kişileri yönetebilir",
                PRODUCTS: "Ürünleri yönetebilir",
                COMPANIES: "Şirketleri yönetebilir",
            },
            editedUser: {
                id: null,
                name: '',
                email: '',
                permissions: []
            }
        }
    },
    created() {
        this.refreshUserList();
    },
    computed: {
        ...mapGetters('users', ['userList'])
    },
    methods: {
        ...mapActions('users', ['refreshUserList', "deleteUser"]),
    }
}
</script>
