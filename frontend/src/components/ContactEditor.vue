<template>
    <b-modal cancel-title="Kapat" ok-title="Kaydet" ok-variant="success" title="Kişi düzenleyici" visible
             @hidden="$emit('close')" @ok="onSaveClicked">
        <b-form>
            <b-form-group label="Adı">
                <b-form-input v-model="name"/>
            </b-form-group>
            <b-form-group label="E-Posta">
                <b-form-input v-model="email"/>
            </b-form-group>
            <b-form-group label="Title">
                <b-form-input v-model="title"/>
            </b-form-group>
            <b-form-group label="Cinsiyet">
                <b-form-input v-model="pronoun"/>
            </b-form-group>
            <b-form-group label="Pozisyon">
                <b-form-input v-model="position"/>
            </b-form-group>
            <b-form-group label="Departman">
                <b-form-input v-model="department"/>
            </b-form-group>
            <b-form-group label="Telefon Numarası">
                <b-form-input v-model="phone"/>
            </b-form-group>
            <b-form-group label="LinkedIn">
                <b-form-input v-model="linkedInUrl"/>
            </b-form-group>
            <b-form-group class="mt-3" label="Şirket">
                <b-form-select v-model="companyId" :options="companyList" text-field="name" value-field="id"/>
            </b-form-group>
        </b-form>
    </b-modal>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
    name: "ContactEditor",
    props: {
        value: Object,
    },
    data() {
        return {
            id: null,
            name: '',
            email: '',
            title: '',
            pronoun: '',
            position: '',
            department: '',
            phone: '',
            linkedInUrl: '',
            companyId: '',
        }
    },
    created() {
        this.id = this.value.id || -1;
        this.name = this.value.name || '';
        this.email = this.value.email || '';
        this.title = this.value.title || '';
        this.pronoun = this.value.pronoun || '';
        this.position = this.value.position || '';
        this.department = this.value.department || '';
        this.phone = this.value.phone || '';
        this.linkedInUrl = this.value.linkedInUrl || '';
        this.companyId = this.value.companyId || [];
        this.refreshCompanyList();
    },
    computed: {
        ...mapGetters("companies", ['companyList'])
    },
    methods: {
        ...mapActions("contacts", ["createContact", "updateContact"]),
        ...mapActions("companies", ["refreshCompanyList"]),
        onSaveClicked() {
            if (this.id === -1) {
                this.createContact({
                    name: this.name,
                    email: this.email,
                    title: this.title,
                    pronoun: this.pronoun,
                    position: this.position,
                    department: this.department,
                    phone: this.phone,
                    linkedInUrl: this.linkedInUrl,
                    companyId: this.companyId,
                }).then(() => this.$emit("close"));
            } else {
                this.updateContact({
                    id: this.id,
                    name: this.name,
                    email: this.email,
                    title: this.title,
                    pronoun: this.pronoun,
                    position: this.position,
                    department: this.department,
                    phone: this.phone,
                    linkedInUrl: this.linkedInUrl,
                    companyId: this.companyId,
                }).then(() => this.$emit("close"));
            }
        }
    }
}
</script>
