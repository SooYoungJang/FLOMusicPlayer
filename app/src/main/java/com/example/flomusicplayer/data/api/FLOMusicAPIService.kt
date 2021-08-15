package com.example.flomusicplayer.data.api

import com.example.flomusicplayer.data.model.MusicItem
import retrofit2.Response
import retrofit2.http.GET

interface FLOMusicAPIService {

    @GET("/2020-flo/song.json")
    suspend fun getMusicList() : Response<MusicItem>
}