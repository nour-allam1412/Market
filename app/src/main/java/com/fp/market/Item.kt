package com.fp.market

data class Item(val name: String,
                val price: Double,
                val imageResource: Int,
                var quantity: Int = 0)
