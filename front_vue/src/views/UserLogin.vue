<script >
import AuthForm from "@/components/RegistratorForm.vue";
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