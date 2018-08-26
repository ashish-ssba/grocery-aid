<template>
  <div class="panel">
    <div class="panel-tabs">
      <a v-bind:class="{'is-active': isLogin}" v-on:click="toggleIsLogin(true)">Login</a>
      <a v-bind:class="{'is-active': !isLogin}" v-on:click="toggleIsLogin(false)">Sign Up</a>
    </div>
    <div class="panel-block">
      <Login v-if="isLogin"/>
      <SignUp v-if="!isLogin"/>
    </div>
  </div>
</template>

<script>
import SignUp from './SignUp.vue'
import Login from './Login.vue'

export default {
    name: 'authBox',
    data: function() {
        return {
            isLogin: true
        }
    },
    methods: {
        toggleIsLogin: function(newValue) {
            this.isLogin = newValue
        }
    },
    components: {
        SignUp, Login
    }
}
</script>

<style lang="scss">
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/components/panel.sass";
</style>
