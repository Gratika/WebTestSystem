<script setup lang='ts'>


import MyButton from '@/components/UI/MyButton.vue'
import { ref} from 'vue'
import type { ICategoryDto} from '@/api/type'
import { toTypedSchema } from '@vee-validate/zod'
import * as zod from 'zod';
import { ErrorMessage,Field,Form,useForm } from 'vee-validate'

const newCategory = ref<ICategoryDto>({
  id: null,
  name: "",
  subcategoryDtoList: null
});
/*валідація форми*/
const categorySchema = toTypedSchema (
  zod.object({
    name:zod
      .string()
      .nonempty('Поле name обов\'язкове'),
  })
);
const { handleSubmit, errors, resetForm } = useForm({
  validationSchema: categorySchema,
});

//визначення події create
const emit = defineEmits(['create'])
function createCategory(){
  //генеруємо подію, на яку підпишеться батьківський компонент
  //перший аргумент, назва події, другий аргумент - дані, які потрібно передати в батьківський компонент
  emit('create', newCategory.value);
  //очищаємо значення полів у формі
  newCategory.value.name='';
}
</script>

<template>
  <Form :validation-schema="categorySchema">
    <h4>Додавання нової категорії</h4>
    <div class="row mb-3 mx-0">
      <label for="name" class="mb-1">Назва:</label>
      <Field
        type="form-control"
        name="name"
        id="name"
        class="my_input"
        v-bind:value="newCategory.name"
        @input="newCategory.name=$event.target.value"
      />
      <ErrorMessage name="name" class="text-danger"/>
    </div>
    <div class="btn_aria">
      <MyButton class="mx-1" @click="createCategory">Зберегти</MyButton>
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