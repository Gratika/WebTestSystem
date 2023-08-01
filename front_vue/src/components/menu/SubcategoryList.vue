<script setup lang='ts'>

import SubcategoryItem from "@/components/menu/SubcategoryItem.vue";
import MyButton from "@/components/UI/MyButton.vue";
import { defineProps } from 'vue';
import type { ISubCategoryDto } from '@/api/type'


const props = defineProps({
    categoryId:Number,
    subcategories: Array<ISubCategoryDto>,
    isShow: Boolean,
    isCanModify: Boolean,//користувач має право змінювати дані
    showAddForm:Boolean//показати форму для створення нової підкатегорії
  });
 const emits = defineEmits(['change']);
 function changeShowAddForm(){
   emits('change',props.categoryId)
 }

</script>
<template>
  <div class="ps-4" v-show="props.isShow">
    <SubcategoryItem
      v-for="subcategory in props.subcategories"
      v-bind:key="subcategory.id"
      :subcategory="subcategory"
    />
    <div v-if="props.isCanModify">
      <MyButton @click='changeShowAddForm'>+</MyButton>
    </div>
  </div>

</template>

<style scoped>
</style>
<!--template>
  <div class="ps-4" v-show="isShow">
      <SubcategoryItem
         v-for="subcategory in subcategories"
         v-bind:key="subcategory.id"
         :subcategory="subcategory"

      />
    <div v-if="isCanModify">
      <MyButton @click="$emit('change', categoryId)">+</MyButton>
    </div>
  </div>

</template>
<script>

import { defineComponent } from 'vue'
import SubcategoryItem from "@/components/menu/SubcategoryItem.vue";
import MyButton from "@/components/UI/MyButton.vue";


export default defineComponent({
  components: { MyButton, SubcategoryItem },
  props: {
    categoryId:BigInt(0),
    subcategories: Array,
    isShow: Boolean,
    isCanModify: Boolean,//користувач має право змінювати дані
    showAddForm:Boolean//показати форму для створення нової підкатегорії
  },
  emits:['change'],
})

</script>
<style scoped>
</style-->