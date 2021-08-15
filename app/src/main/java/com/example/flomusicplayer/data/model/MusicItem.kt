package com.example.flomusicplayer.data.model


import com.google.gson.annotations.SerializedName

data class MusicItem(
    @SerializedName("album")
    val album: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("file")
    val file: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("lyrics")
    val lyrics: String,
    @SerializedName("singer")
    val singer: String,
    @SerializedName("title")
    val title: String
)