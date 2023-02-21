package com.example.restaurantv2.com.codingtroops.restaurantsapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.codingtroops.restaurantsapp.Restaurant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantsViewModel(
    private val stateHandle: SavedStateHandle):
    ViewModel(){
    private var restInterface: RestaurantsApiService
    val state = mutableStateOf(emptyList<Restaurant>())
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(
                "https://restaurant-4521d-default-rtdb.firebaseio.com/"
            )
            .build()
        restInterface = retrofit.create(
            RestaurantsApiService::class.java
        )
    }
    fun toggleFavorite(id: Int){
        val restaurants = state.value.toMutableList()
        val itemIndex =
            restaurants.indexOfFirst { it.id ==id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] =
            item.copy(isFavorite = !item.isFavorite)
        storeSelection(restaurants[itemIndex])
        state.value = restaurants
    }

    private fun storeSelection(item: Restaurant) {
        val savedToggle = stateHandle
            .get<List<Int>?>(FAVORITES)
            .orEmpty().toMutableList()
        if (item.isFavorite) savedToggle.add(item.id)
        else savedToggle.remove(item.id)
        stateHandle[FAVORITES] = savedToggle
    }
    companion object {
        const val FAVORITES = "favorites"
    }

    private fun List<Restaurant>.restoreSelections():
            List<Restaurant> {
        stateHandle.get<List<Int>?>(FAVORITES)?.let {
                selectedIds ->
            val restaurantsMap = this.associateBy { it.id }
            selectedIds.forEach { id ->
                restaurantsMap[id]?.isFavorite = true
            }
            return restaurantsMap.values.toList()
        }
        return this
    }

    fun getRestaurants() {
        restInterface.getRestaurants().enqueue(
            object : Callback<List<Restaurant>> {
                override fun onResponse(
                    call: Call<List<Restaurant>>,
                    response: Response<List<Restaurant>>
                ) {
                    response.body()?.let { restaurants ->
                        state.value =
                            restaurants.restoreSelections()
                    }
                }
                override fun onFailure(
                    call: Call<List<Restaurant>>, t: Throwable
                ) {
                    t.printStackTrace()
                }
            })
    }}

