package com.example.flomusicplayer.presentation.adapter.lyrics

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.flomusicplayer.databinding.LyricsItemBinding
import com.example.flomusicplayer.presentation.data.LyricsInfo

class LyricsViewHolder(private val binding: LyricsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lyricsInfo: LyricsInfo, position : Int) {
        binding.lyricsInfo = lyricsInfo
        binding.lyricsTextView.setTextColor(Color.parseColor("#ffffff"))
    }
}