<script setup lang="ts">
import MyButton from '@/components/UI/MyButton.vue'
import { ref } from 'vue'
import type { IOwner, ISubCategoryDto, ITest } from '@/api/type'
import { ErrorMessage,Field,Form, useForm } from 'vee-validate';
import { toTypedSchema  } from '@vee-validate/zod';
import * as zod from 'zod';

const newTest = ref<ITest>(
  {
    id:null,
    name: '',
    isPublic: false,
    description: '',
    owner: null,
    subcategoryId:null,
  }
);
const owner = ref<IOwner>(
  {
    id:null,
    login: '',
    email:'',
  }
);
const props = defineProps({
  subcategories:{
    type: Array<ISubCategoryDto>,
    default:null
  }
});
const emits = defineEmits(  ['create']);
function createTest(){
  owner.value.id = parseInt(localStorage.getItem('userId'),10);
  newTest.value.owner = owner.value;
  emits('create', newTest.value);
}

  /*валідація форм*/
const testSchema = toTypedSchema (
    zod.object({
      testName: zod
        .string()
        .nonempty('Поле Назва обов\'язкове'),
      testSubcategory: zod
        .string()
        .nonempty('Потрібно обрати підкатегорію')
        .refine(() => {
          // Возвращаем функцию валидации
          const availableSubcategoryIds = props.subcategories?.map(subcategory => subcategory.id);

          return {
            validate: (value: string) => {
              return availableSubcategoryIds?.includes(value);
            },
            message: 'Оберіть дійсну підкатегорію',
          };
        }),
    })
);
  const { handleSubmit, errors, resetForm } = useForm({
    validationSchema: testSchema,
  });
function onSubmit () {
    createTest();
    resetForm();
}

</script>
<template>
  <Form :validation-schema="testSchema" @submit="handleSubmit($event, onSubmit)">
    <h1>Створення нового тесту</h1>
    <div class="row mb-3 mx-0">
      <label for="testName" class="mb-1">Назва тесту:</label>
      <Field
        type="form-control"
        class="my_input"
        id="testName"
        name="testName"
        v-bind:value='newTest.name'
        @input='newTest.name=$event.target.value'
      />
      <ErrorMessage name="testName" class="text-danger"/>
    </div>
    <div class="row mb-3 mx-0">
      <label for="testDescribe" class="form-label">Короткий опис</label>
      <textarea
        class="form-control"
        id="testDescribe"
        name="testDescribe"
        rows="3"
        v-bind:value='newTest.description'
        @input='newTest.description=$event.target.value'
      />
    </div>
    <div class="row mb-3 mx-0">
      <label for="testSubcategory" class="form-label">Оберіть категорію</label>
      <select
        id="testSubcategory"
        name="testSubcategory"
        class="form-select"
        v-model="newTest.subcategoryId"
      >
        <option
          v-for="option in props.subcategories"
          :key="option.id"
          :value="option.id"
        >{{option.name}}
        </option>
      </select>
      <ErrorMessage name="testSubcategory" class="text-danger"/>
    </div>
    <div class="mb-3 form-check">
      <input
        type="checkbox"
        class="form-check-input"
        id="isPublic"
        name="isPublic"
        v-model='newTest.isPublic'
      />
      <label v-if='newTest.isPublic'
        class="form-check-label"
        for="isPublic"
        >Тест доступний для всіх користувачів</label>
      <label v-else
             class="form-check-label"
             for="isPublic"
      >Тест приватний</label>
    </div>
    <div class='btn_aria'>
      <MyButton @click="onSubmit">Створити</MyButton>
    </div>

  </Form>
</template>

<style>
.btn_aria{
  display: flex;
  flex-direction: row;
  justify-content: end;
  margin: 1rem 0;
}
</style>