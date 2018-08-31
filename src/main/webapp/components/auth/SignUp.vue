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
  <article class="message is-danger" v-if="errorMessage.length > 0">
    <div class="message-header">
      <p>Error Creating Account</p>
      <button class="delete" aria-label="delete" v-on:click="dismissError"></button>
    </div>
    <div class="message-body">
      {{ errorMessage }}
    </div>
  </article>
</form>
</template>

<script lang="ts">
import Vue from 'vue';
import * as firebase from 'firebase/app';

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
            firebase.auth().createUserWithEmailAndPassword(this.email, this.password).catch(function(error: firebase.FirebaseError) {
                self.errorMessage = error.message
            });
        },
        cancel: function() {
            this.email = '';
            this.password = '';
        },
        dismissError: function() {
            this.errorMessage = ''
        }
    }
});
</script>

<style lang="scss">
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/elements/button.sass";
@import "~bulma/sass/elements/form.sass";
@import "~bulma/sass/components/message.sass";
@import "~bulma/sass/elements/other.sass";
</style>
