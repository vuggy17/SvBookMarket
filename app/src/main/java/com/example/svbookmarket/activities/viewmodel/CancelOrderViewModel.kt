package com.example.svbookmarket.activities.viewmodel

import androidx.lifecycle.ViewModel
import com.example.svbookmarket.activities.data.BookRepository
import com.example.svbookmarket.activities.data.OrderRepository
import com.example.svbookmarket.activities.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CancelOrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val bookRepository: BookRepository
) :
    ViewModel() {
        fun updateData(order: Order, reason: String){
            orderRepository.updateOrderStatus(orderId = order.id)
            orderRepository.updateReason(orderId = order.id, reason)
            for(book in order.listbooks){
                for(i in 0 until book.numbers){
                    bookRepository.updateBookCount(bookId = book.id)
                }
            }
        }
}