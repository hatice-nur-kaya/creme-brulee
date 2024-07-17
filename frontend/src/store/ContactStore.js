import axios from "axios";

export default {
    namespaced: true,
    state: {
        contacts: [],
    },
    mutations: {
        setContacts(state, contacts) {
            state.contacts = contacts;
        },
        addContact(state, contact) {
            state.contacts.push(contact);
        },
        updateContact(state, contact) {
            const contactIdx = state.contacts.findIndex(u => u.id === contact.id);
            if (contactIdx > -1) {
                state.contacts.splice(contactIdx, 1);
            }
            state.contacts.push(contact);
        },
        deleteContact(state, contact) {
            const idx = state.contacts.findIndex(u => u.id === contact.id);
            if (idx > -1) {
                state.contacts.splice(idx, 1);
            }
        }
    },
    actions: {
        refreshContactList({commit}) {
            return axios.get("/api/contacts/list").then(contactsResponse => {
                commit('setContacts', contactsResponse.data);
                return contactsResponse.data;
            });
        },
        createContact({commit}, contact) {
            return axios.post("/api/contacts/create", contact).then(contactCreateResponse => {
                commit('addContact', contactCreateResponse.data);
                return contactCreateResponse.data;
            });
        },
        updateContact({commit}, contact) {
            return axios.put("/api/contacts/update", contact).then(contactUpdateResponse => {
                commit('updateContact', contactUpdateResponse.data);
                return contactUpdateResponse.data;
            });
        },
        deleteContact({commit}, contact) {
            return axios.delete("/api/contacts/" + contact.id).then(() => {
                commit('deleteContact', contact);
            });
        }
    },
    getters: {
        contactList(state) {
            return state.contacts;
        },
    }
}
