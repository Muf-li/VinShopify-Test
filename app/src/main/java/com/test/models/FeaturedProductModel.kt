package com.test.models

import com.google.gson.annotations.SerializedName

data class FeaturedProductModel(

    @SerializedName("prd_id")
    var prdId: String? = null,

    @SerializedName("prd_name")
    var prdName: String? = null,

    @SerializedName("prd_image")
    var prdImage: String? = null,

    @SerializedName("qty")
    var qty: String? = null,

    @SerializedName("unit")
    var unit: String? = null,

    @SerializedName("current_stock")
    var currentStock: String? = null,

    @SerializedName("price")
    var price: String? = null,

    @SerializedName("currency")
    var currency: String? = null,

    @SerializedName("rating")
    var rating: String? = null,
)