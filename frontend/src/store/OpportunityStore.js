import axios from "axios";

export default {
    namespaced: true,
    state: {
        opportunities: [],
    },
    mutations: {
        setOpportunities(state, opportunities) {
            state.opportunities = opportunities;
        },
        addOpportunity(state, opportunity) {
            state.opportunities.push(opportunity);
        },
        updateOpportunity(state, opportunity) {
            const oppIdx = state.opportunities.findIndex(u => u.id === opportunity.id);
            if (oppIdx > -1) {
                state.opportunities.splice(oppIdx, 1);
            }
            state.opportunities.push(opportunity);
        },
        deleteOpportunity(state, opportunity) {
            const idx = state.opportunities.findIndex(u => u.id === opportunity.id);
            if (idx > -1) {
                state.opportunities.splice(idx, 1);
            }
        }
    },
    actions: {
        refreshOpportunityList({commit}) {
            return axios.get("api/opportunities/list").then(opportunitiesResponse => {
                commit('setOpportunities', opportunitiesResponse.data);
                return opportunitiesResponse.data;
            });
        },
        createOpportunity({commit}, opportunity) {
            return axios.post("/api/opportunities/create", opportunity).then(opportunityCreateResponse => {
                commit('addOpportunity', opportunityCreateResponse.data);
                return opportunityCreateResponse.data;
            });
        },
        updateOpportunity({commit}, opportunity) {
            return axios.put("/api/opportunities/update", opportunity).then(opportunityUpdateResponse => {
                commit('updateOpportunity', opportunityUpdateResponse.data);
                return opportunityUpdateResponse.data;
            });
        },
        deleteOpportunity({commit}, opportunity) {
            return axios.delete("/api/opportunities/" + opportunity.id).then(() => {
                commit('deleteOpportunity', opportunity);
            });
        }
    },
    getters: {
        opportunityList(state) {
            return state.opportunities;
        },
    }
}
