<script >
import AuthForm from "@/components/AuthForm.vue";
export default {
  components: {AuthForm},
  setup(){
    const  isRegister=false;
    return {
      isRegister
    }
  },
}

</script>
<template>
 <AuthForm :isRegister="isRegister"/>
</template>
<style scoped>

</style>