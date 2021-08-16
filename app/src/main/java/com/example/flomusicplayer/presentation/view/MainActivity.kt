package com.example.flomusicplayer.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flomusicplayer.R
import com.example.flomusicplayer.databinding.ActivityMainBinding
import com.example.flomusicplayer.presentation.viewmodel.main.MainViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        exoPlayerView.setProgressUpdateListener { position, bufferedPosition ->
            Log.d("SSs " ," test position $position and buff : $bufferedPosition")
        }
    }

    override fun onResume() {
        super.onResume()
        initExoPlayer()
    }

    private fun initExoPlayer() {
        if (mainViewModel.player == null)
            mainViewModel.player = SimpleExoPlayer.Builder(this).build()
        val player = mainViewModel.player
        exoPlayerView.player = player
        exoPlayerView.showTimeoutMs = 0
        player?.let {
            val url = mainViewModel.file ?: return // null일 경우 return으로 nothing 타입
            val mediaItem = MediaItem.fromUri(url)
            it.setMediaItem(mediaItem)
            it.prepare()
            it.seekTo(mainViewModel.currentWindow, mainViewModel.position)
//            it.playWhenReady = viewModel.playWhenReady
        }
    }
}