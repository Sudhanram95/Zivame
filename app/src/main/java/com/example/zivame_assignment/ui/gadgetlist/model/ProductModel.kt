package com.example.zivame_assignment.ui.gadgetlist.model

import com.google.gson.annotations.SerializedName

class ProductModel {

    @SerializedName("name")
    var name: String = ""

    @SerializedName("price")
    var price: String = ""

    @SerializedName("image_url")
    var imageUrl: String = ""

    @SerializedName("rating")
    var rating: Float = 0.0f
}