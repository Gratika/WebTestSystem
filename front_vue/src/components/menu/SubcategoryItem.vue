<script setup lang='ts'>

 import type { ISubCategoryDto } from '@/api/type'
 import { useMenuStore } from '@/stores/menu'

 const menuStore = useMenuStore();
 const props =defineProps({
   subcategory:Object<ISubCategoryDto>
 })
function setCurrentSubcategoryId(){
   menuStore.setSubcateoryId(props.subcategory.id);
   menuStore.setCateoryId(props.subcategory.categoryId);
}
</script>
<template>
    <div class="py-1" @click='setCurrentSubcategoryId'>
        {{props.subcategory.name}}
    </div>
</template>

<style></style>
<!--template>
    <div class="py-1">
        {{subcategory.name}}
    </div>
</template>
<script>
export  default {
    props:{
      subcategory:{}
    }
}
</script>
<style></style-->