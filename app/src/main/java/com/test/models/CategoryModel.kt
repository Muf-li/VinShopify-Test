package com.test.models

import com.google.gson.annotations.SerializedName

data class CategoryModel(

    @SerializedName("category_id")
    var categoryId: String? = null,

    @SerializedName("category_name")
    var categoryName: String? = null,

    @SerializedName("category_image")
    var categoryImage: String? = null
)