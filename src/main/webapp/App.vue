<template>
  <div>
    <the-menu />
    <h2>CSV Upload</h2>
    <csv-upload />
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import * as firebase from 'firebase/app';
import 'firebase/auth';
import * as config from './firebase.config.js';

import CsvUpload from './components/upload/Csv.vue'
import TheMenu from './components/app/TheMenu.vue'

firebase.initializeApp(config);

export default Vue.extend({
    name: 'App',
    components: {
        CsvUpload, TheMenu 
    }
});
</script>

<style lang="scss">
@charset "utf-8";
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/base/_all.sass";
</style>
