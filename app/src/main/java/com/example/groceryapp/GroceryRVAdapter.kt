package com.example.groceryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryRVAdapter(
    var list: List<GroceryItems>,
    val groceryItemClickInterface: GroceryItemClickInterface
    ):RecyclerView.Adapter<GroceryRVAdapter.GroceryViewMolder>() {

    inner class GroceryViewMolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTV = itemView.findViewById<TextView>(R.id.idTvQuantity)
        val amountTV = itemView.findViewById<TextView>(R.id.idTVTotalAmt)
        val rateTV = itemView.findViewById<TextView>(R.id.idTvRate)
        val deleteTV = itemView.findViewById<ImageView>(R.id.idTVDelete)

    }

    interface GroceryItemClickInterface{
        fun onItemCLick(groceryItems: GroceryItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewMolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_rv_item, parent, false)
//            .inflate(R.layout.grocery_rv_item.parent, false)
        return GroceryViewMolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewMolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.quantityTV.text = list.get(position).itemQuantity.toString()
        holder.rateTV.text = "₦ " + list.get(position).itemPrice.toString()
        val itemTotal: Int = list.get(position).itemPrice * list.get(position).itemQuantity
        holder.amountTV.text = "₦ " + itemTotal.toString()
        holder.deleteTV.setOnClickListener{
            groceryItemClickInterface.onItemCLick(list.get(position))
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}
