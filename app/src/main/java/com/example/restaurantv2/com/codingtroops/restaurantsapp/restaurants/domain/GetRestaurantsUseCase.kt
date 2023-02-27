package com.example.restaurantv2.com.codingtroops.restaurantsapp

import com.example.restaurantv2.com.codingtroops.restaurantsapp.restaurants.data.RestaurantsRepository
import com.example.restaurantv2.com.codingtroops.restaurantsapp.restaurants.domain.GetSortedRestaurantsUseCase
import com.example.restaurantv2.com.codingtroops.restaurantsapp.restaurants.domain.Restaurant

class GetInitialRestaurantsUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()
    suspend operator fun invoke(): List<Restaurant> {
        repository.loadRestaurants()
        return getSortedRestaurantsUseCase()
    }
}