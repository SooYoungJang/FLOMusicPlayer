package com.example.flomusicplayer.presentation.di

import com.example.flomusicplayer.data.api.FLOMusicAPIService
import com.example.flomusicplayer.data.repository.datasource.FLOMusicRemoteDataSource
import com.example.flomusicplayer.data.repository.datasourceImpl.FLOMusicRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideMusicRemoteDataSource(floMusicAPIService: FLOMusicAPIService) : FLOMusicRemoteDataSource {
        return FLOMusicRemoteDataSourceImpl(floMusicAPIService)
    }

}