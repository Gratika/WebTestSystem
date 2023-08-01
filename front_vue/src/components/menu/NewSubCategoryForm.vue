<script setup lang='ts'>


import MyButton from '@/components/UI/MyButton.vue'
import { ref} from 'vue'
import type { ICategoryDto, ISubCategoryDto } from '@/api/type'
import { toTypedSchema } from '@vee-validate/zod'
import * as zod from 'zod';
import { ErrorMessage,Field,Form,useForm } from 'vee-validate'

const newSubCategory = ref<ISubCategoryDto>({
  id: null,
  name: "",
  categoryId: null
});
/*валідація форми*/
const subCategorySchema = toTypedSchema (
  zod.object({
    name:zod
      .string()
      .nonempty('Поле name обов\'язкове'),
  })
);
const { handleSubmit, errors, resetForm } = useForm({
  validationSchema: subCategorySchema,
});

//визначення події create
const emit = defineEmits(['create'])
function createSubCategory(){
  console.log(newSubCategory.value);
  //генеруємо подію, на яку підпишеться батьківський компонент
  //перший аргумент, назва події, другий аргумент - дані, які потрібно передати в батьківський компонент
  emit('create', newSubCategory.value);
  //очищаємо значення полів у формі
  newSubCategory.value.name='';
}
</script>

<template>
  <Form :validation-schema="subCategorySchema">
    <h4>Додавання нової підкатегорії</h4>
    <div class="row mb-3 mx-0">
      <label for="name" class="mb-1">Назва:</label>
      <Field
        type="form-control"
        name="name"
        id="name"
        class="my_input"
        v-bind:value="newSubCategory.name"
        @input="newSubCategory.name=$event.target.value"
      />
      <ErrorMessage name="name" class="text-danger"/>
    </div>
    <div class="btn_aria">
      <MyButton class="mx-1" @click="createSubCategory">Зберегти</MyButton>
    </div>
  </Form>

</template>

<style scoped>
.my_input{
  border: solid 1px var(--background-2);
  border-radius: 20px;
  padding: 10px;
}
.btn_aria{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin: 1rem 0;
}
</style>