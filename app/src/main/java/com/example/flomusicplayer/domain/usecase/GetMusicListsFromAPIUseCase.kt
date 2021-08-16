package com.example.flomusicplayer.domain.usecase

import com.example.flomusicplayer.data.model.MusicItem
import com.example.flomusicplayer.data.util.Resource
import com.example.flomusicplayer.domain.repository.MusicRepository

class GetMusicListsFromAPIUseCase(private val musicRepository: MusicRepository) {

    suspend fun execute() : Resource<MusicItem> {
        return musicRepository.getMusicLists()
    }
}