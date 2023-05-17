<template>
    <div class="col-xl-4 col-md-6 col-12 py-1 my-2">
        <div class="card">
            <div class="card-header">
                <h5 class="card-title">{{test.title}}</h5>
            </div>
            <div class="card-body">
                <p class="card-text">{{ test.body }}</p>
                <a href="#" class="btn btn-primary">Розпочати</a>
            </div>
        </div>
    </div>

</template>
<script>
export default {
    props:{
        test: {}

    }

}
</script>
<style>
 .card-header {
     background: var(--background-2);
     color: var(--color-text-2)
 }
 .card{
     min-width: 200px;
 }
</style>