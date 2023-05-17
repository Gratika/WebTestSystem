<template>
  <div>
      <p>
          <button class="btn" type="button" data-bs-toggle="collapse" data-bs-target="#subcategory" aria-expanded="false" aria-controls="subcategory">
              {{category.name}}
          </button>
      </p>
      <div class="collapse ms-1" id="subcategory">
          <SubcategoryItem
              v-for="subcat in subcategories"
              v-bind:key="subcat.id"
              :subcategory="subcat"
          />
      </div>
  </div>
</template>
<script>
import SubcategoryItem from "@/components/testCategory/SubcategoryItem.vue";
export  default {
    components: {SubcategoryItem},
    props:{
        category:{},
        subcategories:[],
    }
}
</script>
<style></style>