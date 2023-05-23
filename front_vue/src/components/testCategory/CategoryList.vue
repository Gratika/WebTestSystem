<template>
  <div class="my-3 pe-3">
      <CategoryItem
              v-for="category in categories"
              v-bind:key="category.id"
              :category="category"
              :subcategories="subcategoryList.filter(subcat=>subcat.category_id===category.id)"
              :isCanModify="isCanModify"
      />
    <div>{{isCanModify}}</div>
    <div v-if="isCanModify">
      <button class="btn">Додати</button>
    </div>
  </div>
</template>
<script>
import {defineComponent} from "vue";
import CategoryItem from "@/components/testCategory/CategoryItem.vue";

export default defineComponent({
    components: {CategoryItem},
    props:{
        categories:[],
        subcategoryList:[],
        isCanModify:Boolean,
    },

})
</script>
<style scoped>
.btn{
  background: var(--background-2);
}
</style>