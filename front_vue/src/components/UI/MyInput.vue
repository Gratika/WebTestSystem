<script setup>
const props = defineProps({
  modelValue:[String,Number],
  });
function updateInput(event){
  this.$emit('update:modelValue',event.target.value);
}

</script>
<template>
  <input
    class="my_input"
    type="text"
    :value="props.modelValue"
    @input="updateInput"
  >
</template>
<style>
.my_input{
    border: solid 1px var(--background-2);
    border-radius: 20px;
    padding: 10px;
}
</style>