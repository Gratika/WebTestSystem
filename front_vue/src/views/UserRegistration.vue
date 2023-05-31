<script setup>
import MyInput from "@/components/UI/MyInput.vue";
import MyButton from "@/components/UI/MyButton.vue";
</script>
<template>
  <div class="my_content">
      <form>
          <div class="mb-3">
              <label for="emailInput" class="form-label">Email address</label>
              <MyInput type="email" placeholder="name@example.com"/>
          </div>
          <div class="mb-3">
              <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
              <MyInput placeholder="name@example.com" />
          </div>
          <div class="btn_aria">
              <MyButton class="mx-1">Увійти</MyButton>
              <MyButton class="mx-1">Зареєструватися</MyButton>
          </div>
      </form>

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
    align-items: center;
    background:white ;
    border: solid 1px var(--color-text-1);
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    justify-content: space-around;
    padding: 20px 10px;

}
</style>