package com.example.flomusicplayer.presentation.di

import com.example.flomusicplayer.data.repository.MusicRepositoryImpl
import com.example.flomusicplayer.data.repository.datasource.FLOMusicRemoteDataSource
import com.example.flomusicplayer.domain.repository.MusicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MusicRepositoryModule {

    @Singleton
    @Provides
    fun provideMusicRepository(floMusicRemoteDataSource: FLOMusicRemoteDataSource) : MusicRepository {
        return  MusicRepositoryImpl(floMusicRemoteDataSource)
    }
}