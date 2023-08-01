<script setup lang='ts'>
const emits = defineEmits(['click']);
const handleClick = () => {
  emits('click'); // Испускаем событие 'click'
};
</script>
<template>
  <button type='button'  @click="handleClick">
      <slot></slot>
  </button>
</template>
<style scoped>
 button{
     background: var(--background-2);
     border:solid 1px var(--background-2);
     border-radius: 10px;
     color: var(--color-text-2);
     font-weight:500;
     padding: 5px 10px;
 }
</style>