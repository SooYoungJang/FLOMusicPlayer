package com.example.flomusicplayer.presentation.view.musicplay

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.flomusicplayer.R
import com.example.flomusicplayer.databinding.FragmentMusicPlayBinding
import com.example.flomusicplayer.presentation.viewmodel.musicplay.MusicPlayFragmentViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicPlayFragment : Fragment() {

    val musicPlayViewModel: MusicPlayFragmentViewModel by viewModels()
    private lateinit var binding: FragmentMusicPlayBinding
    lateinit var mListener: OnMusicInfoReceivedListener

    interface OnMusicInfoReceivedListener {
        fun onMusicInFoReceived(file: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMusicInfoReceivedListener) {
            mListener = context as OnMusicInfoReceivedListener
        } else {
            throw ClassCastException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_music_play, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = musicPlayViewModel
        return binding.root
    }
    fun changeTextView(string: String){
        Log.d("sdfs", "FFF $string")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        musicPlayViewModel.getMusicLists()

        musicPlayViewModel.file.observe(viewLifecycleOwner, Observer {
            mListener.onMusicInFoReceived(it)
        })

        binding.lyricsLayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_musicPlayFragment_to_lyricsFragment)
        }
    }



    private fun buildMediaSource(context: Context, file: String): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(context, "sample")
        val mediaItem = MediaItem.fromUri(Uri.parse(file))
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaItem)
    }

//    private fun videoPlayerListener() {
//        videoPlayer?.addAnalyticsListener(object : AnalyticsListener {
//            override fun onPositionDiscontinuity(
//                eventTime: AnalyticsListener.EventTime,
//                oldPosition: Player.PositionInfo,
//                newPosition: Player.PositionInfo,
//                reason: Int
//            ) {
//                super.onPositionDiscontinuity(eventTime, oldPosition, newPosition, reason)
//                Log.d("TTT", "sss? ${oldPosition.contentPositionMs} : SSSS ${newPosition.contentPositionMs}")
//
//            }
//        })
//    }
}