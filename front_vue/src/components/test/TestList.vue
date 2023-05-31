<template>
    <div class="container">
        <div class="row">
            <CardTest
                 v-for="test in tests"
                 v-bind:key="test.id"
                 :test="test"
           />

        </div>
    </div>
</template>
<script>
import CardTest from "./CardTest.vue";
export default {
    components: {CardTest},
    props:{
        tests:{
            type: Array,
        }
    }
}
</script>
<style></style>