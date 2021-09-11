package com.app_devs.grocery

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository):ViewModel() {
    fun insert(items: GroceryItems){
        GlobalScope.launch {
            repository.insert(items)
        }
    }
    fun delete(items: GroceryItems){
        GlobalScope.launch {
            repository.insert(items)
        }
    }

    fun getAllItems()=repository.getAllItems()

}