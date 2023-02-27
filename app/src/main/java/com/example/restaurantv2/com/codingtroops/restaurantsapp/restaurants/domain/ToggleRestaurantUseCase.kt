package com.example.restaurantv2.com.codingtroops.restaurantsapp.restaurants.domain

import com.example.restaurantv2.com.codingtroops.restaurantsapp.restaurants.data.RestaurantsRepository
import com.example.restaurantv2.com.codingtroops.restaurantsapp.restaurants.domain.GetSortedRestaurantsUseCase
import com.example.restaurantv2.com.codingtroops.restaurantsapp.restaurants.domain.Restaurant

class ToggleRestaurantUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()
    suspend operator fun invoke(
        id: Int,
        oldValue: Boolean
    ): List<Restaurant> {
        val newFav = oldValue.not()
        repository.toggleFavoriteRestaurant(id, newFav)
        return getSortedRestaurantsUseCase()
    }
}