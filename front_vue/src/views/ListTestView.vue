<script setup>
import TestList from "@/components/test/TestList.vue";
import CategoryList from "@/components/menu/CategoryList.vue";
import {useMenuStore} from "@/stores/menu";
import {onMounted} from "vue";

const menuStore = useMenuStore();
const isCanModify = true;
const  tests =[
  {id:1, title:'Javascript', body: 'опис посту'},
  {id:2, title:'Java', body: 'опис посту'},
  {id:3, title:'Vue', body: 'опис посту'}
]

onMounted(()=>{
  menuStore.getCatrgoryDtoList();
})

</script>

<template>
        <div class="my_content">
            <div class="my_aside ps-3">
                <CategoryList
                    :categoryDtoList="menuStore.categoryDtoList"
                    :isCanModify="isCanModify"
                />
            </div>
            <div class="my_article">
                <TestList :tests="tests"/>
            </div>
        </div>

</template>

<style scoped>
.my_article{
    background: white;
    box-shadow: 0px -25px 20px -20px rgba(0, 0, 0, 0.45), 25px 0 20px -20px rgba(0, 0, 0, 0.45), 0px 25px 20px -20px rgba(0, 0, 0, 0.45), -25px 0 20px -20px rgba(0, 0, 0, 0.45);
    border-radius: 20px;
    grid-column-start: 2;
    grid-column-end: 3;
    padding: 2rem;
}
.my_aside{
    color: var(--color-text-2);
    background: white;
    box-shadow: 0px -25px 20px -20px rgba(0, 0, 0, 0.45), 25px 0 20px -20px rgba(0, 0, 0, 0.45), 0px 25px 20px -20px rgba(0, 0, 0, 0.45), -25px 0 20px -20px rgba(0, 0, 0, 0.45);
    border-radius: 20px;
    grid-column-start: 1;
    grid-column-end: 2;
    padding: 10px;
}
.my_content{
    display: grid;
    grid-column-start: 2;
    grid-column-end: 3;
    grid-column-gap: 20px;
    grid-template-columns: 1fr 4fr;
    height: 100%;
}


</style>
