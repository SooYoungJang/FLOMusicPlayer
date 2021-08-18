package com.example.flomusicplayer.presentation.data

class LyricsManager  {

    private var lyricsInfo = mutableListOf<LyricsInfo>()
    var idx = 0;

    fun add(time: MutableList<Long>, lyrics: MutableList<String>) {
        for (i in 0..time.size) {
            var longTime = time.get(i)
            var strLyrics = lyrics.get(i)
            lyricsInfo.add(LyricsInfo(longTime, strLyrics))
        }
    }

    fun get(time: Long) : Pair<LyricsInfo?,LyricsInfo?> {
        if(lyricsInfo.size > 0) {
            idx = findLowerBound(time)
            if(lyricsInfo.size != idx+1) {
                return Pair(lyricsInfo.get(idx),lyricsInfo.get(idx+1))
            }else {
                var emptyValue = LyricsInfo(0,"")
                return Pair(lyricsInfo.get(idx),emptyValue)
            }
        }
        return Pair(null, null)
    }

    fun getLyrics() : MutableList<LyricsInfo>?{
        if(lyricsInfo.size > 0) {
            return lyricsInfo
        }else {
            return null
        }
    }

    fun findLowerBound(target: Long): Int {
        var start = 0
        var mid = 0
        var end = lyricsInfo.size - 1

        if (target >= lyricsInfo[end].time) {
            return end
        }
        while (end > start) {
            mid = (start + end) / 2
            if (lyricsInfo[mid].time >= target) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        return if (end == 0) 0 else end - 1
    }

}