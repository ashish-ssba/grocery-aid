import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        user
    },
    state: {
        errorMessage: ''
    },
    mutations: {
        updateError: function(state, error) {
            if (error) {
                state.errorMessage = error.message
            } else {
                state.errorMessage = ''
            }
        }
    },
    actions: {
        clearError: function({ commit }) {
            commit('updateError')
        }
    }
})