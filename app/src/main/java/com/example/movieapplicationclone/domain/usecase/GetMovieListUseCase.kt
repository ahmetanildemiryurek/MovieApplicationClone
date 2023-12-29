package com.example.movieapplicationclone.domain.usecase

import com.example.movieapplicationclone.data.model.MovieListResponse
import com.example.movieapplicationclone.domain.repository.MovieListNetworkRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val movieListNetworkRepository: MovieListNetworkRepository
) {
    suspend fun invoke() = flow<UseCaseState<MovieListResponse>>{
        try {
            emit(UseCaseState.Success(movieListNetworkRepository.getMovies()))
        } catch (e: Exception) {
            emit(UseCaseState.Error(e.message.toString()))
        }
    }
}