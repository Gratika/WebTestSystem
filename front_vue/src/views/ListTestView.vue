<script setup lang='ts'>
import TestList from '@/components/test/TestList.vue'
import CategoryList from '@/components/menu/CategoryList.vue'
import { useTestStore } from '@/stores/test'
import { useMenuStore } from '@/stores/menu'
import { onMounted, ref, watch } from 'vue'

import type { ICategoryDto, ISubCategoryDto } from '@/api/type'
import NewCategoryForm from '@/components/menu/NewCategoryForm.vue'
import MyDialog from '@/components/UI/MyDialog.vue'
import NewSubCategoryForm from '@/components/menu/NewSubCategoryForm.vue'
import NewTestForm from '@/components/test/NewTestForm.vue'
import MyButton from '@/components/UI/MyButton.vue'


const menuStore = useMenuStore();
const testStore = useTestStore()

const tests = [
  { id: 1, title: 'Javascript', body: 'опис посту' },
  { id: 2, title: 'Java', body: 'опис посту' },
  { id: 3, title: 'Vue', body: 'опис посту' }
]

const isCanModify = ref(localStorage.getItem('canModify') === 'true');
const showNewCategoryForm = ref(false); // Переменная для отслеживания видимости формы
const showNewSubCategoryForm = ref(false); // Переменная для отслеживания видимости формы
const showNewTestForm = ref(false); // Переменная для отслеживания видимости формы
const subcategoryList = ref<Array<ISubCategoryDto>|null>([]);
watch(() => menuStore.categoryId, (newCategoryId) => {
  if (newCategoryId !== null) {
    subcategoryList.value = menuStore.getSubcategoryListByCategoryId;
  } else {
    subcategoryList.value = [];
  }
});

onMounted(() => {
  const storedValue = localStorage.getItem('canModify');
  isCanModify.value = storedValue === 'true';
  menuStore.getCategoryDtoList()
});

function createCategory (newCategory:ICategoryDto){
  console.log("from ListTestView:" ,newCategory);
  menuStore.saveCategory(newCategory);
  showNewCategoryForm.value=false;
}
function createSubCategory (newSubCategory:ISubCategoryDto){
  newSubCategory.categoryId = menuStore.categoryId//changeCategoryId.value;
  console.log("from ListTestView createSubCategory:" ,newSubCategory);
  menuStore.saveSubCategory(newSubCategory);
  showNewSubCategoryForm.value=false;
}
function changeShowNewCategoryForm(){
  showNewCategoryForm.value=true;
}
function changeShowNewSubCategoryForm(){
  showNewSubCategoryForm.value=true;
  //changeCategoryId.value=categoryId;
}function changeShowNewTestForm(){
  showNewTestForm.value=true;
}
function createNewTest(newTest){
  console.log("Submit Test", newTest);
  testStore.saveTest(newTest);
  showNewTestForm.value=false;


}
</script>

<template>
  <div class="my_content">
    <div class="my_aside ps-3">
      <CategoryList
        :categoryDtoList="menuStore.categoryDtoList"
        :isCanModify="isCanModify"
        :showAddForm = "showNewCategoryForm"
        :showAddSubForm = "showNewSubCategoryForm"
        @change = "changeShowNewCategoryForm"
        @changesub = "changeShowNewSubCategoryForm"
      />
      <!-- @create = "createCategory"-->
    </div>
    <div class="my_article">
      <TestList :tests="tests" />
      <div v-if='isCanModify' class='btn_aria'>
        <MyButton @click='changeShowNewTestForm'>Новий тест</MyButton>
      </div>
    </div>
    <!-- Показываем диалоговое окно с формой для добавления новой категории, если  и showNewCategoryForm === true -->
    <MyDialog v-model:show='showNewCategoryForm'>
      <NewCategoryForm
        @create = "createCategory"
      />
    </MyDialog>
    <!-- Показываем диалоговое окно с формой для добавления новой категории, если  и showNewSubCategoryForm === true -->
    <MyDialog v-model:show='showNewSubCategoryForm'>
      <NewSubCategoryForm
        @create = "createSubCategory"
      />
    </MyDialog>
    <!-- Показываем диалоговое окно с формой для добавления новой категории, если  и showNewTestForm === true -->
    <MyDialog v-model:show='showNewTestForm'>
      <div v-if='subcategoryList===null || subcategoryList.length===0'>
        <h3>Список підкатегорій для обранї категорії пустий. Створити тест можна тільки в певній підкатегорії"</h3>
      </div>
      <div v-else>
        <NewTestForm
          :subcategories ='subcategoryList'
          @create = 'createNewTest'
        />
      </div>

    </MyDialog>

  </div>
</template>

<style scoped>
.btn_aria{
  display: flex;
  justify-content: end;
  margin-top: 10px;
}
.my_article {
  background: white;
  box-shadow: 0px -25px 20px -20px rgba(0, 0, 0, 0.45), 25px 0 20px -20px rgba(0, 0, 0, 0.45),
    0px 25px 20px -20px rgba(0, 0, 0, 0.45), -25px 0 20px -20px rgba(0, 0, 0, 0.45);
  border-radius: 20px;
  grid-column-start: 2;
  grid-column-end: 3;
  padding: 2rem;
}
.my_aside {
  color: var(--color-text-2);
  background: white;
  box-shadow: 0px -25px 20px -20px rgba(0, 0, 0, 0.45), 25px 0 20px -20px rgba(0, 0, 0, 0.45),
    0px 25px 20px -20px rgba(0, 0, 0, 0.45), -25px 0 20px -20px rgba(0, 0, 0, 0.45);
  border-radius: 20px;
  grid-column-start: 1;
  grid-column-end: 2;
  padding: 10px;
}
.my_content {
  display: grid;
  grid-column-start: 2;
  grid-column-end: 3;
  grid-column-gap: 20px;
  grid-template-columns: 1fr 4fr;
  height: 100%;
}
</style>