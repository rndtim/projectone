import AuthService from '../services/AuthService';

// const user = JSON.parse(localStorage.getItem('user'));
const initialState = {status: {loggedIn: false}, user: null};
export const auth = {
    namespaced: true,
    state: initialState,

    actions: {
        login({commit}, credentials) {
            return AuthService.login(credentials)
                .then(user => {
                        // console.log("From auth.module " + JSON.stringify(user));
                        commit('loginSuccess', user);
                        // return Promise.resolve(user);
                    },
                    error => {
                        commit('loginFailure');
                        return Promise.reject(error);
                    }
                );
        },
        logout({commit}) {
            AuthService.logout();
            commit('logout');
        },
        registration({commit}, user) {
            return AuthService.registration(user)
                .then(() => {
                        commit('registerSuccess');
                        // return Promise.resolve(response.data);
                    },
                    error => {
                        commit('registerFailure');
                        return Promise.reject(error);
                    }
                );
        }
    },

    mutations: {
        loginSuccess(state, user) {
            state.status.loggedIn = true;
            state.user = user;
        },
        loginFailure(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        logout(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        registerSuccess(state) {
            state.status.loggedIn = false;
        },
        registerFailure(state) {
            state.status.loggedIn = false;
        }
    }
};