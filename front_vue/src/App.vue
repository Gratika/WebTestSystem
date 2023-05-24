<script >
import TestNavbar from "@/components/TestNavbar.vue";

export default {
    components: {TestNavbar}
}
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
