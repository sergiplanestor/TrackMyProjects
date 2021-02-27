package com.revolhope.data.common.base

import com.revolhope.domain.common.model.State

abstract class BaseRepositoryImpl {

    protected suspend fun <T> statefulCall(call: suspend () -> T): State<T> {
        return try {
            State.Success(call.invoke())
        } catch (e: Throwable) {
            // TODO: Error management
            State.Error(e.message, e)
        }
    }
}