package com.lab.graphql.presentation.dto

import com.lab.graphql.domain.Food

data class FoodReadResponse(
    var name: String,
    var id: Long
){
    constructor(food: Food) : this(food.name, food.id!!)
}
