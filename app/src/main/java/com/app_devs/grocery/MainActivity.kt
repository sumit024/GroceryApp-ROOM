package com.app_devs.grocery

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app_devs.grocery.adapters.GroceryAdapter
import com.app_devs.grocery.databinding.ActivityMainBinding
import com.app_devs.grocery.databinding.GroceryAddDialogBinding
import com.app_devs.grocery.model.GroceryItems
import com.app_devs.grocery.viewmodels.GroceryViewModel

class MainActivity : AppCompatActivity(), GroceryAdapter.GroceryItemClickInterface {
    private lateinit var binding:ActivityMainBinding
    private lateinit var groceryAdapter: GroceryAdapter

    private lateinit var mGroceryViewModel: GroceryViewModel
    private lateinit var groceryAddDialogBinding: GroceryAddDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        groceryAdapter= GroceryAdapter(this)
        binding.rv.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=groceryAdapter
        }

        //val factory=GroceryViewModelFactory(groceryRepository)
        mGroceryViewModel=ViewModelProvider(this).get(GroceryViewModel::class.java)
        mGroceryViewModel.getAllItems.observe(this, Observer {
            groceryAdapter.list=it
            Log.d("SumitK",it.toString())
            groceryAdapter.notifyDataSetChanged()
        })
        binding.favAdd.setOnClickListener {
            openDialog()
        }

    }

    override fun onItemClick(groceryItems: GroceryItems) {
        mGroceryViewModel.delete(groceryItems)
        groceryAdapter.notifyDataSetChanged()
        Toast.makeText(this,"Item Deleted",Toast.LENGTH_SHORT).show()
    }

    private fun openDialog(){
        val dialog= Dialog(this)
        groceryAddDialogBinding= GroceryAddDialogBinding.inflate(layoutInflater)
        dialog.setContentView(groceryAddDialogBinding.root)
        groceryAddDialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        groceryAddDialogBinding.btnAdd.setOnClickListener {
            val itemName=groceryAddDialogBinding.etItemName.text.toString()
            val price=groceryAddDialogBinding.etItemPrice.text.toString()
            val qty=groceryAddDialogBinding.etItemQty.text.toString()
            val itemPrice=groceryAddDialogBinding.etItemPrice.text.toString().toInt()
            val itemQty=groceryAddDialogBinding.etItemQty.text.toString().toInt()
            if(verifyInput(itemName,qty,price)){
                val groceryItems= GroceryItems(0,itemName,itemQty,itemPrice)

                mGroceryViewModel.insert(groceryItems)
                Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show()
                Log.d("Sumit",groceryItems.toString())
                groceryAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else{
                Toast.makeText(this,"All fields are mandatory",Toast.LENGTH_SHORT).show()
            }


        }
        dialog.show()

    }
    private fun verifyInput(itemName:String,price:String,qty:String):Boolean{
        return !(TextUtils.isEmpty(itemName) && TextUtils.isEmpty(price)&& TextUtils.isEmpty(qty))
    }
}