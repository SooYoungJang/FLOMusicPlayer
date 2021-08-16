package com.example.flomusicplayer.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.SimpleExoPlayer

class MainViewModel : ViewModel() {
    var player : SimpleExoPlayer? = null
    var file: String? = null
    var currentWindow: Int = 0
    var position: Long = 0

}