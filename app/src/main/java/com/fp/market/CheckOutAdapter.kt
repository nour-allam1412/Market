package com.fp.market

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fp.market.databinding.CheckoutCardsBinding

class CheckOutAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<CheckOutAdapter.CheckOutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckOutViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CheckoutCardsBinding.inflate(inflater, parent, false)
        return CheckOutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckOutViewHolder, position: Int) {
        val item = items[position]
        holder.binding.itemName.text = item.name
        holder.binding.itemQuantity.text = "Quantity: ${item.quantity}"
        holder.binding.itemPrice.text = "$${item.price * item.quantity}"
        holder.binding.itemImage.setImageResource(item.imageResource)
    }

    override fun getItemCount(): Int = items.size

    class CheckOutViewHolder(val binding: CheckoutCardsBinding) :
        RecyclerView.ViewHolder(binding.root)
}

