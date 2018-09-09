import * as firebase from 'firebase/app'

let user = null

const state = {
    name: ''
}

const getters = {
    getToken: function() {
        return user === null ? null : user.getIdToken()
    }
}

const actions = {
    login: function({ commit }, { email, password }) {
        firebase.auth().signInWithEmailAndPassword(email, password).then(
            (credential) => {
                commit('updateUser', credential.user)
            }
        ).catch(
            (err) => {
                let error = {
                    message: err.message,
                    original: err
                }
                commit('updateError', error, { root: true })
            }
        )
    },

    signUp: function({ commit }, { email, password }) {
        firebase.auth().createUserWithEmailAndPassword(email, password).then(
            (credential) => {
                commit('updateUser', credential.user)
            }
        ).catch(
            (err) => {
                let error = {
                    message: err.message,
                    original: err
                }
                commit('updateError', error, { root: true })
            }
        )
    },

    logout: function({ commit }) {
        firebase.auth().signOut().then(
            () => {
                commit('updateUser', null)
            }
        )
    }
}

const mutations = {
    updateUser: function(state, newUser) {
        if (newUser === null) {
            state.name = ''
            user = null
        } else {
            state.name = newUser.displayName || newUser.email
            user = newUser
        }
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}