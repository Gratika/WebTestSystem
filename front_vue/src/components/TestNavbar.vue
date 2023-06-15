<script setup>
import MyBtn from "@/components/UI/MyBtn.vue";
</script>
<template>
    <nav>
        <div class="container-fluid">
          <router-link to="/">
                <img src="../assets/logo.svg" alt="" width="30" height="24" >
                TestSystem
          </router-link>
          <div class = "user_login">
              <router-link to="/registration">
                 <MyBtn>Sing UP</MyBtn>
              </router-link>
              <router-link to="/login">
                <MyBtn>Log In</MyBtn>
              </router-link>
          </div>
        </div>

    </nav>
</template>
<style scoped>

nav {
  display: flex;
  width: 100%;

}

.user_login{
  display: inline-block;
  float: right;
}
.btn{
    color: var(--color-text-1);
}

</style>