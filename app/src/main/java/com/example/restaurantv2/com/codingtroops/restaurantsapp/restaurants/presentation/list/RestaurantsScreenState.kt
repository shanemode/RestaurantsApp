package com.example.restaurantv2.com.codingtroops.restaurantsapp.restaurants.presentation.list

import com.example.restaurantv2.com.codingtroops.restaurantsapp.restaurants.domain.Restaurant

data class RestaurantsScreenState(
    val restaurants: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
    )

