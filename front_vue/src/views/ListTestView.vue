<script >
import TestList from "@/components/TestList.vue";
import CategoryList from "@/components/testCategory/CategoryList.vue";
import {useMenuStore} from "@/stores/menu";
const menuStore=useMenuStore();
export default {
    components: {CategoryList, TestList},
    data() {
        return {
            tests:[
                {id:1, title:'Javascript', body: 'опис посту'},
                {id:2, title:'Java', body: 'опис посту'},
                {id:3, title:'Vue', body: 'опис посту'}
            ],
            categoryList:[
                {id:1,name:'Cat1'},
                {id:2,name:'Cat2'},
                {id:3,name:'Cat3'},
                {id:4,name:'Cat4'}
            ],
            subcategoryList: [
                {id:1,name:'subcat1', category_id:1},
                {id:2,name:'subcat2', category_id:1},
                {id:3,name:'subcat1', category_id:2},
                {id:4,name:'subcat4', category_id:2},
                {id:5,name:'subcat1', category_id:3},
                {id:6,name:'subcat5', category_id:3},
                {id:7,name:'subcat4', category_id:4},
                {id:8,name:'subcat8', category_id:4},
                {id:9,name:'subcat12', category_id:4},
            ],
            isCanModify:true,
        }
    },
}
</script>

<template>
        <div class="my_content">
            <div class="my_aside ps-3">
                <CategoryList
                    :categories="categoryList"
                    :subcategoryList="subcategoryList"
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
    padding: 2rem;
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
