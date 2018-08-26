<template>
<form>
  <div class="field">
    <label class="label">Email</label>
    <div class="control">
      <input class="input" type="email" placeholder="Email" />
    </div>
  </div>
  <div class="field">
    <label class="label">Password</label>
    <div class="control">
      <input class="input" type="password" placeholder="Password" />
    </div>
  </div>
  <div class="field is-grouped">
    <div class="control">
      <button class="button is-link">Login</button>
    </div>
    <div class="control">
      <button class="button is-text">Cancel</button>
    </div>
  </div>
</form>
</template>

<script>
import firebase from 'firebase/app'

export default {
    name: 'login',
    data: function() {
        return {
            email: '',
            password: ''
        }
    },
    methods: {
        login: function() {
            firebase.auth().signInWithEmailAndPassword(this.email, this.password).catch(function(error) {
                alert("Opps. " + error.message);
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
