<template>
<form>
  <div class="field">
    <label class="label">Email</label>
    <div class="control">
      <input class="input" v-model="email" type="email" placeholder="Email" />
    </div>
  </div>
  <div class="field">
    <label class="label">Password</label>
    <div class="control">
      <input class="input" v-model="password" type="password" placeholder="Password" />
    </div>
  </div>
  <div class="field is-grouped">
    <div class="control">
      <input type="button" class="button is-primary" v-on:click="signUp" value="Sign Up">
    </div>
    <div class="control">
      <input type="button" class="button is-text" v-on:click="cancel" value="Cancel">
    </div>
  </div>
  <error-message 
    v-if="errorMessage.length > 0" 
    header="Error Creating Account" 
    v-bind:message="errorMessage" 
    v-on:close="errorMessage = ''"/>
</form>
</template>

<script lang="ts">
import Vue from 'vue';
import * as firebase from 'firebase/app';

import ErrorMessage from '../base/BaseErrorMessage.vue'

export default Vue.extend({
    name: 'signup',
    data: function() {
        return {
            email: '',
            password: '',
            errorMessage: ''
        }
    },
    methods: {
        signUp: function() {
            var self = this
            firebase.auth().createUserWithEmailAndPassword(this.email, this.password).then(
              (credential) => {
                let user = credential.user ? credential.user.displayName || credential.user.email : "Unknown"
                this.$emit('login', user)
              }
            ).catch(
              (error) => {
                this.errorMessage = error.message
              }
            )
        },
        cancel: function() {
            this.email = '';
            this.password = '';
            this.$emit('cancel')
        }
    },
    components: {
      ErrorMessage
    }
});
</script>

<style lang="scss">
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/elements/button.sass";
@import "~bulma/sass/elements/form.sass";
</style>
