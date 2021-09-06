package com.vkornee.weatherapp.common

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class CoroutineUseCase<in P, out R>(
        private val dispatcher: CoroutineDispatcher
) {
    abstract suspend fun execute(params: P): R

    suspend operator fun invoke(params: P): R = withContext(dispatcher) {
        execute(params)
    }

}

abstract class FlowCoroutineUseCase<in P, out R>(
        private val dispatcher: CoroutineDispatcher
) {
    abstract fun execute(params: P): Flow<R>

    operator fun invoke(params: P): Flow<R> =
            execute(params).flowOn(dispatcher)

}