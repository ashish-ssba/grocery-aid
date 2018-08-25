<template>
  <form method="POST" action="/csv" enctype="multipart/form-data">
    <label path="file">Select a CSV file to upload</label>
    <br />
    <input type="file" name="file" />
    <br />
    <input type="submit" value="Parse" />
  </form>
</template>

<script>
export default {
}
</script>
