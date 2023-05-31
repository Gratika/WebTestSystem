<template>
  <div class="ps-4" v-show="isShow">
      <SubcategoryItem
         v-for="subcategory in subcategories"
         v-bind:key="subcategory.id"
         :subcategory="subcategory"

      />
    <div v-if="isCanModify">
      <MyButton>+</MyButton>
    </div>
  </div>

</template>
<script>

import {defineComponent} from "vue";
import SubcategoryItem from "@/components/menu/SubcategoryItem.vue";
import MyButton from "@/components/UI/MyButton.vue";

export default defineComponent({
    components: {MyButton, SubcategoryItem},
    props:{
        subcategories:[],
        isShow:Boolean,
        isCanModify:Boolean,//користувач має право змінювати дані
    }
})
</script>
<style scoped>
</style>