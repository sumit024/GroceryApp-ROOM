package com.app_devs.grocery.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app_devs.grocery.model.GroceryItems

@Database(entities = [GroceryItems::class],version = 1,exportSchema = false)
abstract class GroceryDatabase:RoomDatabase() {

    abstract fun getGroceryDao(): GroceryDAO

    companion object{
        @Volatile
        private var instance: GroceryDatabase?=null
        fun getDatabase(context: Context): GroceryDatabase {
            val temp= instance
            if(temp!=null)
                return temp
            synchronized(this){
                return Room.databaseBuilder(context.applicationContext, GroceryDatabase::class.java,"grocery_db").build()
            }

        }

    }
}