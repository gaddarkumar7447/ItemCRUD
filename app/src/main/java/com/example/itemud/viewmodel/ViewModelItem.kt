package com.example.itemud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itemud.datamodel.DataItem
import com.example.itemud.repo.Repository
import kotlinx.coroutines.launch

class ViewModelItem(private val repository: Repository) : ViewModel() {

    fun getAllItem() : LiveData<List<DataItem>> {
        return repository.getAllData()
    }

    fun insertData(insertDataItem: DataItem){
        viewModelScope.launch {
            repository.insertData(insertDataItem)
        }
    }

    fun updateData(updateDataItem: DataItem){
        viewModelScope.launch {
            repository.updateData(updateDataItem)
        }
    }

    fun deleteDataItem(deleteDataItem : DataItem){
        viewModelScope.launch {
            repository.deleteItem(deleteDataItem)
        }
    }
}