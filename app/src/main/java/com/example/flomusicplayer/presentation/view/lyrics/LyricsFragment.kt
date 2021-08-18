package com.example.flomusicplayer.presentation.view.lyrics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flomusicplayer.R
import com.example.flomusicplayer.databinding.FragmentLyricsBinding
import com.example.flomusicplayer.presentation.adapter.lyrics.LyricsRcvAdapter
import com.example.flomusicplayer.presentation.data.LyricsManager
import com.example.flomusicplayer.presentation.view.MainActivity
import com.example.flomusicplayer.presentation.view.musicplay.MusicPlayFragment
import com.example.flomusicplayer.presentation.viewmodel.main.MainViewModel
import javax.inject.Inject

class LyricsFragment : Fragment() {
    private lateinit var binding: FragmentLyricsBinding
    private lateinit var lyricsAdapter: LyricsRcvAdapter
    private lateinit var lyricsManager: LyricsManager
    private lateinit var mainViewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lyrics, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = (requireActivity() as MainActivity).musicFragment.musicPlayViewModel
        lyricsManager = (requireActivity() as MainActivity).lyricsManager
        mainViewModel =  (requireActivity() as MainActivity).mainViewModel
//        binding.viewModel  = (requireActivity() as MainActivity).searchFragment.musicPlayViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitBtnClick()
        initRecyclerView()
        viewLyricsList()
    }

    private fun initRecyclerView() {
        lyricsAdapter = LyricsRcvAdapter()
        binding.lyricsRcv.adapter = lyricsAdapter
        binding.lyricsRcv.layoutManager = LinearLayoutManager(activity)

        (binding.lyricsRcv.layoutManager as LinearLayoutManager).scrollToPosition(mainViewModel.currentPosition)

    }

    private fun viewLyricsList() {
        lyricsAdapter.differ.submitList(lyricsManager.getLyrics())
    }

    private fun exitBtnClick() {
        binding.exitButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_lyricsFragment_to_musicPlayFragment)
        }
    }



}