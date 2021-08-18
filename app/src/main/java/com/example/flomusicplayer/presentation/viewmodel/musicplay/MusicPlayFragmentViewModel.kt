package com.example.flomusicplayer.presentation.viewmodel.musicplay

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.flomusicplayer.data.model.MusicItem
import com.example.flomusicplayer.data.util.Resource
import com.example.flomusicplayer.domain.usecase.GetMusicListsFromAPIUseCase
import com.example.flomusicplayer.presentation.data.LyricsManager
import com.example.flomusicplayer.presentation.viewmodel.util.ConvertLyrics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicPlayFragmentViewModel @Inject constructor(
    private val getMusicListsFromAPIUseCase: GetMusicListsFromAPIUseCase,
    private val lyricsManager: LyricsManager
) : ViewModel() {

    fun getMusicLists() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val resource = getMusicListsFromAPIUseCase.execute()
            resource.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            setImage(it.image)
                            setAlbum(it.album)
                            setTitle(it.title)
                            setSinger(it.singer)
                            setFile(it.file)
                            ConvertLyrics.convertSplitLyrics(it.lyrics).let {
                                setCurLyrics(it.second.get(0))
                                setNextLyrics(it.second.get(1))
                                lyricsManager.add(it.first, it.second)
                            }

                        }
                    }
                    is Resource.Error -> {
                        response.data?.let {

                        }
                    }
                    is Resource.Loading -> {

                    }
                }
            }

        } catch (e: Exception) {
            e.message?.let {
                Log.d("musicViewModel", "error : $it")
            }
        }
    }

    private val _file: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val file: LiveData<String>
        get() = _file

    private val _curLyric: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val curLyric: LiveData<String>
        get() = _curLyric

    private val _nextLyric: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val nextLyric: LiveData<String>
        get() = _nextLyric

    private val _image: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }

    val image: LiveData<String>
        get() = _image

    private val _title: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val title: LiveData<String>
        get() = _title

    private val _singer: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val singer: LiveData<String>
        get() = _singer

    private val _album: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val album: LiveData<String>
        get() = _album

    fun setTitle(title: String) {
        _title.postValue(title)
    }

    fun setSinger(singer: String) {
        _singer.postValue(singer)
    }

    fun setAlbum(album: String) {
        _album.postValue(album)
    }

    fun setImage(image: String) {
        _image.postValue(image)
    }

    fun setCurLyrics(lyrics: String) {
        _curLyric.postValue(lyrics)
    }

    fun setNextLyrics(lyrics: String) {
        _nextLyric.postValue(lyrics)
    }

    fun setFile(file: String) {
        _file.postValue(file)
    }

//    fun updateLyric(curIdx: Int) {
//        _curLyric.value = lyricManager.get(curIdx)?.content ?: lyricManager.get(nextIdx)?.content
//        _nextLyric.value = lyricManager.get(nextIdx)?.content ?: ""
//    }

}