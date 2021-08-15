package com.example.flomusicplayer.data.repository.datasourceImpl

import android.util.Log
import com.example.flomusicplayer.data.api.FLOMusicAPIService
import com.example.flomusicplayer.data.model.MusicItem
import com.example.flomusicplayer.data.repository.datasource.FLOMusicRemoteDataSource
import retrofit2.Response

class FLOMusicRemoteDataSourceImpl(private val floMusicAPIService: FLOMusicAPIService) : FLOMusicRemoteDataSource {
    override suspend fun getMusicLists(): Response<MusicItem> {
        return floMusicAPIService.getMusicList()
    }
}