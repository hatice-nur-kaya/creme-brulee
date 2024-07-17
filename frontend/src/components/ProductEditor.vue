<template>
    <b-modal cancel-title="Kapat" ok-title="Kaydet" ok-variant="success" title="Ürün düzenleyici" visible
             @hidden="$emit('close')" @ok="onSaveClicked">
        <b-form>
            <b-form-group label="Ürün Adı">
                <b-form-input v-model="name"/>
            </b-form-group>
            <b-form-group label="Fiyat">
                <b-form-input v-model="price"/>
            </b-form-group>
            <b-form-group label="Kategori">
                <b-form-select v-model="categoryId" :options="productCategoryList" text-field="name" value-field="id"/>
            </b-form-group>
        </b-form>
    </b-modal>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
    name: "ProductEditor",
    props: {
        value: Object,
    },
    data() {
        return {
            id: null,
            name: '',
            price: '',
            categoryId: '',
        }
    },
    created() {
        this.id = this.value.id || -1;
        this.name = this.value.name || '';
        this.price = this.value.price || '';
        this.categoryId = this.value.categoryId || [];
        this.refreshProductCategoryList();
    },
    computed: {
        ...mapGetters("products", ['productCategoryList']),
    },
    methods: {
        ...mapActions("products", ["createProduct", "updateProduct", "refreshProductCategoryList"]),
        onSaveClicked() {
            if (this.id === -1) {
                this.createProduct({
                    name: this.name,
                    price: this.price,
                    categoryId: this.categoryId,
                }).then(() => this.$emit("close"));
            } else {
                this.updateProduct({
                    id: this.id,
                    name: this.name,
                    price: this.price,
                    categoryId: this.categoryId,
                }).then(() => this.$emit("close"));
            }
        },
    }
}
</script>
