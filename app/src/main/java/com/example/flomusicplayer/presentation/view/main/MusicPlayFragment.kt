package com.example.flomusicplayer.presentation.view.main

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.flomusicplayer.R
import com.example.flomusicplayer.data.util.Resource
import com.example.flomusicplayer.databinding.FragmentMusicPlayBinding
import com.example.flomusicplayer.presentation.viewmodel.main.MusicPlayFragmentViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicPlayFragment : Fragment() {

    private val musicPlayViewModel: MusicPlayFragmentViewModel by viewModels()
    private var videoPlayer: SimpleExoPlayer? = null
    private lateinit var binding: FragmentMusicPlayBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_music_play, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        musicPlayViewModel.getMusicLists()
        musicPlayViewModel.musicListsLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        Log.d("tt ", "testest 11 ${it.album} : ${it}")
                        exoPlayerInit(view.context, it.file)
                    }
                }
                is Resource.Error -> {
                    response.data?.let {
                        Toast.makeText(activity, "errorr message response", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Loading -> {

                }
            }
        })
    }

    fun exoPlayerInit(context: Context, file: String) {
        // ExoPlayer 인스턴스를 생성하고 소스를 플레이에 할당하여 비디오 플레이어 초기화
        videoPlayer = SimpleExoPlayer.Builder(context).build()
        binding.exoPlayerView?.player = videoPlayer
        buildMediaSource(context, file)?.let {
            videoPlayer?.setMediaSource(it)
            videoPlayer?.prepare()
        }
    }

    private fun buildMediaSource(context: Context, file: String): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(context, "sample")
        val mediaItem = MediaItem.fromUri(Uri.parse(file))
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaItem)
    }

    // 일시중지
    override fun onResume() {
        super.onResume()
        videoPlayer?.playWhenReady = true
    }

    // 정지
    override fun onStop() {
        super.onStop()
        videoPlayer?.playWhenReady = false
//        if (isFinishing) {
//            releasePlayer()
//        }
    }

    // 종료
    private fun releasePlayer() {
        videoPlayer?.release()
    }
}