package com.test.models

import com.google.gson.annotations.SerializedName

data class RegionModel(

    @SerializedName("country_id")
    var countryId: String? = null,

    @SerializedName("country_code")
    var countryCode: String? = null,

    @SerializedName("country_name")
    var countryName: String? = null
)