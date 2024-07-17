import axios from "axios";

export default {
    namespaced: true,
    state: {
        companies: [],
    },
    mutations: {
        setCompanies(state, companies) {
            state.companies = companies;
        },
        addCompany(state, company) {
            state.companies.push(company);
        },
        updateCompany(state, company) {
            const companyIdx = state.companies.findIndex(u => u.id === company.id);
            if (companyIdx > -1) {
                state.companies.splice(companyIdx, 1);
            }
            state.companies.push(company);
        },
        deleteCompany(state, company) {
            const idx = state.companies.findIndex(u => u.id === company.id);
            if (idx > -1) {
                state.companies.splice(idx, 1);
            }
        }
    },
    actions: {
        refreshCompanyList({commit}) {
            return axios.get("/api/companies/list").then(companiesResponse => {
                commit('setCompanies', companiesResponse.data);
                return companiesResponse.data;
            });
        },
        createCompany({commit}, company) {
            return axios.post("/api/companies/create", company).then(companyCreateResponse => {
                commit('addCompany', companyCreateResponse.data);
                return companyCreateResponse.data;
            });
        },
        updateCompany({commit}, company) {
            return axios.put("/api/companies/update", company).then(companyUpdateResponse => {
                commit('updateCompany', companyUpdateResponse.data);
                return companyUpdateResponse.data;
            });
        },
        deleteCompany({commit}, company) {
            return axios.delete("/api/companies/" + company.id).then(() => {
                commit('deleteCompany', company);
            });
        }
    },
    getters: {
        companyList(state) {
            return state.companies;
        }
    }
}
