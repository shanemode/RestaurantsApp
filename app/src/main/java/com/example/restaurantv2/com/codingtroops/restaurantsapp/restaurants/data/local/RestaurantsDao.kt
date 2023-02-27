package com.example.restaurantv2.com.codingtroops.restaurantsapp.restaurants.data.local


import androidx.room.*
import com.example.restaurantv2.com.codingtroops.restaurantsapp.PartialLocalRestaurant


@Dao
interface RestaurantsDao {
    @Query("SELECT * FROM restaurants")
    suspend fun getAll(): List<LocalRestaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(restaurants: List<LocalRestaurant>)

    @Update(entity = LocalRestaurant::class)
    suspend fun update(partialRestaurant: PartialLocalRestaurant)

    @Update(entity = LocalRestaurant::class)
    suspend fun updateAll(partialRestaurants: List<PartialLocalRestaurant>)

    @Query("SELECT * FROM restaurants WHERE is_favorite = 1")
    suspend fun getAllFavorited(): List<LocalRestaurant>

}