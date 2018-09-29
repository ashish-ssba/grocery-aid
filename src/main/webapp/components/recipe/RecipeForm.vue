<template>
    <form>
        <base-message v-if="successMessage.length > 0"
            header="Success"
            v-bind:message="successMessage"
            v-on:close="successMessage=''" />
        <error-message v-if="errorMessage.length > 0"
            header="Error Saving Recipe"
            v-bind:message="errorMessage"
            v-on:close="errorMessage=''" />
        <fieldset class="field">
            <label class="label">Recipe Name</label>
            <div class="control">
                <input class="input" type="text" placeholder="Recipe Name" v-model="name" />
            </div>
        </fieldset>
        <fieldset>
            <ingredient-line v-for="ingredient in ingredients" 
                v-bind:key="ingredient.id" 
                v-bind:name="ingredient.name" 
                v-bind:amount="ingredient.amount" 
                v-bind:unit="ingredient.unit"></ingredient-line>
            <div class="field">
                <div class="control">
                    <input class="button" type="button" value="Add Ingredient" v-on:click="addIngredient" />
                </div>
            </div>
        </fieldset>
        <fieldset class="field">
            <div class="control">
                <input class="button" type="button" value="Save" v-on:click="saveRecipe" />
            </div>
        </fieldset>
    </form>
</template>

<script>
import Vue from 'vue'

import IngredientLine from './IngredientLine.vue'
import BaseMessage from '../base/BaseMessage.vue'
import ErrorMessage from '../base/BaseErrorMessage.vue'

export default Vue.extend({
    name: 'recipeform',
    data: function() {
        return {
            name: '',
            ingredients: [],
            nextId: 0,
            successMessage: '',
            errorMessage: ''
        }
    },
    components: {
        IngredientLine, ErrorMessage, BaseMessage
    },
    methods: {
        addIngredient: function() {
            this.ingredients.push({
                id: this.nextId++,
                name: '',
                amount: 1,
                unit: ''
            })
        },
        saveRecipe: function() {
            let ingredients = this.ingredients.map(ing => {
                return {
                    name: ing.name,
                    unit: ing.unit,
                    amount: ing.amount
                }
            })

            fetch('/recipes', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({recipeName: this.name, ingredients: ingredients})
            }).then(response => {
                if (response.ok) {
                    this.successMessage = "Succesfully saved recipe!"
                } else {
                    this.errorMessage = response.statusText
                }
            })
        }
    }
})
</script>

<style lang="scss">
@import "~bulma/sass/utilities/_all.sass";
@import "~bulma/sass/elements/button.sass";
@import "~bulma/sass/elements/form.sass";
</style>
