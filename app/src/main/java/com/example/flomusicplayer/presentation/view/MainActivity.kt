package com.example.flomusicplayer.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flomusicplayer.R
import com.example.flomusicplayer.databinding.ActivityMainBinding
import com.example.flomusicplayer.presentation.data.LyricsManager
import com.example.flomusicplayer.presentation.view.lyrics.LyricsFragment
import com.example.flomusicplayer.presentation.view.musicplay.MusicPlayFragment
import com.example.flomusicplayer.presentation.viewmodel.main.MainViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity :  AppCompatActivity(), MusicPlayFragment.OnMusicInfoReceivedListener {
    private lateinit var dataBinding: ActivityMainBinding
    val mainViewModel: MainViewModel by viewModels()
    @Inject lateinit var lyricsManager: LyricsManager
    val musicFragment: MusicPlayFragment by lazy {
        supportFragmentManager.fragments[0].childFragmentManager.fragments[0] as MusicPlayFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        exoPlayerView.setProgressUpdateListener { position, bufferedPosition ->
            val lyricsInfo = lyricsManager.get(position)
            lyricsInfo.first?.content?.let { musicFragment.musicPlayViewModel.setCurLyrics(it) }
            lyricsInfo.second?.content?.let { musicFragment.musicPlayViewModel.setNextLyrics(it) }
        }
    }


    override fun onResume() {
        super.onResume()
        initExoPlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
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

    override fun onMusicInFoReceived(file: String) {
        mainViewModel.player?.let {
            val url = file ?: return
            val mediaItem = MediaItem.fromUri(url)
            it.setMediaItem(mediaItem)
            it.prepare()
            it.seekTo(mainViewModel.currentWindow, mainViewModel.position)
        }
    }
    private fun releasePlayer() {
        mainViewModel.apply {
            player?.let {
                playbackPosition = it.currentPosition
                currentWindow = it.currentWindowIndex
                it.release()
                player = null
            }
        }
    }
}