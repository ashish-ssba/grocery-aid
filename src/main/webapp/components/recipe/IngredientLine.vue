<template>
    <div class="field is-grouped">
        <div class="control">
            <input class="input" type="number" v-model.number="amount" min="0" />
        </div>
        <div class="select">
            <select v-model="unit">
                <option value=''>None</option>
                <option>tsp</option>
                <option>tbsp</option>
                <option>fl oz</option>
                <option>cup</option>
                <option>pint</option>
                <option>qt</option>
                <option>gal</option>
                <option>ml</option>
                <option>l</option>
                <option>lb</option>
                <option>oz</option>
                <option>mg</option>
                <option>g</option>
                <option>kg</option>
            </select>
        </div>
        <div class="control">
            <input class="input" type="text" placeholder="Ingredient" v-model="name" />
        </div>
    </div>
</template>

<script lang="ts">
import Vue from 'vue'
export default Vue.extend({
    name: 'ingredientline',
    data: function() {
        return {
            amount: 1,
            unit: 'None',
            name: ''
        }
    }
})
</script>

<style lang="scss">
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/elements/form.sass";
</style>
