package com.example.itemud.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itemud.databinding.ItemcardBinding
import com.example.itemud.datamodel.DataItem
import com.example.itemud.onclickAdapter.OnClick

class DataAdapter(private val listDataItem : List<DataItem>, private val onClick: OnClick) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(ItemcardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listDataItem.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = listDataItem[position]
        holder.itemBinding.apply {
            itemName.text = "Name: ${item.itemName}"
            itemPrize.text = "Prize: ${item.itemPrize}"
            itemOderNo.text = "Item Oder No.: ${item.itemOrder}"
            itemQuility.text = "Item Quility: ${item.itemQuility}"
        }

        holder.itemBinding.deleteItem.setOnClickListener {
            onClick.onClick(item)
        }
        holder.itemBinding.itemCard.setOnClickListener {
            onClick.updateItem(item)
        }
    }

    class DataViewHolder(val itemBinding : ItemcardBinding) : RecyclerView.ViewHolder(itemBinding.root)
}