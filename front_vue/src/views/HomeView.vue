<script >

export default {
}
</script>

<template>
    <div class="my_content ">
        <div class="main_header">
            <div class="main_text">
                <h1>Вчиться та навчайте разом з нами</h1>
                <p>Перевірте свої знання з будь-якого предмета шкільної програми!</p>
            </div>
            <img class="main_img" src="@/assets/img/girl.avif">

        </div>
        <div class="row px-3">
            <div class="col-12 col-md-6 col-xl-3">
                <div class="card text-white mb-3 main_card" >
                    <div class="card-header">
                        <h5>Friendly interface</h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Простий та інтуітивно зрозумілий інтерфейс.</p>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-6 col-xl-3">
                <div class="card text-white mb-3 main_card" >
                    <div class="card-header">
                        <h5>Free content</h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Усі тести в нашій системі є абсолютно безкоштовними</p>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-6 col-xl-3">
                <div class="card text-white mb-3 main_card" >
                    <div class="card-header">
                        <h5>the best content</h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-6 col-xl-3">
                <div class="card text-white mb-3 main_card">
                    <div class="card-header">
                        <h5>Primary card title</h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="main_button">
            <router-link to="/public"><button class="btn">Спробувати</button></router-link>
        </div>
    </div>
</template>

<style scoped>
.btn{
    background: var(--add_color_dark);
    margin: 0 1rem;
}
.card-body{
    background: white;
    border: none;
    color: var(--color-text-2);
}
.main_button{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: flex-end;

}
.card-header{
    background: var(--add_color_dark);
    border: none;
}
.main_card {
    border: none;
    margin: 2rem 1rem;
    max-width: 18rem;
    max-height: 17rem;
    box-shadow: 9px 10px 17px 0px rgba(136, 179, 172, 0.74);
}
.my_content{
    background: white;
    border-radius: 20px;
    display: flex;
    justify-content: space-around;
    flex-direction: column;
    height: 100%;
    padding: 1rem 1rem;
}
.main_header{
    align-content: space-around;
    display: flex;
    justify-content: space-around;

}
.main_img {
    border-radius: 100%;
    max-height: 25rem;
    max-width: 25rem;

}
.main_text{
    margin: 0 1rem;
}

</style>

