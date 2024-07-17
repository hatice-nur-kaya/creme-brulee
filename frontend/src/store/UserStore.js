import axios from "axios";

export default {
    namespaced: true,
    state: {
        users: [],
    },
    mutations: {
        setUsers(state, users) {
            state.users = users;
        },
        addUser(state, user) {
            state.users.push(user);
        },
        updateUser(state, user) {
            const userIndex = state.users.findIndex(u => u.id === user.id);
            if (userIndex > -1) {
                state.users.splice(userIndex, 1);
            }
            state.users.push(user);
        },
        deleteUser(state, user) {
            const idx = state.users.findIndex(u => u.id === user.id);
            if (idx > -1) {
                state.users.splice(idx, 1);
            }
        }
    },
    actions: {
        refreshUserList({commit}) {
            return axios.get("/api/users/list").then(usersResponse => {
                commit('setUsers', usersResponse.data);
                return usersResponse.data;
            });
        },
        createUser({commit}, user) {
            return axios.post("/api/users/create", user).then(userCreateResponse => {
                commit('addUser', userCreateResponse.data);
                return userCreateResponse.data;
            });
        },
        updateUser({commit}, user) {
            return axios.put("/api/users/update", user).then(userUpdateResponse => {
                commit('updateUser', userUpdateResponse.data);
                return userUpdateResponse.data;
            });
        },
        deleteUser({commit}, user) {
            return axios.delete("/api/users/" + user.id).then(() => {
                commit('deleteUser', user);
            });
        }
    },
    getters: {
        userList(state) {
            return state.users;
        }
    }
}
