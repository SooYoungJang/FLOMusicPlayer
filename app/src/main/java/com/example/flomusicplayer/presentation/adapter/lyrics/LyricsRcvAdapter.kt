package com.example.flomusicplayer.presentation.adapter.lyrics

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flomusicplayer.databinding.LyricsItemBinding
import com.example.flomusicplayer.presentation.data.LyricsInfo

class LyricsRcvAdapter : RecyclerView.Adapter<LyricsViewHolder>(){

    var currentPositon = 0


    val callback = object : DiffUtil.ItemCallback<LyricsInfo>(){
        override fun areItemsTheSame(oldItem: LyricsInfo, newItem: LyricsInfo): Boolean {
            return oldItem.content == newItem.content
        }

        override fun areContentsTheSame(oldItem: LyricsInfo, newItem: LyricsInfo): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LyricsViewHolder {
        val binding = LyricsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LyricsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LyricsViewHolder, position: Int) {
        val lyricsItem = differ.currentList[position]
        holder.bind(lyricsItem,currentPositon)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}