import { createStore } from 'vuex'

export default createStore({
  state: {
    authenticated: false
  },
  getters: {
  },
  mutations: {
    change_auth: (state, auth) => state.authenticated = auth
  },
  actions: {
    setAuth: ({commit}, auth) => commit('change_auth', auth)
  },
  modules: {
  }
})
