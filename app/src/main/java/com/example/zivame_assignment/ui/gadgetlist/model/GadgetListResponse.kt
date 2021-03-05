package com.example.zivame_assignment.ui.gadgetlist.model

import com.google.gson.annotations.SerializedName

class GadgetListResponse {

    @SerializedName("products")
    var productList: List<ProductModel>? = null
}