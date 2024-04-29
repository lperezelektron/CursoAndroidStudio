package com.example.androidexercises.SuperHeroApp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/12e505838528859943d1be5928b0f522/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

    @GET("api/12e505838528859943d1be5928b0f522/{id}")
    suspend fun getSuperHeroInfo(@Path("id") superHeroId: String): Response<SuperHeroDetailResponse>

}