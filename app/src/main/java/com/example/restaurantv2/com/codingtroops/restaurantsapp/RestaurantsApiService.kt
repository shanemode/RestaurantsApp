package com.example.restaurantv2.com.codingtroops.restaurantsapp

import com.example.restaurantv2.com.codingtroops.restaurantsapp.Restaurant
import retrofit2.http.GET
import retrofit2.http.Query


interface RestaurantsApiService {
    @GET("restaurants.json")
    suspend fun getRestaurants(): List<Restaurant>

    @GET("restaurants.json?orderBy=\"r_id\"")
    suspend fun getRestaurant(@Query("equalTo") id: Int): Map<String, Restaurant>
}