<script >
import TestNavbar from "@/components/TestNavbar.vue";

export default {
    components: {TestNavbar}
}
</script>

<template>
  <div class="my_header">
      <TestNavbar/>
  </div>
  <div class="my_content ">
      <div class="main_header">
          <div class="main_text">
              <h1>Тренуй свій мозок сьогодні</h1>
              <p>Перевірте свої знання з будь-якого предмета шкільної програми!</p>
          </div>
          <img class="main_img" src="@/assets/img/main_page.avif">

      </div>
  </div>
  <div class="my_footer"></div>

</template>

<style scoped>
.my_content{
    background: white;
    grid-column-start: 2;
    grid-column-end: 3;
    display: flex;
}
.main_header{
    display: flex;
    padding: 2rem 1rem;
}
.main_img {
    border-radius: 100%;

}
.main_text{
    margin: 0 1rem;
}

</style>
