package com.myu.myumovie.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.myu.myumovie.data.remote.MovieService
import com.myu.myumovie.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MovieModule {
    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl() = ApiConstants.BASE_URL

    @Provides
    @Named("API_KEY")
    fun provideApiKey() = ApiConstants.API_KEY

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, @Named("BASE_URL") BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}