package com.app_devs.grocery.data

import androidx.lifecycle.LiveData
import com.app_devs.grocery.model.GroceryItems

class GroceryRepository(private val dao: GroceryDAO) {


    suspend fun insert(items: GroceryItems)=dao.insert(items)
    suspend fun delete(items: GroceryItems)=dao.delete(items)
    val getAllItems:LiveData<List<GroceryItems>> = dao.readAllData()

}