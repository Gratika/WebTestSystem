<script lang="ts">


import MyButton from "@/components/UI/MyButton.vue";
import MyInput from "@/components/UI/MyInput.vue";
import type {ILoginInput, ISignUpInput} from "@/api/type";
import {useAuthStore} from "@/stores/auth";
import { Form, useField, useForm } from 'vee-validate';
import { toTypedSchema  } from '@vee-validate/zod';
import * as zod from 'zod';
import { createToast } from 'mosha-vue-toastify';

export default {
  components: {MyButton, MyInput},
  props:{
    isRegister:Boolean,
  },
  setup(){
    const userLogin:ILoginInput =  {
        login:"",
        password: ""
    };
    const userRegistration:ISignUpInput =  {
        email: "",
        login: "",
        password: "",
        //passwordConfirm: "",
    }
    const passwordConfirm:String="";


    const authStore = useAuthStore();
    /*валідація форм*/
      const loginSchema = toTypedSchema (
          zod.object({
              login:zod
                  .string()
                  .min(1,'Login is required'),
              email: zod
                  .string()
                  .min(1, 'Email address is required')
                  .email('Email Address is invalid'),
              password: zod
                  .string()
                  .min(1, 'Password is required')
                  .min(8, 'Password must be more than 8 characters')
                  .max(32, 'Password must be less than 32 characters'),
              passwordConfirm: zod.string().min(1, 'Please confirm your password'),
          })
              .refine((data) => data.password === data.passwordConfirm, {
                  path: ['passwordConfirm'],
                  message: 'Passwords do not match',
              })

      );
      const { handleSubmit, errors, resetForm } = useForm({
          validationSchema: loginSchema,
      });
      const { value: login } = useField('name');
      const { value: email } = useField('email');
      const { value: password } = useField('password');
      const { value: passwordConfirm } = useField('passwordConfirm');

      function onLogin(){
        userLogin.login = userRegistration.login;
        userLogin.password = userRegistration.password;
        authStore.onLogin(userLogin);
    }
    const onRegistration=()=>{
        console.log("user_registration:");
        console.log(userRegistration);
        authStore.onRegistration(userRegistration);
    };
    return{
        userLogin,
        userRegistration,
        onLogin,
        onRegistration,
        passwordConfirm
    }
  },

}


</script>

<template>
  <div class="my_content">
    <div class="my_container">
        <div class="row mb-3">
          <MyInput
                  type="form-control"
                  name="login"
                  id="loginInput"
                  placeholder="Login"
                  v-bind:value="userRegistration.login"
                  @input="userRegistration.login=$event.target.value"
          />
        </div>

        <div class="row mb-3" v-if="isRegister">
          <MyInput
                  type="email"
                  id="emailInput"
                  v-bind:value="userRegistration.email"
                  @input="userRegistration.email=$event.target.value"
                  placeholder="Name@example.com"
          />
        </div>
        <div class="row mb-3">
          <MyInput
                  type="password"
                  id="passwordInput"
                  v-bind:value="userRegistration.password"
                  @input="userRegistration.password=$event.target.value"
                  placeholder="Password"
          />
        </div>
        <div class="row mb-3">
            <MyInput
                    type="password"
                    id="passwordConfirm"
                    name = "passwordConfirm"
                    v-bind:value="passwordConfirm"
                    placeholder="Confirm password"
            />
        </div>
        <div class="btn_aria">
          <MyButton v-if="!isRegister" class="mx-1">Увійти</MyButton>
          <MyButton v-else class="mx-1" @click="onRegistration">Зареєструватися</MyButton>
        </div>


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
</style>