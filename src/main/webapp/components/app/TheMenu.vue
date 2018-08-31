<template>
    <nav class="navbar is-fixed-top" role="navigation" aria-label="main navigation">
        <div class="navbar-brand">
            <a role="button" class="navbar-item">Grocery Aid</a>
            <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" v-on:click="isMenuActive = !isMenuActive" v-bind:class="{'is-active': isMenuActive}">
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
            </a>
        </div>

        <div class="navbar-menu" v-bind:class="{'is-active': isMenuActive}">
            <div class="navbar-start">
            </div>
            <div class="navbar-end">
                <auth-toggle />
            </div>
        </div>
    </nav>
</template>

<script lang="ts">
import Vue from 'vue'
import AuthToggle from '../auth/AuthToggle.vue'

export default Vue.extend({
    name: 'TheMenu',
    data: function() {
        return {
            isMenuActive: false
        }
    },
    components: {
        AuthToggle
    }
})
</script>

<style lang="scss">
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/components/navbar.sass";
@import "~bulma/sass/elements/button.sass";
</style>
