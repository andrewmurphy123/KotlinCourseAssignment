package com.example.kotlincourseassignment

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ShoppingList: ViewModel() {
    var items = mutableStateOf(listOf<Item>())

    fun addItem(item: Item) {
        var newItems = items.value.toMutableList()
        newItems.add(item)
        items.value = newItems
    }
    fun removeItem(item: Item) {
        var newItems = items.value.toMutableList()
        newItems.remove(item)
        items.value = newItems
    }
}