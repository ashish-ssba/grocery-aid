<template>
  <div>
    <h2>CSV Upload</h2>
    <CsvUpload />
  </div>
</template>

<script>
import CsvUpload from './components/upload/Csv.vue'
import firebase from 'firebase/app';
import auth from 'firebase/auth';
import config from './firebase.config.js';

firebase.initializeApp(config);
console.log("Firebase initialized with: " + JSON.stringify(config));

export default {
    name: 'App',
    components: {
        CsvUpload
    }
}
</script>

<style scoped>
p {
    font-size: 2em;
    text-align: center;
}
</style>
