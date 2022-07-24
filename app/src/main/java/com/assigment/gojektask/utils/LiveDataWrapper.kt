package com.assigment.gojektask.utils

class LiveDataWrapper <T>(
    val responseStatus: RESULT,
    val response: T? = null,
    val error: Throwable? = null
) {

    enum class RESULT {
        SUCCESS, LOADING, ERROR
    }

    companion object {
        fun <T> loading() = LiveDataWrapper<T>(RESULT.LOADING)
        fun <T> success (data: T) = LiveDataWrapper<T>(RESULT.SUCCESS, data)
        fun <T> error(err: Throwable) = LiveDataWrapper<T>(RESULT.ERROR, null, err)
    }
}