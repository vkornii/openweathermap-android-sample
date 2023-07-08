package com.vkornee.weatherapp.common

abstract class BaseMapper<From, To> {
    abstract fun map(from: From): To
}
