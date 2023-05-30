<script  setup>
import TestNavbar from "@/components/TestNavbar.vue";


</script>

<template>
  <div class="my_header">
      <TestNavbar/>
  </div>
  <div class="main_content ">
      <router-view></router-view>
  </div>
  <div class="my_footer"></div>

</template>

<style scoped>

</style>
