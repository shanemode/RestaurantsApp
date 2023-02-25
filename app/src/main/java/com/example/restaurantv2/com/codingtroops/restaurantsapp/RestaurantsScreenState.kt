package com.example.restaurantv2.com.codingtroops.restaurantsapp

data class RestaurantsScreenState(
    val restaurants: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
    )

