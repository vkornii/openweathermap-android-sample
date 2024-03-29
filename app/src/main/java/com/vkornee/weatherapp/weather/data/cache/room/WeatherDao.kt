package com.vkornee.weatherapp.weather.data.cache.room

import androidx.room.*
import com.vkornee.weatherapp.weather.data.cache.room.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: WeatherEntity)

    @Query("""
        DELETE 
        FROM ${WeatherEntity.TABLE_NAME} 
        WHERE ${WeatherEntity.CITY_COLUMN} = :city
        """)
    suspend fun removeEntitiesByCity(city: String)

    @Query("""
        SELECT * 
        FROM ${WeatherEntity.TABLE_NAME}
        WHERE ${WeatherEntity.CITY_COLUMN} = :city
    """)
    fun flowWeather(city: String): Flow<WeatherEntity>

    @Query("""
        SELECT * 
        FROM ${WeatherEntity.TABLE_NAME}
        WHERE 
            ${WeatherEntity.CITY_COLUMN} = :city
        AND
            ${WeatherEntity.ID_COLUMN} = :id
    """)
    fun flowWeatherDetails(city: String, id: Int): Flow<WeatherEntity>
}
