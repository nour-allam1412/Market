package com.fp.market

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fp.market.databinding.ItemCardsBinding

class MenuAdapter(private var itemsList: List<Item>,
                  private val viewModel: SharedViewModel)
    : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){

    inner class MenuViewHolder(private val binding: ItemCardsBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            binding.itemName.text = item.name
            binding.itemPrice.text = "$${item.price}"
            binding.itemImage.setImageResource(item.imageResource)
            binding.itemQuantity.text = "Quantity: ${item.quantity}"

            binding.btnAdd.setOnClickListener {
                viewModel.addItem(item)
            }

            viewModel.selectedItems.observeForever { selectedItems ->
                val currentItem = selectedItems.find { it.name == item.name }
                currentItem?.let {
                    binding.itemQuantity.text = "Quantity: ${it.quantity}"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}