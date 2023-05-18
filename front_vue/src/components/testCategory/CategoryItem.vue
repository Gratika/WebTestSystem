<template>
  <div>
      <button class="btn" type="button" @click="subcategoryShow = !subcategoryShow">
          {{category.name}}
      </button>
      <SubcategoryList
      :subcategories="subcategories"
      :isShow="subcategoryShow"/>
  </div>
</template>
<script>
import SubcategoryItem from "@/components/testCategory/SubcategoryItem.vue";
import SubcategoryList from "@/components/testCategory/SubcategoryList.vue";
export  default {
    components: {SubcategoryList},
    props:{
        category:{},
        subcategories:[],
    },
    data(){
        return{
            subcategoryShow:false,
        }

    }
}
</script>
<style scoped>
.btn{
    text-align: start;
    font-size: 1.1rem;
    width: 100%;
}
</style>