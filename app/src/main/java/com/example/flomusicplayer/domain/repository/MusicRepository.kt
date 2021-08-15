package com.example.flomusicplayer.domain.repository

import com.example.flomusicplayer.data.model.MusicItem
import com.example.flomusicplayer.data.util.Resource

interface MusicRepository {

    suspend fun getMusicLists() : Resource<MusicItem>

}