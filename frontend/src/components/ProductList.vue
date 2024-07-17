<template>
    <div>
        <h1>Ürünler</h1>
        <datatable-button create-button label="Yeni ürün oluştur" @click="Object.assign(editedProduct, {id: -1, name: '', price: '', categoryId: null})"/>
        <b-button variant="warning" @click="categoryEditorVisible = true">
            <b-icon-pencil/>
            Kategorileri yönet
        </b-button>
        <b-table :fields="fields" :items="productList" bordered class="mt-2" hover small striped>
            <template #cell(categoryId)="row">
                {{ (productCategoryList.find(c => c.id === row.item.categoryId) || {}).name }}
            </template>
            <template #cell(actions)="row">
                <datatable-button edit-button @click="Object.assign(editedProduct, productList.find(u => u.id === row.item.id))"/>
                <datatable-button delete-button @click="deleteProduct(productList.find(u => u.id === row.item.id))"/>
            </template>
        </b-table>
        <product-editor v-if="editedProduct.id" v-model="editedProduct" @close="editedProduct.id = null"/>
        <product-category-editor v-if="categoryEditorVisible" @close="categoryEditorVisible = false"/>
    </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import ProductEditor from "@/components/ProductEditor.vue";
import ProductCategoryEditor from "@/components/ProductCategoryEditor.vue";
import DatatableButton from "@/components/DatatableButton.vue";

export default {
    name: "ProductList",
    components: {DatatableButton, ProductEditor, ProductCategoryEditor},
    data() {
        return {
            fields: [
                {key: 'id', label: 'No'},
                {key: 'name', label: 'Ad'},
                {key: 'price', label: 'Fiyat'},
                {key: 'categoryId', label: 'Kategori'},
                {key: 'actions', label: 'Eylemler', tdClass: 'text-right', thClass: 'text-right'}
            ],
            editedProduct: {
                id: null,
                name: '',
                price: '',
                categoryId: ''
            },
            categoryEditorVisible: false
        }
    },
    created() {
        this.refreshProductList();
        this.refreshProductCategoryList();
    },
    computed: {
        ...mapGetters('products', ['productList', 'productCategoryList'])
    },
    methods: {
        ...mapActions('products', ['refreshProductList', 'refreshProductCategoryList', 'deleteProduct'])
    }
}
</script>
