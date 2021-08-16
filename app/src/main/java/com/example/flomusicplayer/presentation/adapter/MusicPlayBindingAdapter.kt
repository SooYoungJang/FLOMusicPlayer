package com.example.flomusicplayer.presentation.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

    @BindingAdapter("bind_image")
    fun ImageView.bindImage(imageUrl: String?) {
        Glide.with(this.context).load(imageUrl)
            .into(this)
    }