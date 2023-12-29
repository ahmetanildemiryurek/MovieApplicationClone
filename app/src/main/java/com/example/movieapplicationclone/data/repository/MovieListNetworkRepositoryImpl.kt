package com.example.movieapplicationclone.data.repository

import com.example.movieapplicationclone.data.model.MovieListResponse
import com.example.movieapplicationclone.domain.repository.MovieListNetworkRepository
import com.example.movieapplicationclone.data.service.MovieServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListNetworkRepositoryImpl @Inject constructor(
    private val movieListServices : MovieServices
) : MovieListNetworkRepository {
    override suspend fun getMovies(): MovieListResponse = withContext(Dispatchers.IO){
        movieListServices.getMovies()
    }
}