<template>
  <div class="card">
    <div class="card-header">
      Featured
    </div>
    <div class="card-body">
      <h5 class="card-title">{{test.title}}</h5>
      <p class="card-text">{{ test.body }}</p>
      <a href="#" class="btn btn-primary">Розпочати</a>
    </div>
  </div>
</template>
<script>
 export default {
   props:{
     test: {
       type:{}
     }

   }

 }
</script>
<style></style>