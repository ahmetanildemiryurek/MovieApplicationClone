package com.example.movieapplicationclone.domain.repository

import com.example.movieapplicationclone.data.model.MovieDetailsResponse

interface MovieDetailsNetworkRepository {

    suspend fun getMovieDetails(movie_id:String) : MovieDetailsResponse


}