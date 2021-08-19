package com.example.flomusicplayer.presentation.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flomusicplayer.presentation.data.LyricsManager
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MainViewModel : ViewModel() {
    var player : SimpleExoPlayer? = null
    var file: String? = null
    var currentWindow: Int = 0
    private val _position = MutableLiveData<Long>()
    val position : LiveData<Long>
        get() = _position

    fun setPosition(position: Long) {
        _position.value = position
    }

    var playbackPosition: Long = 0

}