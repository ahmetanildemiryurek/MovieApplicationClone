package com.example.movieapplicationclone.data.service

import com.example.movieapplicationclone.data.model.MovieDetailsResponse
import com.example.movieapplicationclone.data.model.MovieListResponse
import com.example.movieapplicationclone.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("page")
        pageNumber: Int = 1,
        @Query("api_key")
        apiKey: String = Constant.API_KEY
    ): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")
        id: String,
        @Query("api_key")
        apiKey: String = Constant.API_KEY
    ): MovieDetailsResponse
}