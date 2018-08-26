<template>
  <div>
    <div>
      <Auth />
    </div>
    <h2>CSV Upload</h2>
    <CsvUpload />
  </div>
</template>

<script>
import firebase from 'firebase/app';
import auth from 'firebase/auth';
import config from './firebase.config.js';

import CsvUpload from './components/upload/Csv.vue'
import Auth from './components/auth/AuthBox.vue'

firebase.initializeApp(config);

export default {
    name: 'App',
    components: {
        CsvUpload, Auth 
    }
}
</script>

<style lang="scss">
@charset "utf-8";
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/base/_all.sass";
</style>
