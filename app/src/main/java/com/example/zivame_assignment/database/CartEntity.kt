package com.example.zivame_assignment.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
class CartEntity {

    @PrimaryKey
    @NonNull
    var itemId: Int = 0

    var itemName: String = ""
    var price: String = ""
    var imageUrl: String = ""
}