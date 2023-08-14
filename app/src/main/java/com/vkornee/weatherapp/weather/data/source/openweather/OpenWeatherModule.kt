package com.vkornee.weatherapp.weather.data.source.openweather

import com.vkornee.weatherapp.BuildConfig
import com.vkornee.weatherapp.weather.data.source.openweather.api.IOpenWeatherMapApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenWeatherModule {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(loggingInterceptor)
                }
            }
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) //todo use kotlin serialization
            .build()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): IOpenWeatherMapApi =
        retrofit.create(IOpenWeatherMapApi::class.java)
}

