<script setup lang='ts'>
 const props = defineProps({
   show:{
     type:Boolean,
     default:false
   }
 })
 const emits = defineEmits(['update:show'])

 function hideDialog(){
   emits('update:show', false);
 }
</script>

<template>
 <div class='dialog' v-if='props.show' @click='hideDialog'>
   <div @click.stop class='dialog_content'>
     <slot></slot>
   </div>
 </div>
</template>

<style scoped>
.dialog{
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  background: black;
  display: flex;
  opacity: 80%;
  position: fixed;
}
.dialog_content{
  margin: auto;
  background: white;
  border-radius: 12px;
  min-height: 100px;
  min-width: 500px;
  padding: 20px;
}
</style>