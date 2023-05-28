<script setup>
import {defineComponent} from "vue";
import CategoryItem from "@/components/testCategory/CategoryItem.vue";
const props = defineProps({
  categoryDtoList:{
    type:Object,
    required:true,
    default:()=>{}
  },
  isCanModify:Boolean
})
/*export default defineComponent({
    components: {CategoryItem},
    props:{
        categories:[],
        subcategoryList:[],
        isCanModify:Boolean,
    },

})*/
</script>
<template>
  <div class="my-3 pe-3">
      <CategoryItem
              v-for="category in categoryDtoList"
              v-bind:key="category.id"
              :category="category"
              :subcategories="category.subcategoryDtoList"
              :isCanModify="isCanModify"
      />
    <div v-if="isCanModify">
      <button class="btn">Додати</button>
    </div>
  </div>
</template>
<style scoped>
.btn{
  background: var(--background-2);
}
</style>