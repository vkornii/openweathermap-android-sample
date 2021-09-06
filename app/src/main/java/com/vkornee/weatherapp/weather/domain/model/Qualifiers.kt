package com.vkornee.weatherapp.weather.domain.model

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Cache(val value: CacheType)

enum class CacheType {
    ROOM
}

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class DataSource(val value: SourceType)

enum class SourceType {
    OPEN_WEATHER
}