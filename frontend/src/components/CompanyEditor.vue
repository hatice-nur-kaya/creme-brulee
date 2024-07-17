<template>
    <b-modal cancel-title="Kapat" ok-title="Kaydet" ok-variant="success" title="Şirket düzenle" visible
             @hidden="$emit('close')" @ok="onSaveClicked">
        <b-form>
            <b-form-group label="Şirket adı">
                <b-form-input v-model="name"/>
            </b-form-group>
            <b-form-group label="Web">
                <b-form-input v-model="website"/>
            </b-form-group>
            <b-form-group label="Adres">
                <b-form-input v-model="address"/>
            </b-form-group>
            <b-form-group label="Telefon">
                <b-form-input v-model="phoneNumber"/>
            </b-form-group>
        </b-form>
    </b-modal>
</template>

<script>
import {mapActions} from "vuex";

export default {
    name: "CompanyEditor",
    props: {
        value: Object,
    },
    data() {
        return {
            id: null,
            name: '',
            address: '',
            website: '',
            phoneNumber: '',
        }
    },
    created() {
        this.id = this.value.id || -1;
        this.name = this.value.name || '';
        this.address = this.value.address || '';
        this.website = this.value.website || '';
        this.phoneNumber = this.value.phoneNumber || '';
    },
    methods: {
        ...mapActions("companies", ["createCompany", "updateCompany"]),
        onSaveClicked() {
            if (this.id === -1) {
                this.createCompany({
                    name: this.name,
                    address: this.address,
                    website: this.website,
                    phoneNumber: this.phoneNumber,
                }).then(() => this.$emit("close"));
            } else {
                this.updateCompany({
                    id: this.id,
                    name: this.name,
                    address: this.address,
                    website: this.website,
                    phoneNumber: this.phoneNumber
                }).then(() => this.$emit("close"));
            }
        }
    }
}
</script>
