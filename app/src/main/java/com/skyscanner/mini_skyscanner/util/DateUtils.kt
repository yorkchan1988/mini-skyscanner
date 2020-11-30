package com.skyscanner.mini_skyscanner.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun toSimpleString(date: Date) : String {
        val format = SimpleDateFormat("HH:mm")
        return format.format(date)
    }

    fun formatTime(dateAsString: String): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val result = simpleDateFormat.parse(dateAsString)

        return DateUtils.toSimpleString(result)
    }
}