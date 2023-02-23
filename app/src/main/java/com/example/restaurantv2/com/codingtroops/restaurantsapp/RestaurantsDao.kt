package com.example.restaurantv2.com.codingtroops.restaurantsapp


import androidx.room.*
import com.example.restaurantv2.com.codingtroops.restaurantsapp.PartialRestaurant
import com.example.restaurantv2.com.codingtroops.restaurantsapp.Restaurant


@Dao
interface RestaurantsDao {
    @Query("SELECT * FROM restaurants")
    suspend fun getAll(): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(restaurants: List<Restaurant>)

    @Update(entity = Restaurant::class)
    suspend fun update(partialRestaurant: PartialRestaurant)

    @Update(entity = Restaurant::class)
    suspend fun updateAll(partialRestaurants: List<PartialRestaurant>)

    @Query("SELECT * FROM restaurants WHERE is_favorite = 1")
    suspend fun getAllFavorited(): List<Restaurant>

}