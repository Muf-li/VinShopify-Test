package com.test.models

import com.google.gson.annotations.SerializedName

data class BannerSliderModel(

    @SerializedName("slider_id")
    var sliderId: String? = null,

    @SerializedName("slider_name")
    var sliderName: String? = null,

    @SerializedName("slider_image")
    var sliderImage: String? = null
)