package com.example.zivame_assignment.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItemToCart(item: CartEntity): Long

    @Query("SELECT * FROM cart_table")
    fun getAllItemsInCart(): List<CartEntity>

    @Query("SELECT COUNT(itemId) FROM cart_table")
    fun getItemCount(): LiveData<Int>

    @Query("DELETE FROM cart_table WHERE itemId = :id")
    fun removeFromCart(id: Int)

    @Query("SELECT SUM(price) FROM cart_table")
    fun getTotalAmount(): LiveData<Int>

    @Query("DELETE FROM cart_table")
    fun deleteAllItem()
}