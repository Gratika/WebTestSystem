<script setup lang='ts'>
import SubcategoryList from '@/components/menu/SubcategoryList.vue';
import MyBtn from '@/components/UI/MyBtn.vue';
import type { ICategoryDto, ISubCategoryDto } from '@/api/type'
import { ref,defineProps } from 'vue'
import { useMenuStore } from '@/stores/menu'

const menuStore = useMenuStore();
const  props = defineProps({
    category:Object<ICategoryDto>,
    subcategories:Array<ISubCategoryDto>,
    isCanModify:Boolean,
    showAddForm:Boolean
  });
  const emits = defineEmits(['change']);
  const subcategoryShow = ref(false);
  function changeShowAddForm(categoryId){
      emits('change', categoryId);
  }
  function setCurrentCategoryId(){
    menuStore.setCateoryId(props.category.id);
    menuStore.setSubcateoryId(0n);
    subcategoryShow.value = !subcategoryShow.value;

  }

</script>
<template>
  <div>
    <MyBtn @click='setCurrentCategoryId'>
      {{props.category.name}}
    </MyBtn>
    <SubcategoryList
      :categoryId='props.category.id'
      :subcategories='subcategories'
      :isShow='subcategoryShow'
      :isCanModify = 'isCanModify'
      :showAddForm = 'showAddForm'
      @change = 'changeShowAddForm'/>
  </div>
</template>

<style scoped>
</style>
<!--template>
  <div>
      <MyBtn @click="subcategoryShow = !subcategoryShow">
          {{category.name}}
      </MyBtn>
      <SubcategoryList
        :categoryId='category.id'
        :subcategories='subcategories'
        :isShow='subcategoryShow'
        :isCanModify = 'isCanModify'
        :showAddForm = 'showAddForm'
        @change = 'changeShowAddForm'/>
  </div>
</template>
<script>
import SubcategoryList from '@/components/menu/SubcategoryList.vue';
import MyBtn from '@/components/UI/MyBtn.vue';

export  default {
    components: {MyBtn, SubcategoryList},
    props:{
        category:{},
        subcategories:Array,
        isCanModify:Boolean,
        showAddForm:Boolean
    },
    emits:['change'],
    data(){
        return{
            subcategoryShow:false,

        }

    },
  methods:{
      changeShowAddForm(categoryId){
        this.$emit('change', categoryId);
      }
  }
}
</script>
<style scoped>
</style-->