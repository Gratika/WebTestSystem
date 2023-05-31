<script setup>
import {defineComponent} from "vue";
import CategoryItem from "@/components/menu/CategoryItem.vue";
import MyButton from "@/components/UI/MyButton.vue";
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
  <div class="my-3">
      <CategoryItem
              v-for="category in categoryDtoList"
              v-bind:key="category.id"
              :category="category"
              :subcategories="category.subcategoryDtoList"
              :isCanModify="isCanModify"
      />
    <div class="left" v-if="isCanModify">
      <MyButton>Додати</MyButton>
    </div>
  </div>
</template>
<style scoped>
 .left{
     display: flex;
     justify-content: flex-end;
     padding: 20px 0;
 }
</style>