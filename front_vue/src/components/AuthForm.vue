<script lang="ts">

import MyButton from "@/components/UI/MyButton.vue";
import MyInput from "@/components/UI/MyInput.vue";
export default {
  components: {MyButton, MyInput},
  props:{
    isRegister:Boolean,
  },
  setup(){

  },

}


</script>

<template>
  <div class="my_content">
    <div class="my_container">
        <div class="row mb-3">
          <MyInput type="form-control" id="loginInput" placeholder="login"/>
        </div>

        <div class="row mb-3" v-if="isRegister">
          <MyInput type="email" id="emailInput" placeholder="name@example.com"/>
        </div>
        <div class="row mb-3">
          <MyInput type="password" id="passwordInput" placeholder="password" />
        </div>
        <div class="btn_aria">
          <MyButton v-if="!isRegister" class="mx-1">Увійти</MyButton>
          <MyButton v-else class="mx-1">Зареєструватися</MyButton>
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