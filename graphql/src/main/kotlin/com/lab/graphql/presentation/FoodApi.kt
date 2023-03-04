package com.lab.graphql.presentation

import com.lab.graphql.domain.FoodService
import com.lab.graphql.presentation.dto.FoodReadResponse
import com.lab.graphql.presentation.dto.FoodsReadResponse
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class FoodApi(
    private val foodService: FoodService
) {
    @MutationMapping
    fun saveFood(@Argument name: String): Long {
        return foodService.saveFood(name)
    }

    @QueryMapping
    fun readFood(@Argument name: String): FoodReadResponse {
        return foodService.readFood(name)
    }

    @QueryMapping
    fun readFoods(): List<FoodsReadResponse> {
        return foodService.readFoods()
    }
}
