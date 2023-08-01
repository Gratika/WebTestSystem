<script setup lang='ts'>

import CategoryItem from '@/components/menu/CategoryItem.vue';
import MyButton from '@/components/UI/MyButton.vue';

import { defineProps } from 'vue'

import type { ICategoryDto } from '@/api/type'




const props = defineProps({
  categoryDtoList:{
    type: Array<ICategoryDto>,
    required: true,
    default: () => []
  },
  isCanModify: Boolean,
  showAddForm:Boolean,
  showAddSubForm:Boolean,
});
//const showAddForm = ref(false); // Переменная для отслеживания видимости формы
//визначення події create
const emits = defineEmits(['change','changesub'])
function changeShowAddForm(){
  emits('change');
}
function changeShowAddSubForm(categoryId){
  emits('changesub', categoryId);
}
/*function createCategory(newCategory:ICategoryDto){
  emit('create',newCategory);
  showAddForm.value=!showAddForm.value;
}*/
</script>
<template>
  <div class="my-3">
      <CategoryItem
              v-for='category  in props.categoryDtoList'
              v-bind:key='category.id'
              :category='category'
              :subcategories='category.subcategoryDtoList'
              :isCanModify='isCanModify'
              :showAddForm='showAddSubForm'
              @change = 'changeShowAddSubForm'
      />
    <div class="left" v-if="isCanModify">
      <MyButton @click="changeShowAddForm">Додати</MyButton>
    </div>
    <!--div class="left" v-if="isCanModify">
      <MyButton @click="showAddForm = true">Додати</MyButton>
    </div-->

    <!-- Показываем диалоговое окно с формой для добавления новой записи, если  и showAddForm === true -->

      <!--MyDialog v-model:show='showAddForm'>
        <NewCategoryForm
          @create = "createCategory"
        />
      </MyDialog-->


  </div>
</template>
<style scoped>
 .left{
     display: flex;
     justify-content: flex-end;
     padding: 20px 0;
 }
</style>