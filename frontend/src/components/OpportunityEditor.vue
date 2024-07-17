<template>
    <b-modal cancel-title="Kapat" ok-title="Kaydet" ok-variant="success" title="Opportunity" visible
             @hidden="$emit('close')" @ok="onSaveClicked">
        <b-form>
            <b-form-group label="Satış sorumlusu">
                <b-form-select v-model="salesPersonId" :options="userList" text-field="name" value-field="id"/>
            </b-form-group>
            <b-form-group label="Şirket adı">
                <b-form-select v-model="companyId" :options="companyList" text-field="name" value-field="id"/>
            </b-form-group>
            <b-form-group label="İletişim">
                <b-form-select v-model="contactId" :options="companyId ? contactList.filter(c => c.companyId === companyId) : []" text-field="email" value-field="id"/>
            </b-form-group>
            <b-form-group label="Adet">
                <b-form-input v-model="unit"/>
            </b-form-group>
            <b-form-group label="İndirim %">
                <b-form-input v-model="discount"/>
            </b-form-group>
            <b-form-group label="Fatura tarihi">
                <b-form-datepicker v-model="invoiceDate"/>
            </b-form-group>
            <b-form-group label="Durum">
                <b-form-select v-model="status" :options="availableStatus" text-field="label" value-field="value"/>
            </b-form-group>
        </b-form>
    </b-modal>
</template>

<script>
import {mapActions, mapGetters} from "vuex";


export default {
    name: "OpportunityEditor",
    props: {
        value: Object,
    },
    data() {
        return {
            id: null,
            salesPersonId: '',
            companyId: null,
            contactId: null,
            unit: '',
            discount: '',
            invoiceDate: '',
            status: [],
            availableStatus: [
                {label: "Satıldı", value: "SOLD"},
                {label: "Görüşme Devam Ediyor", value: "CONTINUES"},
                {label: "Olumsuz", value: "NOTSOLD"},
            ]
        }
    },
    created() {
        this.id = this.value.id || -1;
        this.salesPersonId = this.value.salesPersonId || [];
        this.companyId = (this.contactList.find(c => c.id === this.value.contactId) || {}).companyId;
        this.contactId = this.value.contactId || [];
        this.unit = this.value.unit || '';
        this.discount = this.value.discount || '';
        this.status = this.value.status || [];
        this.invoiceDate = this.value.invoiceDate || new Date();
        this.refreshUserList();
        this.refreshContactList();
        this.refreshCompanyList();
    },
    computed: {
        ...mapGetters("users", ["userList"]),
        ...mapGetters("contacts", ["contactList"]),
        ...mapGetters("companies", ["companyList"]),
    },
    methods: {
        ...mapActions("opportunities", ["createOpportunity", "updateOpportunity"]),
        ...mapActions("users", ["refreshUserList"]),
        ...mapActions("contacts", ["refreshContactList"]),
        ...mapActions("companies", ["refreshCompanyList"]),
        onSaveClicked() {
            if (this.id === -1) {
                this.createOpportunity({
                    salesPersonId: this.salesPersonId,
                    contactId: this.contactId,
                    unit: this.unit,
                    discount: this.discount,
                    invoiceDate: this.invoiceDate,
                    status: this.status,
                }).then(() => this.$emit("close"));
            } else {
                this.updateOpportunity({
                    id: this.id,
                    salesPersonId: this.salesPersonId,
                    contactId: this.contactId,
                    unit: this.unit,
                    discount: this.discount,
                    invoiceDate: this.invoiceDate,
                    status: this.status,
                }).then(() => this.$emit("close"));
            }
        }
    }
}
</script>
