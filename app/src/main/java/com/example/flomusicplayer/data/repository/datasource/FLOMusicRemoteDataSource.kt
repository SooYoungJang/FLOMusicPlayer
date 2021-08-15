package com.example.flomusicplayer.data.repository.datasource

import com.example.flomusicplayer.data.model.MusicItem
import com.example.flomusicplayer.data.util.Resource
import retrofit2.Response

interface FLOMusicRemoteDataSource {
    suspend fun getMusicLists() : Response<MusicItem>
}