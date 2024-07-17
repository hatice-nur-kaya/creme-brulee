import Vue from 'vue';
import Vuex from 'vuex';
import App from './App.vue';
import {BootstrapVue, IconsPlugin} from "bootstrap-vue";
import VueRouter from 'vue-router';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

import AuthenticationStore from "@/store/AuthenticationStore";
import UserStore from "@/store/UserStore";
import DashBoard from "@/components/Dashboard.vue";
import OpportunityStore from "@/store/OpportunityStore";
import ContactStore from "@/store/ContactStore";
import ProductStore from "@/store/ProductStore";
import CompanyStore from "@/store/CompanyStore";
import CompanyList from "@/components/CompanyList.vue";
import ContactList from "@/components/ContactList.vue";
import OpportunityList from "@/components/OpportunityList.vue";
import ProductList from "@/components/ProductList.vue";
import UserList from "@/components/UserList.vue";

Vue.config.productionTip = false

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(Vuex);
Vue.use(VueRouter)

new Vue({
    router: new VueRouter({
        routes: [
            {path: '/Dashboard', name: 'Dashboard', component: DashBoard},
            {path: '/Companies', name: 'Companies', component: CompanyList},
            {path: '/Contacts', name: 'Contacts', component: ContactList},
            {path: '/Opportunities', name: 'Opportunties', component: OpportunityList},
            {path: '/Products', name: 'Products', component: ProductList},
            {path: '/Users', name: 'Users', component: UserList},
        ],
        mode: "history"
    }),
    render: h => h(App),
    store: new Vuex.Store({
        modules: {
            authentication: AuthenticationStore,
            companies: CompanyStore,
            contacts: ContactStore,
            opportunities: OpportunityStore,
            products: ProductStore,
            users: UserStore,
        }
    })
}).$mount('#app')
