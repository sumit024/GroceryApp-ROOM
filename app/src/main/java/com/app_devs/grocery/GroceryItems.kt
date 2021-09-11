package com.app_devs.grocery

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_items")
data class GroceryItems(
    @ColumnInfo(name = "item_name")
    val item_name:String,
    @ColumnInfo(name = "item_qty")
    val item_qty:Int,
    @ColumnInfo(name = "item_price")
    val item_price:Int

){
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null
}
