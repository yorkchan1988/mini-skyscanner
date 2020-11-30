package com.skyscanner.mini_skyscanner.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FlightsApiResponse(
    @SerializedName("itineraries")
    @Expose
    var itineraries: List<Itinerary>,
    @SerializedName("legs")
    @Expose
    var legs: List<Leg>
)