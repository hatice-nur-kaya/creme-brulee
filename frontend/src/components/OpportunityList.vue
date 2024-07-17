<template>
    <div>
        <h1>Fırsatlar</h1>
        <datatable-button create-button label="Yeni fırsat oluştur" @click="editedOpportunity.id = -1"/>
        <b-table :fields="fields" :items="opportunityList" bordered class="mt-2" hover small striped>
            <template #cell(salesPersonId)="row">
                {{ (userList.find(u => u.id === row.item.salesPersonId) || {}).name }}
            </template>
            <template #cell(contactId)="row">
                {{ (contactList.find(u => u.id === row.item.contactId) || {}).name }}
            </template>
            <template #cell(status)="row">
                {{ statusNames[row.item.status] }}
            </template>
            <template #cell(actions)="row">
                <datatable-button edit-button @click="Object.assign(editedOpportunity, opportunityList.find(u => u.id === row.item.id))"/>
                <datatable-button delete-button @click="deleteOpportunity(opportunityList.find(u => u.id === row.item.id))"/>
            </template>
        </b-table>
        <opportunity-editor v-if="editedOpportunity.id" v-model="editedOpportunity" @close="editedOpportunity.id = null"/>
    </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import OpportunityEditor from "@/components/OpportunityEditor.vue";
import DatatableButton from "@/components/DatatableButton.vue";

export default {
    name: "OpportunityList",
    components: {DatatableButton, OpportunityEditor},
    data() {
        return {
            fields: [
                {key: 'id', label: 'No'},
                {key: 'salesPersonId', label: 'Satış sorumlusu'},
                {key: 'status', label: 'Durum'},
                {key: 'contactId', label: 'İletişim kişisi'},
                {key: 'discount', label: 'İndirim %'},
                {key: 'unit', label: 'Adet'},
                {key: 'invoiceDate', label: 'Fatura tarihi'},
                {key: 'actions', label: 'Eylemler', tdClass: 'text-right', thClass: 'text-right'},
            ],
            statusNames: {
                SOLD: "Satıldı",
                CONTINUES: "Görüşme Devam Ediyor",
                NOTSOLD: "Olumsuz",
            },
            editedOpportunity: {
                id: null,
                salesPersonId: '',
                status: [],
                contactId: '',
                discount: '',
                unit: '',
                invoiceDate: '',
            }
        }
    },
    created() {
        this.refreshOpportunityList();
        this.refreshUserList();
        this.refreshContactList();
    },
    computed: {
        ...mapGetters('opportunities', ['opportunityList']),
        ...mapGetters('users', ['userList']),
        ...mapGetters('contacts', ['contactList']),
    },
    methods: {
        ...mapActions('opportunities', ['refreshOpportunityList', "deleteOpportunity"]),
        ...mapActions('users', ['refreshUserList']),
        ...mapActions('contacts', ['refreshContactList']),
    }
}
</script>
