package com.example.flomusicplayer.data.repository

import android.util.Log
import com.example.flomusicplayer.data.model.MusicItem
import com.example.flomusicplayer.data.repository.datasource.FLOMusicRemoteDataSource
import com.example.flomusicplayer.data.util.Resource
import com.example.flomusicplayer.domain.repository.MusicRepository
import retrofit2.Response

class MusicRepositoryImpl(private val floMusicRemoteDataSource: FLOMusicRemoteDataSource) : MusicRepository {

    override suspend fun getMusicLists(): Resource<MusicItem> {
        return responseToResource(floMusicRemoteDataSource.getMusicLists())
    }

    private fun responseToResource(resource: Response<MusicItem>): Resource<MusicItem> {
        if (resource.isSuccessful) {
            resource.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(resource.message())
    }
}