package com.example.movieapplicationclone.di

import android.util.Log
import com.example.movieapp.BuildConfig
import com.example.movieapplicationclone.data.service.MovieServices
import com.example.movieapplicationclone.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            client(okHttp)
            baseUrl(BuildConfig.BASE_URL)
            addConverterFactory(MoshiConverterFactory.create())
        }.build()
    }

    @Singleton
    @Provides
    fun provideRestOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(Constant.WRITE_TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor { message ->
                    try {
                        if (BuildConfig.DEBUG) {
                            Log.d("okhttp", message)
                        }
                    } catch (e: Exception) {
                    }
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(interceptor)
            }
        }.build()
    }
    @Singleton
    @Provides
    fun provideMovieListServices(retrofit: Retrofit): MovieServices {
        return retrofit.create(MovieServices::class.java)
    }
}