<script setup>
</script>
<template>
    <nav>
        <div class="container-fluid">
          <router-link to="/">
                <img src="../assets/logo.svg" alt="" width="30" height="24" class="d-inline-block align-text-top">
                TestSystem
          </router-link>
          <div class = "user_login">
            <button class="btn">Sing UP</button>
            <button class="btn">Log In</button>
          </div>
        </div>

    </nav>
</template>
<style>

nav {
  display: flex;
  width: 100%;

}

.user_login{
  display: inline-block;
  float: right;
}



</style>