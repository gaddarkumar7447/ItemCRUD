package com.example.itemud.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.itemud.datamodel.DataItem

@Database(entities = [DataItem::class], version = 1)
abstract class DataBaseAbstractItem : RoomDatabase(){
    abstract fun getDataDao() : DatabaseDao

    companion object {
        private var INSTANCE: DataBaseAbstractItem? = null
        fun getDatabaseInstance(context: Context): DataBaseAbstractItem {
            synchronized(this) {
                if (INSTANCE == null) {
                    val instance = Room.databaseBuilder(context, DataBaseAbstractItem::class.java, "localDB1").build()
                    INSTANCE = instance
                }
            }
            return INSTANCE!!
        }
    }
}