package com.skyscanner.mini_skyscanner.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Leg(
    @SerializedName("id")
    @Expose
    var lId : String,
    @SerializedName("departure_airport")
    @Expose
    var departureAirport : String,
    @SerializedName("arrival_airport")
    @Expose
    var arrivalAirport : String,
    @SerializedName("departure_time")
    @Expose
    var departureTime : String,
    @SerializedName("arrival_time")
    @Expose
    var arrivalTime : String,
    @SerializedName("stops")
    @Expose
    var stops : Int,
    @SerializedName("airline_name")
    @Expose
    var airlineName : String,
    @SerializedName("airline_id")
    @Expose
    var airlineId : String,
    @SerializedName("duration_mins")
    @Expose
    var durationMins : Int
)