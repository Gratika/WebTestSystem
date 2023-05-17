<script >
import TestList from "@/components/TestList.vue";
import Navbar from "@/components/TestNavbar.vue";
import CategoryList from "@/components/testCategory/CategoryList.vue";
export default {
 components: {CategoryList, Navbar, TestList},
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
    {id:1,name:subcat1, category_id:1},
    {id:2,name:subcat2, category_id:1},
    {id:3,name:subcat1, category_id:2},
    {id:4,name:subcat4, category_id:2},
    {id:5,name:subcat1, category_id:3},
    {id:6,name:subcat5, category_id:3},
    {id:7,name:subcat4, category_id:4},
    {id:8,name:subcat8, category_id:4},
    {id:9,name:subcat12, category_id:4},
   ]
  }
 },
}
</script>

<template>
 <div class="my_header">
   <Navbar/>
 </div>
 <div class="my_aside">
  <CategoryList/>
 </div>
 <div>
  <TestList :tests="tests"/>
 </div>
 <div class="my_footer">footer</div>
</template>

<style scoped>
.my_aside{

}
.my_header {
 background: #661B0A;
 color: #E6D1CC;
 grid-column-start: 1;
 grid-column-end: 4;
 line-height: 1.5;
}
.my_footer {
 grid-column-start: 1;
 grid-column-end: 4;
 line-height: 1.5;
}



</style>
