<template>
    <div class="navbar-item" v-bind:class="itemClasses">
        <a v-if="!isAuthenticated" class="button is-text" aria-haspopup="true" aria-controls="dropdown-menu" v-on:click="isDropdownActive = !isDropdownActive">
            Sign Up/Login
        </a>
        <div v-if="!isAuthenticated" class="navbar-dropdown is-right">
            <div class="navbar-item">
                <div class="panel">
                    <div class="panel-tabs">
                        <a v-bind:class="{'is-active': isLogin}" v-on:click="isLogin = true">Login</a>
                        <a v-bind:class="{'is-active': !isLogin}" v-on:click="isLogin = false">Sign Up</a>
                    </div>
                    <div class="panel-block">
                        <login v-if="isLogin" v-on:login="login" v-on:cancel="isDropdownActive = false"/>
                        <sign-up v-if="!isLogin" v-on:login="login" v-on:cancel="isDropdownActive = false"/>
                    </div>
                </div>
            </div>
        </div>
        <a v-if="isAuthenticated" class="button is-text" v-on:click="logout">Logout ({{username}})</a>
    </div>
</template>

<script lang="ts">
import Vue from 'vue'
import SignUp from './SignUp.vue'
import Login from './Login.vue'

export default Vue.extend({
    name: 'AuthToggle',
    data: function() {
        return {
            isDropdownActive: false,
            isLogin: true
        }
    },
    computed: {
        itemClasses: function() {
            return {
                'has-dropdown': !this.isAuthenticated,
                'is-active': !this.isAuthenticated && this.isDropdownActive
            }
        },
        username: function() {
            return this.$store.state.user.name
        },
        isAuthenticated: function() {
            return this.$store.state.user.name && this.$store.state.user.name.length > 0
        }
    },
    methods: {
        login: function(username: string) {
            this.isDropdownActive = false
            this.isAuthenticated = true
            this.username = username
        },
        logout: function() {
            this.$store.dispatch('user/logout')
            this.isDropdownActive = false
        }
    },
    components: {
        SignUp, Login
    }
})
</script>

<style lang="scss" scoped>
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/elements/button.sass";
@import "~bulma/sass/components/dropdown.sass";
@import "~bulma/sass/components/panel.sass";

.dropdown-content {
    padding-top: 0rem;
    padding-bottom: 0rem;
}
</style>
