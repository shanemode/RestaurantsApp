package com.example.restaurantv2.com.codingtroops.restaurantsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantv2.com.codingtroops.restaurantsapp.PartialRestaurant
import com.example.restaurantv2.com.codingtroops.restaurantsapp.Restaurant
import com.example.restaurantv2.com.codingtroops.restaurantsapp.RestaurantsApiService
import com.example.restaurantv2.com.codingtroops.restaurantsapp.RestaurantsDb
import com.example.restaurantv2.com.codingtroops.restaurantsapp.RestaurantsApplication
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException


class RestaurantsViewModel() : ViewModel() {
    private val getRestaurantsUseCase = GetInitialRestaurantsUseCase()
    private val toggleRestaurantsUseCase = ToggleRestaurantUseCase()
    private val _state = mutableStateOf(RestaurantsScreenState(restaurants = listOf(), isLoading=true))
    val state: State<RestaurantsScreenState> get() = _state
    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(error = exception.message, isLoading = false)
    }

    init {
        getRestaurants()
    }

    fun toggleFavorite(id: Int, oldValue: Boolean) {
        viewModelScope.launch(errorHandler) {
            val updatedRestaurants = toggleRestaurantsUseCase(id, oldValue)
            _state.value = _state.value.copy(restaurants = updatedRestaurants)
        }
    }

    private fun getRestaurants() {
        viewModelScope.launch(errorHandler) {
            val restaurants = getRestaurantsUseCase()
            _state.value = _state.value.copy(restaurants = restaurants, isLoading = false)
        }
    }


}