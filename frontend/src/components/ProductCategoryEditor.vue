<template>
    <div>
        <b-modal cancel-title="Kapat" ok-title="Kaydet" ok-variant="success" scrollable size="lg"
                 title="Kategori düzenleyici" visible @hidden="$emit('close')">
            <datatable-button create-button label="Kategori ekle" @click="editedCategory.id = -1"/>
            <b-table :fields="fields" :items="productCategoryList" bordered class="mt-3" hover small striped>
                <template #cell(actions)="row">
                    <datatable-button edit-button @click="Object.assign(editedCategory, productCategoryList.find(u => u.id === row.item.id))"/>
                    <datatable-button delete-button @click="deleteProductCategory(productCategoryList.find(u => u.id === row.item.id))"/>
                </template>
            </b-table>
        </b-modal>
        <b-modal v-if="editedCategory.id" cancel-title="Kapat" ok-title="Kaydet" ok-variant="success"
                 visible @hidden="editedCategory.id = null" @ok="onSaveClicked">
            <b-form>
                <b-form-group label="Kategori adı">
                    <b-form-input v-model="editedCategory.name"/>
                </b-form-group>
            </b-form>
        </b-modal>
    </div>
</template>
<script>
import {mapActions, mapGetters} from "vuex";
import DatatableButton from "@/components/DatatableButton.vue";

export default {
    name: "ProductCategoryEditor",
    components: {DatatableButton},
    props: {
        value: Object,
    },
    data() {
        return {
            fields: [
                {key: 'id', label: 'No'},
                {key: 'name', label: 'Kategori Adı'},
                {key: 'actions', label: 'Eylemler', tdClass: 'text-right', thClass: 'text-right'}
            ],
            editedCategory: {
                id: null,
                name: ''
            }
        }
    },
    created() {
        this.refreshProductCategoryList();
    },
    computed: {
        ...mapGetters("products", ['productCategoryList'])
    },
    methods: {
        ...mapActions("products", ["createProductCategory", "updateProductCategory", 'refreshProductCategoryList', 'deleteProductCategory']),
        onSaveClicked() {
            if (this.editedCategory.id === -1) {
                this.createProductCategory({
                    name: this.editedCategory.name,
                }).then(() => this.editedCategory.id = null);
            } else {
                this.updateProductCategory({
                    id: this.editedCategory.id,
                    name: this.editedCategory.name,
                }).then(() => this.editedCategory.id = null);
            }
        },
    }
}
</script>
