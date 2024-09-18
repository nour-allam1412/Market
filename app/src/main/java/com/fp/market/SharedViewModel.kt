package com.fp.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _selectedItems = MutableLiveData<MutableList<Item>>()
    val selectedItems: LiveData<MutableList<Item>> = _selectedItems

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> = _totalPrice

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    init {
        _selectedItems.value = mutableListOf()
        _totalPrice.value = 0.0

        _items.value = listOf(
            Item("Sandwich 1", 100.0, R.mipmap.sandwish1),
            Item("Sandwich 2", 150.0, R.mipmap.sandwish1),
            Item("Sandwich 3", 80.0, R.mipmap.sandwish1),
            Item("Sandwich 4", 90.0, R.mipmap.sandwish1),
            Item("Sandwich 5", 110.0, R.mipmap.sandwish1),
            Item("Sandwich 6", 120.0, R.mipmap.sandwish1),
            Item("Sandwich 7", 105.0, R.mipmap.sandwish1),
            Item("Sandwich 8", 140.0, R.mipmap.sandwish1),
            )
    }

    fun addItem(item: Item) {
        val currentList = _selectedItems.value ?: mutableListOf()
        val existingItem = currentList.find { it.name == item.name }

        if (existingItem != null) {
            // If the item already exists, increase the quantity
            increaseQuantity(existingItem)
            calculateTotalPrice()
        } else {
            // If the item is new, set its initial quantity to 1 and add it
            currentList.add(item.copy(quantity = 1))
            _selectedItems.value = currentList
            calculateTotalPrice()
        }
    }

    fun removeItem(item: Item) {
        _selectedItems.value?.remove(item)
        calculateTotalPrice()
    }

    fun increaseQuantity(item: Item) {
        val currentList = _selectedItems.value ?: return
        val index = currentList.indexOfFirst { it.name == item.name }

        if (index != -1) {
            // Increase the quantity of the existing item
            currentList[index] = currentList[index].copy(quantity = currentList[index].quantity + 1)

            //currentList[index].quantity += 1
            // Update LiveData to trigger observers
            _selectedItems.value = currentList.toMutableList()
        }
    }

    private fun calculateTotalPrice() {
        _totalPrice.value = _selectedItems.value?.sumOf { it.price * it.quantity } ?: 0.0
    }
}