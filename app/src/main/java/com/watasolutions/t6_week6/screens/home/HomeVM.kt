package com.watasolutions.t6_week6.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watasolutions.t6_week6.model.Movie
import com.watasolutions.t6_week6.model.MovieResp
import com.watasolutions.t6_week6.services.MovieRestClient
import kotlinx.coroutines.launch

class HomeVM : ViewModel() {
    private var _movieData: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()
    val movieData: LiveData<List<Movie>>
        get() = _movieData

    fun getNowPlaying() {
        viewModelScope.launch {
            val movieResp = MovieRestClient.getInstance().api.listNowPlayMovies(
                language = "en-US",
                page = 1,)
            _movieData.postValue(movieResp.results!!)
        }
    }

    fun getComingUpMovie() {
        viewModelScope.launch {
            val movieResp =
                MovieRestClient.getInstance().api.listUpComingMovies(language = "en-US", page = 1)
            _movieData.postValue(movieResp.results!!)
        }
    }
}