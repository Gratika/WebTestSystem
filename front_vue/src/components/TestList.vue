<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <CardTest
            v-for="test in tests"
            :test="test"/>
      </div>
    </div>
  </div>
</template>
<script>
import CardTest from "./CardTest";
export default {
  components: {CardTest},
  props:{
    tests:{
      type: Array,
    }
  }
}
</script>
<style></style>