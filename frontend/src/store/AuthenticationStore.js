import axios from "axios";

export default {
    namespaced: true,
    state: {
        authFailed: false,
        currentUser: {
            id: null,
            name: '',
            email: '',
            permissions: []
        }
    },
    mutations: {
        setCurrentUser(state, user) {
            Object.assign(state.currentUser, user);
        },
        setAuthFailed(state, status) {
            state.authFailed = !!status;
        },
        clear(state) {
            Object.assign(state.currentUser, {id: null, name: '', email: '', permissions: []});
            state.authFailed = false;
        }
    },
    actions: {
        refreshAuthentication({commit}) {
            axios.get("/api/auth/me")
                .then(result => commit(result.status !== 200 || !result.data ? 'clear' : 'setCurrentUser', result.data));
        },
        login({commit}, {username, password}) {
            const params = new URLSearchParams();
            params.append("username", username);
            params.append("password", password);
            return axios.post("/login", params).then(result => {
                if (result.status !== 200 || !result.request.responseURL.endsWith('/home')) {
                    commit('setAuthFailed', true);
                    commit('clear');
                    return false;
                } else {
                    return axios.get("/api/auth/me").then(currentUser => {
                        commit('setCurrentUser', currentUser.data);
                        commit('setAuthFailed', false);
                        return currentUser.data;
                    });
                }
            });
        },
        logout({commit}) {
            return axios.get("/logout").then(() => commit('clear'));
        }
    },
    getters: {
        authFailed(state) {
            return state.authFailed;
        },
        loggedIn(state) {
            return !!state.currentUser.id;
        },
        currentUser(state) {
            return state.currentUser;
        },
        currentUserPermissions(state) {
            return state.currentUser.permissions;
        }
    }
}
