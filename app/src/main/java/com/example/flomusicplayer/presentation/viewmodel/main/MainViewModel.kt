package com.example.flomusicplayer.presentation.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flomusicplayer.presentation.data.LyricsManager
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val lyricsManager: LyricsManager) : ViewModel() {
    var player : SimpleExoPlayer? = null
    var file: String? = null
    var currentWindow: Int = 0
    var position: Long = 0
    var playbackPosition: Long = 0

    val currentPosition
      get() = lyricsManager.idx

}