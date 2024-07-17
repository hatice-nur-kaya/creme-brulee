import axios from "axios";

export default {
    namespaced: true,
    state: {
        products: [],
        categories: []
    },
    mutations: {
        setProductCategories(state, productCategories) {
            state.categories = productCategories;
        },
        setProducts(state, products) {
            state.products = products;
        },
        addProduct(state, product) {
            state.products.push(product);
        },
        addProductCategory(state, productCategory) {
            state.categories.push(productCategory);
        },
        updateProduct(state, product) {
            const pIdx = state.products.findIndex(u => u.id === product.id);
            if (pIdx > -1) {
                state.products.splice(pIdx, 1);
            }
            state.products.push(product);
        },
        updateProductCategory(state, productCategory) {
            const pcIdx = state.categories.findIndex(u => u.id === productCategory.id);
            if (pcIdx > -1) {
                state.categories.splice(pcIdx, 1);
            }
            state.categories.push(productCategory);
        },
        deleteProduct(state, product) {
            const idx = state.products.findIndex(u => u.id === product.id);
            if (idx > -1) {
                state.products.splice(idx, 1);
            }
        },
        deleteProductCategory(state, categories) {
            const idx = state.categories.findIndex(u => u.id === categories.id);
            if (idx > -1) {
                state.categories.splice(idx, 1);
            }
        }
    },
    actions: {
        refreshProductList({commit}) {
            return axios.get("/api/products/list").then(productsResponse => {
                commit('setProducts', productsResponse.data);
                return productsResponse.data;
            });
        },
        refreshProductCategoryList({commit}) {
            return axios.get("/api/products/listCategories").then(categoriesResponse => {
                commit('setProductCategories', categoriesResponse.data);
                return categoriesResponse.data;
            });
        },
        createProduct({commit}, product) {
            return axios.post("/api/products/create", product).then(productCreateResponse => {
                commit('addProduct', productCreateResponse.data);
                return productCreateResponse.data;
            });
        },
        createProductCategory({commit}, productCategory) {
            return axios.post("/api/products/createCategory", productCategory).then(categoryCreateResponse => {
                commit('addProductCategory', categoryCreateResponse.data);
                return categoryCreateResponse.data;
            });
        },
        updateProduct({commit}, product) {
            return axios.put("/api/products/update", product).then(productUpdateResponse => {
                commit('updateProduct', productUpdateResponse.data);
                return productUpdateResponse.data;
            });
        },
        updateProductCategory({commit}, productCategory) {
            return axios.put("/api/products/updateCategory", productCategory).then(categoryUpdateResponse => {
                commit('updateProductCategory', categoryUpdateResponse.data);
                return categoryUpdateResponse.data;
            });
        },
        deleteProduct({commit}, product) {
            return axios.delete("/api/products/" + product.id).then(() => {
                commit('deleteProduct', product);
            });
        },
        deleteProductCategory({commit}, categories) {
            return axios.delete("/api/products/" + categories.id).then(() => {
                commit('deleteProductCategory', categories);
            })
        }
    },
    getters: {
        productList(state) {
            return state.products;
        },
        productCategoryList(state) {
            return state.categories;
        }
    }
}
