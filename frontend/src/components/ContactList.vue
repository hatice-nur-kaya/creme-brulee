<template>
    <div>
        <h1>İletişim kişileri</h1>
        <datatable-button create-button label="Yeni iletişim kişi kaydı oluştur" @click="editedContact.id = -1"/>
        <b-table :fields="fields" :items="contactList" bordered class="mt-2" hover small striped>
            <template #cell(companyId)="row">
                {{ (companyList.find(c => c.id === row.item.companyId) || {}).name }}
            </template>
            <template #cell(actions)="row">
                <datatable-button edit-button @click="Object.assign(editedContact, contactList.find(u => u.id === row.item.id))"/>
                <datatable-button delete-button @click="deleteContact(contactList.find(u => u.id === row.item.id))"/>
            </template>
        </b-table>
        <contact-editor v-if="editedContact.id" v-model="editedContact" @close="editedContact.id = null"/>
    </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import ContactEditor from "@/components/ContactEditor.vue";
import DatatableButton from "@/components/DatatableButton.vue";

export default {
    name: "ContactList",
    components: {DatatableButton, ContactEditor},
    data() {
        return {
            fields: [
                {key: 'id', label: 'No'},
                {key: 'name', label: 'Ad'},
                {key: 'email', label: 'E-posta'},
                {key: 'title', label: 'Title'},
                {key: 'pronoun', label: 'Cinsiyet'},
                {key: 'position', label: 'Görev'},
                {key: 'department', label: 'Departman'},
                {key: 'phone', label: 'Telefon'},
                {key: 'companyId', label: 'Şirket'},
                {key: 'linkedInUrl', label: 'LinkedIn'},
                {key: 'actions', label: '', tdClass: 'text-right', thClass: 'text-right'}
            ],

            editedContact: {
                id: null,
                name: '',
                email: '',
                title: '',
                pronoun: '',
                position: '',
                department: '',
                phone: '',
                companyId: '',
                linkedInUrl: ''
            }
        }
    },
    created() {
        this.refreshContactList();
        this.refreshCompanyList();
    },
    computed: {
        ...mapGetters('contacts', ['contactList']),
        ...mapGetters('companies', ['companyList']),
    },
    methods: {
        ...mapActions('contacts', ['refreshContactList', "deleteContact"]),
        ...mapActions('companies', ['refreshCompanyList']),
    }
}
</script>
