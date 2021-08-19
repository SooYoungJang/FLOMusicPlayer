package com.example.flomusicplayer.presentation.view.lyrics

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.flomusicplayer.R
import com.example.flomusicplayer.databinding.FragmentLyricsBinding
import com.example.flomusicplayer.presentation.data.LyricsManager
import com.example.flomusicplayer.presentation.view.MainActivity
import com.example.flomusicplayer.presentation.view.musicplay.MusicPlayFragment
import com.example.flomusicplayer.presentation.viewmodel.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_lyrics.*

class LyricsFragment : Fragment() {
    private lateinit var binding: FragmentLyricsBinding
    private lateinit var lyricsManager: LyricsManager
    private lateinit var mainViewModel: MainViewModel
    val lyricTextViewList = mutableListOf<TextView>()
    lateinit var onTimeSelectedListener: OnTimeSelectedListener
    lateinit var onBackKeyPressedListener: OnBackKeyPressedListener

    interface OnBackKeyPressedListener {
        fun onBackKeyPressed()
    }

    interface OnTimeSelectedListener {
        fun onTimeSelected(changedTime: Long)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LyricsFragment.OnTimeSelectedListener) {
            onTimeSelectedListener = context as LyricsFragment.OnTimeSelectedListener
        } else {
            throw ClassCastException("$context must implement OnFragmentInteractionListener")
        }
        if (context is LyricsFragment.OnBackKeyPressedListener) {
            onBackKeyPressedListener = context as LyricsFragment.OnBackKeyPressedListener
        } else {
            throw ClassCastException("$context must implement OnFragmentInteractionListener")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lyrics, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = (requireActivity() as MainActivity).musicFragment.musicPlayViewModel
        lyricsManager = (requireActivity() as MainActivity).lyricsManager
        mainViewModel = (requireActivity() as MainActivity).mainViewModel
//        binding.viewModel  = (requireActivity() as MainActivity).searchFragment.musicPlayViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitBtnClick()

    }

    override fun onResume() {
        super.onResume()
        val lyricInfoList = lyricsManager.getLyrics()
        for ((idx, lyricInfo) in lyricInfoList!!.withIndex()) {
            val textView = View.inflate(requireActivity(), R.layout.lyric_item, null) as TextView
            textView.text = lyricInfo.content.trim()
            if (textView.text.isNullOrBlank()) continue

            textView.setOnClickListener {
                if (toggleLyricButton.isChecked) {
                    Log.d("fsfsdf", "SSSS $idx")
                    onTimeSelectedListener.onTimeSelected(
                        lyricsManager.getLyricsIdx(idx).time ?: return@setOnClickListener
                    )
                }
                else
                    onBackKeyPressedListener.onBackKeyPressed()
            }
            lyricTextViewList.add(textView)
        }
        for (textView in lyricTextViewList) {
            lyricVerticalLayout.addView(textView)
        }
        var preIdx = 0;
        mainViewModel.position.observe(this, Observer {
            val idx = lyricsManager.getIdx(it)
            lyricTextViewList.get(preIdx).setTextColor(ContextCompat.getColor(requireContext(), R.color.textDefaultColor))
            lyricTextViewList.get(idx).setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            preIdx = idx;
        })
    }

    private fun exitBtnClick() {
        binding.exitButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_lyricsFragment_to_musicPlayFragment)
        }
    }

    override fun onPause() {
        super.onPause()
        lyricTextViewList.clear()
        lyricVerticalLayout.removeAllViewsInLayout()
    }


}