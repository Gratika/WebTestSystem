<script lang="ts">


import MyButton from "@/components/UI/MyButton.vue";
import type {ISignUpInput} from "@/api/type";
import {useAuthStore} from "@/stores/auth";
import { ErrorMessage,Field, useForm } from 'vee-validate';
import { toTypedSchema  } from '@vee-validate/zod';
import * as zod from 'zod';
import { createToast } from 'mosha-vue-toastify';

export default {
  components: {MyButton, Field, ErrorMessage},
  setup(){

    const userRegistration:ISignUpInput =  {
        email: "",
        login: "",
        password: "",
        passwordConfirm: "",
    }
    const authStore = useAuthStore();
    /*валідація форм*/
      const registerSchema = toTypedSchema (
          zod.object({
              login:zod
                  .string()
                  .nonempty('Поле login обов\'язкове'),
              email: zod
                  .string()
                  .nonempty('Поле email обов\'язкове')
                  .email('Email адреса не валідна'),
              password: zod
                  .string()
                  .nonempty('Поле password обов\'язкове')
                  .min(8, 'Password must be more than 8 characters')
                  .max(32, 'Password must be less than 32 characters'),
              passwordConfirm: zod.string().nonempty('Будь ласка, повторіть свій пароль'),
          })
              .refine((data) => data.password === data.passwordConfirm, {
                  path: ['passwordConfirm'],
                  message: 'Паролі не співпадають',
              })

      );
      const { handleSubmit, errors, resetForm } = useForm({
          validationSchema: registerSchema,
      });


    const onRegistration=()=>{
        console.log("user_registration:");
        console.log(userRegistration);
        authStore.onRegistration(userRegistration);
    };
    return{
        userRegistration,
        onRegistration,
        registerSchema
    }
  },

}


</script>

<template>
  <div class="my_content">
    <div class="my_container">
        <Form :validation-schema="registerSchema">
            <div class="row mb-3">
                <Field
                        type="form-control"
                        name="login"
                        id="login"
                        class="my_input"
                        v-bind:value="userRegistration.login"
                        @input="userRegistration.login=$event.target.value"
                />
                <ErrorMessage name="login" />
            </div>

            <div class="row mb-3">
                <Field
                        type="email"
                        name="email"
                        id="email"
                        class="my_input"
                        v-bind:value="userRegistration.email"
                        @input="userRegistration.email=$event.target.value"
                />
                <ErrorMessage name="email" />
            </div>
            <div class="row mb-3">
                <Field
                        type="password"
                        name="password"
                        id="password"
                        class="my_input"
                        v-bind:value="userRegistration.password"
                        @input="userRegistration.password=$event.target.value"
                />
                <ErrorMessage name="password" />
            </div>
            <div class="row mb-3">
                <Field
                        type="password"
                        id="passwordConfirm"
                        name = "passwordConfirm"
                        class="my_input"
                        v-bind:value="userRegistration.passwordConfirm"
                        @input="userRegistration.passwordConfirm=$event.target.value"
                />
                <ErrorMessage name="passwordConfirm" />
            </div>
            <div class="btn_aria">
                <MyButton class="mx-1" @click="onRegistration">Зареєструватися</MyButton>
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
.form-label{
  margin: 0;
  padding: 10px;
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