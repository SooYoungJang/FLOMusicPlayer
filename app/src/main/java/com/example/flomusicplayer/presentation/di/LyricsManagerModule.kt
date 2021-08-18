package com.example.flomusicplayer.presentation.di

import com.example.flomusicplayer.presentation.data.LyricsInfo
import com.example.flomusicplayer.presentation.data.LyricsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LyricsManagerModule {

    @Singleton
    @Provides
    fun provideLyricsManager() : LyricsManager {
        return LyricsManager()
    }
}