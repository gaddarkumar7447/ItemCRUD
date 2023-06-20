package com.example.itemud

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itemud.adapter.DataAdapter
import com.example.itemud.database.DataBaseAbstractItem
import com.example.itemud.databinding.ActivityMainBinding
import com.example.itemud.databinding.AlertdialogBinding
import com.example.itemud.datamodel.DataItem
import com.example.itemud.onclickAdapter.OnClick
import com.example.itemud.repo.Repository
import com.example.itemud.viewmodel.ViewModelDataItemFactory
import com.example.itemud.viewmodel.ViewModelItem
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnClick {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModelItem: ViewModelItem
    private lateinit var adapter : DataAdapter
    private lateinit var list: List<DataItem>
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = listOf()
        initializeViewModel()

        viewModelItem.getAllItem().observe(this, Observer {
            adapter = DataAdapter(it, this)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.setHasFixedSize(true)
            adapter.notifyDataSetChanged()
        })


        binding.addItemButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Enter Item details")
            val dialogLayout = AlertdialogBinding.inflate(LayoutInflater.from(this))
            val name  = dialogLayout.inputItemName
            val order = dialogLayout.inputItemOrder
            val prize  = dialogLayout.inputItemPrize
            val quility = dialogLayout.inputItemQuility
            builder.setView(dialogLayout.root)
            builder.setCancelable(false)
            builder.setPositiveButton("OK") { dialogInterface, i -> }
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })


            val alertDialog = builder.create()
            alertDialog.show()
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                if (name.text.isNotEmpty() && order.text.isNotEmpty() && prize.text.isNotEmpty() && quility.text.isNotEmpty()){
                    Toast.makeText(this, "Item Created", Toast.LENGTH_SHORT).show()
                    viewModelItem.insertData(DataItem(0,name.text.toString(), prize.text.toString(),order.text.toString(),quility.text.toString()))

                    alertDialog.dismiss()
                }else{
                    Toast.makeText(this, "fill all details", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initializeViewModel() {
        val databaseDao = DataBaseAbstractItem.getDatabaseInstance(this).getDataDao()
        val repo = Repository(databaseDao)
        viewModelItem = ViewModelProvider(this, ViewModelDataItemFactory(repo))[ViewModelItem::class.java]
    }

    override fun onClick(item: DataItem) {
        viewModelItem.deleteDataItem(item)
    }

    override fun updateItem(item: DataItem) {
        val name  = item.itemName
        val order = item.itemOrder
        val prize  = item.itemPrize
        val quility = item.itemQuility
        val id = item.id
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Update Item details")
        val dialogLayout = AlertdialogBinding.inflate(LayoutInflater.from(this))

        val itemNameEditText = dialogLayout.inputItemName
        val itemOrderEditText = dialogLayout.inputItemOrder
        val itemPrizeEditText = dialogLayout.inputItemPrize
        val itemQuilityEditText = dialogLayout.inputItemQuility


        itemNameEditText.setText(name)
        itemOrderEditText.setText(order)
        itemPrizeEditText.setText(prize)
        itemQuilityEditText.setText(quility)

        builder.setView(dialogLayout.root)
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialogInterface, i -> }
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })

        val alertDialog = builder.create()
        alertDialog.show()
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            if (itemNameEditText.text.isNotEmpty() && itemOrderEditText.text.isNotEmpty() && itemPrizeEditText.text.isNotEmpty() && itemQuilityEditText.text.isNotEmpty()){
                Toast.makeText(this, "Data is update", Toast.LENGTH_SHORT).show()
                viewModelItem.updateData(DataItem(id,itemNameEditText.text.toString() , itemPrizeEditText.text.toString(),itemOrderEditText.text.toString(), itemQuilityEditText.text.toString()))

                alertDialog.dismiss()
            }else{
                Toast.makeText(this, "fill all details", Toast.LENGTH_SHORT).show()
            }
        }

    }
}