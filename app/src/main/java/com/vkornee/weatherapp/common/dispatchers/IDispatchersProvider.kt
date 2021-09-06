package com.vkornee.weatherapp.common.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatchersProvider {

    val main: CoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher
}