package com.example.movieapplicationclone.domain.usecase

sealed class UseCaseState <T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) :  UseCaseState<T>(data)
    class Error<T>(message: String, data: T? = null) :  UseCaseState<T>(data, message)
}