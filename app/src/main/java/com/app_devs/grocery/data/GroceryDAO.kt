package com.app_devs.grocery.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app_devs.grocery.model.GroceryItems

@Dao
interface GroceryDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(items: GroceryItems)

    @Delete
    suspend fun delete(items: GroceryItems)

    @Query("SELECT * FROM grocery_items ORDER BY id ASC")
    fun readAllData():LiveData<List<GroceryItems>>
}