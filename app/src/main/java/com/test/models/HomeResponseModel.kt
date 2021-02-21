package com.test.models

import com.google.gson.annotations.SerializedName

data class HomeResponseModel(

    @SerializedName("logo")
    var logo: String? = null,

    @SerializedName("banner_slider")
    var bannerSlider: ArrayList<BannerSliderModel>? = null,

    @SerializedName("featured")
    var featured: ArrayList<FeaturedProductModel>? = null,

    @SerializedName("categories")
    var categories: ArrayList<CategoryModel>? = null,

    @SerializedName("regions")
    var regions: ArrayList<RegionModel>? = null,

    @SerializedName("cartcount")
    var cartCount: Int,

    @SerializedName("cartid")
    var cartId: Int,

    @SerializedName("notify_count")
    var notifyCount: Int,

    @SerializedName("push_count")
    var pushCount: Int,
)