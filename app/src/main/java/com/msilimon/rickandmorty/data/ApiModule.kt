package com.msilimon.rickandmorty.data

import com.msilimon.rickandmorty.data.dto.ResponseDto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

private const val BASE_URL = "https://rickandmortyapi.com/api/"

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}

interface ApiService {
    @GET("character/")
    suspend fun getPage(@Query("page") page: Int): ResponseDto
}
