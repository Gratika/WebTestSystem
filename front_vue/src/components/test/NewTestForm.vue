<template>
    <form @submit.prevent>
        <div class="mb-3">
            <label for="testName" class="form-label">Назва тесту</label>
            <input type="email" class="form-control" id="testName" placeholder="name@example.com">
        </div>
        <div class="mb-3">
            <label for="testDescribe" class="form-label">Короткий опис</label>
            <textarea class="form-control" id="testDescribe" rows="3"></textarea>
        </div>
        <div class="mb-3">
            <label for="testSubcategory" class="form-label">Оберіть категорію</label>
            <select id="testSubcategory" class="form-select">
                <option>Disabled select</option>
            </select>
        </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="isPublic">
                <label class="form-check-label" for="isPublic">Тест доступний навіть для незареєстрованих користувачів</label>
            </div>
            <button type="submit" class="btn btn-primary">Створити</button>
    </form>

</template>
<script>
export default {}
</script>
<style></style>