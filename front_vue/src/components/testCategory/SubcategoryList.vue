<template>
  <div class="ps-4" v-show="isShow">
      <SubcategoryItem
         v-for="subcategory in subcategories"
         v-bind:key="subcategory.id"
         :subcategory="subcategory"

      />
    <div v-if="isCanModify">
      <button class="btn">Додати</button>
    </div>
  </div>

</template>
<script>

import {defineComponent} from "vue";
import SubcategoryItem from "@/components/testCategory/SubcategoryItem.vue";

export default defineComponent({
    components: {SubcategoryItem},
    props:{
        subcategories:[],
        isShow:Boolean,
        isCanModify:Boolean,//користувач має право змінювати дані
    }
})
</script>
<style scoped>
.btn{
  background: var(--background-2);
}
</style>