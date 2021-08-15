package com.example.flomusicplayer.presentation.viewmodel.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flomusicplayer.data.model.MusicItem
import com.example.flomusicplayer.data.util.Resource
import com.example.flomusicplayer.domain.usecase.GetMusicListsFromAPIUseCase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicPlayFragmentViewModel @Inject constructor (private val getMusicListsFromAPIUseCase: GetMusicListsFromAPIUseCase) : ViewModel() {
    val musicListsLiveData = MutableLiveData<Resource<MusicItem>>()

    fun getMusicLists() = viewModelScope.launch(Dispatchers.IO) {
        musicListsLiveData.postValue(Resource.Loading())
        try {
            val resource  = getMusicListsFromAPIUseCase.execute()
            musicListsLiveData.postValue(resource)
        }catch (e: Exception) {
            e.message?.let {
                musicListsLiveData.postValue(Resource.Error(it))
            }
        }
    }
}