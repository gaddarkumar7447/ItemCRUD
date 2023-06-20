package com.example.itemud.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("localDB1")
data class DataItem(
    @PrimaryKey(autoGenerate = true)
    val id : Long,

    val itemName : String,
    val itemPrize : String,
    val itemOrder : String,
    val itemQuility : String
)