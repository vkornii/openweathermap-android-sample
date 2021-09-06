package com.vkornee.weatherapp

import com.vkornee.weatherapp.common.dispatchers.DispatchersProvider
import com.vkornee.weatherapp.common.dispatchers.IDispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationModule {

    @Binds
    fun provideDispatchersProvider(dispatchersProvider: DispatchersProvider): IDispatchersProvider
}