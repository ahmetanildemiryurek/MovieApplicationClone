package com.example.movieapplicationclone.di

import com.example.movieapplicationclone.data.repository.MovieDetailsNetworkRepositoryImpl
import com.example.movieapplicationclone.data.repository.MovieListNetworkRepositoryImpl
import com.example.movieapplicationclone.domain.repository.MovieListNetworkRepository
import com.example.movieapplicationclone.domain.repository.MovieDetailsNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindMovieListRepository(movieListNetworkRepositoryImpl: MovieListNetworkRepositoryImpl) : MovieListNetworkRepository

    @Singleton
    @Binds
    abstract fun bindMovieDetailsRepository(movieDetailsNetworkRepositoryImpl: MovieDetailsNetworkRepositoryImpl) : MovieDetailsNetworkRepository
}