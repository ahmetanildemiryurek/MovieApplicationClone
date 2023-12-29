package com.example.movieapplicationclone.domain.usecase

import com.example.movieapplicationclone.data.model.MovieDetailsResponse
import com.example.movieapplicationclone.domain.repository.MovieDetailsNetworkRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase  @Inject constructor(
    private val movieDetailsNetworkRepository: MovieDetailsNetworkRepository
) {
    suspend fun execute (movie_id:String) = flow<UseCaseState<MovieDetailsResponse>> {
        try {
            emit(UseCaseState.Success(movieDetailsNetworkRepository.getMovieDetails(movie_id)))
        } catch (e: Exception) {
            emit(UseCaseState.Error(e.message.toString()))
        }
    }
}