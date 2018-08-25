<template>
  <div>
    <h2>CSV Upload</h2>
    <CsvUpload />
  </div>
</template>

<script>
import CsvUpload from './components/upload/Csv.vue'

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
