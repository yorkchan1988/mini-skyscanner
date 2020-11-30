package com.skyscanner.mini_skyscanner.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Itinerary(
    @SerializedName("id")
    @Expose
    var itId: String,
    @SerializedName("legs")
    @Expose
    var legs: List<String>,
    @SerializedName("price")
    @Expose
    var price: String,
    @SerializedName("agent")
    @Expose
    var agent: String
)