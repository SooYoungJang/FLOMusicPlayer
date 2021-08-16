package com.example.flomusicplayer.presentation.viewmodel.musicplay

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flomusicplayer.data.model.MusicItem
import com.example.flomusicplayer.data.util.Resource
import com.example.flomusicplayer.domain.usecase.GetMusicListsFromAPIUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicPlayFragmentViewModel @Inject constructor (private val getMusicListsFromAPIUseCase: GetMusicListsFromAPIUseCase) : ViewModel() {
    private val _musicListsLiveData = MutableLiveData<Resource<MusicItem>>()

    val musicListsLiveData: LiveData<Resource<MusicItem>>
        get() = _musicListsLiveData

    fun getMusicLists() = viewModelScope.launch(Dispatchers.IO) {
//        _musicListsLiveData.postValue(Resource.Loading())
        try {
            val resource  = getMusicListsFromAPIUseCase.execute()
//            _musicListsLiveData.postValue(resource)
            resource.let {response->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            setImage(it.image)
                            setAlbum(it.album)
                            setTitle(it.title)
                            setSinger(it.singer)
                        }
                    }
                    is Resource.Error -> {
                        response.data?.let {
                          Log.d("ss","FSFSDFSFDSFSDFSDFSDF ")
                        }
                    }
                    is Resource.Loading -> {

                    }
                }
            }

        }catch (e: Exception) {
            e.message?.let {
                _musicListsLiveData.postValue(Resource.Error(it))
            }
        }
    }

    private val _image : MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }

    val image : LiveData<String>
        get() = _image

    private val _title : MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val title: LiveData<String>
        get() = _title

    private val _singer : MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val singer: LiveData<String>
        get() = _singer

    private val _album : MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val album: LiveData<String>
        get() = _album

    fun setTitle(title: String) {
        Log.d("sfdsf" , "sss + $title")
        _title.postValue(title)
    }
    fun setSinger(singer: String) {
        _singer.postValue(singer)
    }
    fun setAlbum(album: String) {
        _album.postValue(album)
    }
    fun setImage(image: String) {
        Log.d("sfdsf" , "sss + $image")
        _image.postValue(image)
    }

    fun sdf(): Array<String?> {

       var s = _album.value
       var d1 = _image.value
       var e = _singer.value
       var q = _title.value
        var ss = arrayOf(s,d1,e,q)
        return ss
    }

}