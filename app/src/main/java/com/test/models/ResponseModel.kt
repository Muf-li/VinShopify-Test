package com.test.models

import com.google.gson.annotations.SerializedName

data class ResponseModel(

    @SerializedName("httpcode")
    var httpcode: Int,

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("data")
    var data: HomeResponseModel? = null,
)