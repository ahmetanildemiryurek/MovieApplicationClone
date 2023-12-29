package com.example.movieapplicationclone.data.repository

import com.example.movieapplicationclone.data.model.MovieDetailsResponse
import com.example.movieapplicationclone.data.service.MovieServices
import com.example.movieapplicationclone.domain.repository.MovieDetailsNetworkRepository
import javax.inject.Inject

class MovieDetailsNetworkRepositoryImpl @Inject constructor (
    private val movieDetailsServices : MovieServices
        ) : MovieDetailsNetworkRepository {

    //this is implemented by override
    override suspend fun getMovieDetails(movie_id : String): MovieDetailsResponse {
        return movieDetailsServices.getMovieDetails(movie_id)
    }

}