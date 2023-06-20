package com.example.itemud.repo

import androidx.lifecycle.LiveData
import com.example.itemud.database.DatabaseDao
import com.example.itemud.datamodel.DataItem

class Repository(private val databaseDao: DatabaseDao) {
    suspend fun insertData(insertData : DataItem){
        databaseDao.insertData(insertData)
    }

    suspend fun deleteItem(deleteDataItem: DataItem){
        databaseDao.deleteData(deleteDataItem)
    }

    suspend fun updateData(updateDataItem: DataItem){
        databaseDao.updateData(updateDataItem)
    }

    fun getAllData() : LiveData<List<DataItem>>{
        return databaseDao.getAllData()
    }

}