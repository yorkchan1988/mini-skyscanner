package com.skyscanner.mini_skyscanner.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Itinerary(
    @SerializedName("id")
    @Expose
    var itId: String,
    @SerializedName("legs")
    @Expose
    var legIds: List<String>,
    @SerializedName("price")
    @Expose
    var price: String,
    @SerializedName("agent")
    @Expose
    var agent: String,
    @Expose(serialize = false, deserialize = false)
    var legs: List<Leg?> = listOf()
)