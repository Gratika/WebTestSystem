<script setup lang="ts">
import TestList from "@/components/TestList";
export default {
  components: {TestList},
  data() {
    return {
      tests:[
        {id:1, title:'Javascript', body: 'опис посту'},
        {id:2, title:'Java', body: 'опис посту'},
        {id:3, title:'Vue', body: 'опис посту'}
      ]
    }
  },
}
</script>
<template>
 <div>
  <TestList :tests="tests"/>
 </div>
</template>

<style>

</style>