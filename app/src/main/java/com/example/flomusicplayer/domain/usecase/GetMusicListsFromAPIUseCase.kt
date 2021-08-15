package com.example.flomusicplayer.domain.usecase

import android.util.Log
import com.example.flomusicplayer.data.model.MusicItem
import com.example.flomusicplayer.data.util.Resource
import com.example.flomusicplayer.domain.repository.MusicRepository
import retrofit2.Response

class GetMusicListsFromAPIUseCase(private val musicRepository: MusicRepository) {

    suspend fun execute() : Resource<MusicItem> {
        return musicRepository.getMusicLists()
    }
}