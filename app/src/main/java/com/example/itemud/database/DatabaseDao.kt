package com.example.itemud.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.itemud.datamodel.DataItem

@Dao
interface DatabaseDao {
    @Insert
    suspend fun insertData(dataItem : DataItem)

    @Update
    suspend fun updateData(dataItem: DataItem)

    @Delete
    suspend fun deleteData(dataItem: DataItem)

    @Query("SELECT * FROM localDB1")
    fun getAllData() : LiveData<List<DataItem>>
}