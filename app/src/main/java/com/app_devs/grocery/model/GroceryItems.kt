package com.app_devs.grocery.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//always make sure to have the id as integer/ long. this was the error
@Entity(tableName = "grocery_items")
data class GroceryItems(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val item_name:String,
    val item_qty:Int,
    val item_price:Int
)
