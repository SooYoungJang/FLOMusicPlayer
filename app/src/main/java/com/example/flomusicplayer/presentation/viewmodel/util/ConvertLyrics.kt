package com.example.flomusicplayer.presentation.viewmodel.util

object ConvertLyrics {
    private fun convertTimeToLong(time: String) : Long {
        val (min, sec, milliSec) = time.split(":").map { it.toLong() }
        return min * 60 * 1000 + sec * 1000 + milliSec
    }
    fun convertSplitLyrics(lyrics: String) : Pair<MutableList<Long>,MutableList<String>> {
        val splitLyrics = lyrics.split("\n")
        var contentList = mutableListOf<String>()
        var lyricsList = mutableListOf<Long>()
        for (lyric in splitLyrics) {
            val (time, content) = lyric.split("[", "]").filter { it.isNotEmpty() }
            contentList.add(content)
            lyricsList.add(convertTimeToLong(time))
        }
        return Pair(lyricsList,contentList)
    }
}