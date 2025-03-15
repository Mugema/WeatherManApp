package com.example.weatherman.di

import com.example.weatherman.data.repository.WeatherRepositoryImplementation
import com.example.weatherman.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface BindsModule {

    @Binds
    fun BindsWeatherRepository(weatherRepositoryImplementation: WeatherRepositoryImplementation ):WeatherRepository
}