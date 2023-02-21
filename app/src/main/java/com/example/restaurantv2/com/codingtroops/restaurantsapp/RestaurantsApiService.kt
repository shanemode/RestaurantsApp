package com.example.restaurantv2.com.codingtroops.restaurantsapp

import com.codingtroops.restaurantsapp.Restaurant
import retrofit2.Call
import retrofit2.http.GET

interface RestaurantsApiService {
    @GET("restaurants.json")
    fun getRestaurants(): Call<List<Restaurant>>
}