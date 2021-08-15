package com.example.flomusicplayer.presentation.di

import com.example.flomusicplayer.domain.repository.MusicRepository
import com.example.flomusicplayer.domain.usecase.GetMusicListsFromAPIUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetMusicListsFromAPIUseCase(musicRepository: MusicRepository) : GetMusicListsFromAPIUseCase {
        return GetMusicListsFromAPIUseCase(musicRepository)
    }
}