<template>
    <div>
        <h1>Şirketler</h1>
        <datatable-button create-button label="Yeni şirket oluştur" @click="editedCompany.id = -1"/>
        <b-table :fields="fields" :items="companyList" bordered class="mt-2" hover small striped>
            <template #cell(actions)="row">
                <datatable-button edit-button @click="Object.assign(editedCompany, companyList.find(u => u.id === row.item.id))"/>
                <datatable-button delete-button @click="deleteCompany(companyList.find(u => u.id === row.item.id))"/>
            </template>
        </b-table>
        <company-editor v-if="editedCompany.id" v-model="editedCompany" @close="editedCompany.id = null"/>
    </div>
</template>

<script>
import CompanyEditor from "@/components/CompanyEditor.vue";
import {mapActions, mapGetters} from "vuex";
import DatatableButton from "@/components/DatatableButton.vue";

export default {
    name: "CompanyList",
    components: {DatatableButton, CompanyEditor},
    data() {
        return {
            fields: [
                {key: 'id', label: 'No'},
                {key: 'name', label: 'Adı'},
                {key: 'website', label: 'Web'},
                {key: 'address', label: 'Adres'},
                {key: 'phoneNumber', label: 'Telefon'},
                {key: 'actions', label: 'Eylemler', tdClass: 'text-right', thClass: 'text-right'}
            ],
            editedCompany: {
                id: null
            }
        }
    },
    created() {
        this.refreshCompanyList();
    },
    computed: {
        ...mapGetters('companies', ['companyList']),
    },
    methods: {
        ...mapActions('companies', ['refreshCompanyList', "deleteCompany"]),
    }
}
</script>
