<template>
  <div>
      <CategoryItem
              v-for="category in categories"
              v-bind:key="category.id"
              :category="category"
              :subcategories="subcategoryList.filter(subcat=>subcat.category_id=category.id)"
      />
  </div>
</template>
<script>
import {defineComponent} from "vue";
import CategoryItem from "@/components/testCategory/CategoryItem.vue";

export default defineComponent({
    components: {CategoryItem},
    props:{
        categories:[],
        subcategoryList:[]
    },
    data(){
        return{
            subcategories:[],
        }
    }
})
</script>
<style></style>