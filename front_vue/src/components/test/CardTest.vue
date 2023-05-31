<template>
    <div class="col-xl-4 col-md-6 col-12 py-1 my-2">
        <div class="card">
            <div class="card-header">
                <h5 class="card-title">{{test.title}}</h5>
            </div>
            <div class="card-body">
                <p class="card-text">{{ test.body }}</p>
                <MyButton>Розпочати</MyButton>
            </div>
        </div>
    </div>

</template>
<script>
import MyButton from "@/components/UI/MyButton.vue";

export default {
    components: {MyButton},
    props:{
        test: {}

    }

}
</script>
<style scoped>
 .card-header {
     background: var(--background-2);
     border: none;
     text-align: center;
 }
 .card{
     border: 1px solid var(--background-2);
     box-shadow: rgba(126, 248, 197, 0.63) 0px 0px 0.25em, rgba(48, 154, 110, 0.2) 0px 0.25em 1em;
     color: var(--color-text-2);
     min-width: 200px;
     max-width: 400px;
     min-height: 150px;
     max-height: 400px;
     height: 100%;

 }
 .card-body{
     align-items: flex-end;
     display: flex;
     justify-content: space-between;
     flex-wrap: wrap;
     flex-direction: column;
 }
 .card-text{
     width: 100%;
 }

</style>