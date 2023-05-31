<template>
  <div>
      <MyBtn @click="subcategoryShow = !subcategoryShow">
          {{category.name}}
      </MyBtn>
      <SubcategoryList
      :subcategories="subcategories"
      :isShow="subcategoryShow"
      :isCanModify = "isCanModify"/>
  </div>
</template>
<script>
import SubcategoryItem from "@/components/menu/SubcategoryItem.vue";
import SubcategoryList from "@/components/menu/SubcategoryList.vue";
import MyBtn from "@/components/UI/MyBtn.vue";
export  default {
    components: {MyBtn, SubcategoryList},
    props:{
        category:{},
        subcategories:[],
        isCanModify:Boolean,
    },
    data(){
        return{
            subcategoryShow:false,
        }

    }
}
</script>
<style scoped>
</style>