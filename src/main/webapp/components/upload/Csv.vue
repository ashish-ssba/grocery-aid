<template>
  <form method="POST" action="/csv" enctype="multipart/form-data">
    <div class="field file has-name">
      <label class="file-label">
        <input class="file-input" type="file" name="file" v-on:change="updateFilename($event.target.files)"/>
        <span class="file-cta">
          <span class="file-label">
            Select a CSV file...
          </span>
        </span>
        <span class="file-name">{{ filename }}</span>
      </label>
    </div>
    <div class="field">
        <div class="control">
          <input type="submit" class="button is-primary" value="Parse" />
        </div>
    </div>
  </form>
</template>

<script lang="ts">
import Vue from 'vue';

export default Vue.extend({
    name: 'csvUpload',
    data: function() {
        return {
            filename: 'No file selected'
        }
    },
    methods: {
        updateFilename: function(files: FileList) {
            if (files.length > 0) {
                this.filename = files[0].name;
            } else {
                this.filename = "No file selected";
            }
        }
    }
});
</script>

<style lang="scss">
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/elements/button.sass";
@import "~bulma/sass/elements/form.sass";
</style>
