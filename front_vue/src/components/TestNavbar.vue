<script setup>
import MyBtn from "@/components/UI/MyBtn.vue";
import {ref, watchEffect } from 'vue'
import { useAuthStore } from '@/stores/auth'

const isUserAuthenticated = ref(false);
watchEffect(() => {
  isUserAuthenticated.value = localStorage.getItem('isLogin') === 'true';
});

const isUserMenuVisible = ref(false);

const authStore = useAuthStore();
function toggleUserMenu() {
  isUserMenuVisible.value = !isUserMenuVisible.value;
}
// Метод разлогинивания пользователя
function logoutUser() {
  toggleUserMenu();
  console.log("isUserAuthenticated: ",isUserAuthenticated.value);
  if (isUserAuthenticated.value){
    authStore.onLogout();
  }
}

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
              <div @click="toggleUserMenu" class="user_icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                  <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
                </svg>
              </div>
              <!-- Показываем меню только при isUserMenuVisible === true -->
              <div v-if="isUserMenuVisible" class="user_menu">
                <router-link to="/profile" class='d-block'>
                  <MyBtn>Профиль</MyBtn>
                </router-link>
                <MyBtn @click="logoutUser">Выход</MyBtn>
              </div>
            <!--/template-->
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
.user_icon{
  display: inline-block;
  margin-left: 10px;
  border: 2px solid white;
  border-radius: 50%;
  padding: 1px;
}
.user_menu {
  position: absolute;
  top: 55px;
  right: 25px;
  background-color: white;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
  padding: 16px;
  border-radius: 4px;
  z-index: 1;
}

.btn{
    color: var(--color-text-1);
}

</style>