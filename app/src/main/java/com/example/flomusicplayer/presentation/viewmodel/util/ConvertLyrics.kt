package com.example.flomusicplayer.presentation.viewmodel.util

object ConvertLyrics {
    fun convert(time: String) : Long {
        val (min, sec, milliSec) = time.split(":").map { it.toLong() }
        return min * 60 * 1000 + sec * 1000 + milliSec
    }
}