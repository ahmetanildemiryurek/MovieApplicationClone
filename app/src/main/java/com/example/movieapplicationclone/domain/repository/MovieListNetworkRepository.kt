package com.example.movieapplicationclone.domain.repository

import com.example.movieapplicationclone.data.model.MovieListResponse

interface MovieListNetworkRepository {
    suspend fun getMovies(): MovieListResponse
}