<template>
  <article class="message is-primary">
    <div class="message-header">
      <p>{{header}}</p>
      <a class="delete" aria-label="delete" v-on:click="$emit('close')" />
    </div>
    <div class="message-body">
     {{ message }}
    </div>
  </article>
</template>

<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
    name: 'BaseMessage',
    props: {
        header: String,
        message: String
    }
})
</script>

<style lang="scss">
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/components/message.sass";
@import "~bulma/sass/elements/other.sass";
</style>