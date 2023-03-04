package com.lab.graphql.domain

import com.lab.graphql.presentation.dto.FoodReadResponse
import com.lab.graphql.presentation.dto.FoodsReadResponse
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FoodService(
    private val foodRepository: FoodRepository
) {

    @Transactional
    fun saveFood(name: String): Long {
        return foodRepository.saveAndFlush(Food(name)).id!!
    }

    @Transactional(readOnly = true)
    fun readFood(name: String): FoodReadResponse {
        return FoodReadResponse(
            foodRepository.findByName(name)
                ?: throw EntityNotFoundException("Food Entity Not Found By Name")
        )
    }

    @Transactional(readOnly = true)
    fun readFoods(): List<FoodsReadResponse> {
        return foodRepository.findAll()
            .map { FoodsReadResponse(it) }
    }
}
