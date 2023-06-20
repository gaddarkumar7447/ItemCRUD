package com.example.itemud.onclickAdapter

import com.example.itemud.datamodel.DataItem

interface OnClick {
    fun onClick(item: DataItem)

    fun updateItem(item: DataItem)
}