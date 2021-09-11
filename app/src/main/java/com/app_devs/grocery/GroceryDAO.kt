package com.app_devs.grocery

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items:GroceryItems)

    @Delete
    suspend fun delete(items:GroceryItems)

    @Query("SELECT * FROM grocery_items")
     fun readAllData():LiveData<List<GroceryItems>>
}