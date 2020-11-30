package com.skyscanner.mini_skyscanner.network

import com.skyscanner.mini_skyscanner.models.response.ErrorResponse

sealed class ApiResource<T>(
    val status: ApiStatus? = null,
    val data: T? = null,
    val error: ErrorResponse? = null
) {
    class Success<T>(data: T) : ApiResource<T>(ApiStatus.SUCCESS, data)
    class Loading<T>() : ApiResource<T>(ApiStatus.LOADING)
    class Error<T>(data: T? = null, error: ErrorResponse?) : ApiResource<T>(ApiStatus.ERROR, data, error)

    enum class ApiStatus {SUCCESS, ERROR, LOADING}
}