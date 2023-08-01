<script lang="ts">


import MyButton from '@/components/UI/MyButton.vue';
import type {ILoginInput} from '@/api/type';
import {useAuthStore} from '@/stores/auth';
import { ErrorMessage,Field,Form, useForm } from 'vee-validate';
import { toTypedSchema  } from '@vee-validate/zod';
import * as zod from 'zod';

export default {
  components: {MyButton, Field, ErrorMessage,Form},
  setup(){

    const userLogin:ILoginInput =  {
        login:'',
        password: ''
    };
    const authStore = useAuthStore();
    /*валідація форм*/
      const loginSchema = toTypedSchema (
        zod.object({
          login:zod
            .string()
            .nonempty('Поле login обов\'язкове'),
          password: zod
            .string()
            .nonempty('Поле password обов\'язкове')
            .min(8, 'Password must be more than 8 characters')
            .max(32, 'Password must be less than 32 characters'),
        })

      );
      const { handleSubmit, errors, resetForm } = useForm({
          validationSchema: loginSchema,
      });


      function onLogin(){
        console.log("onLogin");
        authStore.onLogin(userLogin);
    }

    return{
        userLogin,
        onLogin,
        loginSchema
    }
  },

}


</script>

<template>
  <div class="my_content">
    <div class="my_container">
      <Form :validation-schema="loginSchema">
        <div class="row mb-3 mx-0">
          <label for="login" class="mb-1">Логін:</label>
          <Field
            type="form-control"
            name="login"
            id="login"
            class="my_input"
            v-bind:value="userLogin.login"
            @input="userLogin.login=$event.target.value"
          />
          <ErrorMessage name="login" class="text-danger"/>
        </div>

        <div class="row mb-3 mx-0">
          <label for="password" class="mb-1">Пароль:</label>
          <Field
            type="password"
            name="password"
            id="password"
            class="my_input"
            v-bind:value="userLogin.password"
            @input="userLogin.password=$event.target.value"
          />
          <ErrorMessage name="password" class="text-danger"/>
        </div>

        <div class="btn_aria">
          <MyButton class="mx-1" @click="onLogin">Увійти</MyButton>
        </div>
      </Form>
    </div>
  </div>
</template>

<style scoped>
.btn_aria{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin: 1rem 0;
}

.my_content{
  align-items: center;
  background:white ;
  border-radius: 20px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  height: 100%;
  padding: 3rem 0;
}
.my_container{
  align-content: space-around;
  align-items: stretch;
  background:white ;
  border: solid 1px var(--color-text-1);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  min-width: 300px;
  justify-content: space-around;
  padding: 20px;

}
.my_input{
  border: solid 1px var(--background-2);
  border-radius: 20px;
  padding: 10px;
}
</style>