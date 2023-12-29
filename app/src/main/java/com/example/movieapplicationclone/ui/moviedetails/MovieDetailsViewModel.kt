package com.example.movieapp.ui.moviedetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplicationclone.data.model.MovieDetailsResponse
import com.example.movieapplicationclone.domain.usecase.GetMovieDetailsUseCase
import com.example.movieapplicationclone.domain.usecase.UseCaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel  @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val listDetailsMutableLiveData = MutableLiveData<MovieDetailsResponse>()
    val listDetailsLiveData: LiveData<MovieDetailsResponse> get() = listDetailsMutableLiveData

    fun getMovieDetailsUseCaseState(movie_id:String) {
        viewModelScope.launch {
            getMovieDetailsUseCase.execute(movie_id).collect{
                when(it){
                    is UseCaseState.Success -> {
                        listDetailsMutableLiveData.value = it.data!!
                    }
                    is UseCaseState.Error  -> {
                        Log.e(it.message, "")
                    }
                }
            }
        }
    }
}