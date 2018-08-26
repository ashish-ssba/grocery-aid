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
      <button class="button is-link" v-on:click="signUp">Sign Up</button>
    </div>
    <div class="control">
      <button class="button is-text" v-on:click="cancel">Cancel</button>
    </div>
  </div>
</form>
</template>

<script>
import firebase from 'firebase/app';

export default {
    name: 'signup',
    data: function() {
        return {
            email: '',
            password: ''
        }
    },
    methods: {
        signUp: function() {
            firebase.auth().createUserWithEmailAndPassword(this.email, this.password).catch(function(error) {
                alert("Oops. " + error.message);
                if (error.code === 'auth/invalid-email') {
                } else {
                }
            });
        },
        cancel: function() {
            this.email = '';
            this.password = '';
        }
    }
}
</script>

<style>
</style>
