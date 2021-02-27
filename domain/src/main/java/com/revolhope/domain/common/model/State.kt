package com.revolhope.domain.common.model

sealed class State<T> {

    data class Success<T>(val data: T) : State<T>()
    data class Error<T>(
        val message: String?,
        val throwable: Throwable?
    ) : State<T>()
}