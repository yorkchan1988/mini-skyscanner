package com.skyscanner.mini_skyscanner.network

import java.lang.Exception

sealed class ApiResource<T>(
    val status: ApiStatus? = null,
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : ApiResource<T>(ApiStatus.SUCCESS, data)
    class Loading<T>() : ApiResource<T>(ApiStatus.LOADING)
    class Error<T>(data: T? = null, error: Throwable) : ApiResource<T>(ApiStatus.ERROR, data, error)

    enum class ApiStatus {SUCCESS, ERROR, LOADING}
}