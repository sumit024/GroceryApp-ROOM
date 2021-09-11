package com.app_devs.grocery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app_devs.grocery.model.GroceryItems
import com.app_devs.grocery.databinding.GroceryItemRowBinding

class GroceryAdapter(private val groceryItemClickInterface: GroceryItemClickInterface)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list= emptyList<GroceryItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(GroceryItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder){
            holder.binding.tvItemName.text=list[position].item_name
            holder.binding.tvItemPrice.text= "Rs. "+list[position].item_price.toString()
            holder.binding.tvItemQty.text=list[position].item_qty.toString()
            val itemTotal:Int=list[position].item_price*list[position].item_qty
            holder.binding.tvTotalAmount.text="Rs. "+itemTotal.toString()
            holder.binding.ivDelete.setOnClickListener {
                groceryItemClickInterface.onItemClick(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(val binding: GroceryItemRowBinding):RecyclerView.ViewHolder(binding.root)

    interface GroceryItemClickInterface{
        fun onItemClick(groceryItems: GroceryItems)
    }

}