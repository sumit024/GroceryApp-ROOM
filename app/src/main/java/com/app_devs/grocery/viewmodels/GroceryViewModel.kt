package com.app_devs.grocery.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app_devs.grocery.model.GroceryItems
import com.app_devs.grocery.data.GroceryDatabase
import com.app_devs.grocery.data.GroceryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application): AndroidViewModel(application) {
    private val repository: GroceryRepository
    val getAllItems:LiveData<List<GroceryItems>>
    init {
        val dao= GroceryDatabase.getDatabase(application).getGroceryDao()
        repository= GroceryRepository(dao)
        getAllItems=repository.getAllItems
    }
    fun insert(items: GroceryItems){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(items)
        }
    }
    fun delete(items: GroceryItems){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(items)
        }
    }

}