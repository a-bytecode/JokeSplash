package com.example.jokesplash.remote

import com.example.jokesplash.model.JokesClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://jokes-by-api-ninjas.p.rapidapi.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", "d841fadc6emshcff481dd4ef3e2cp148a97jsnd6be3c3c51b1")
            .addHeader("X-RapidAPI-Host", "jokes-by-api-ninjas.p.rapidapi.com")
            .build()
        chain.proceed(newRequest)
    }
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface JokesApiService {
    @GET("jokes")
    suspend fun getJokes(@Query("limit") limit: Int): List<JokesClass>
}

object JokesApi {
    val retrofitService: JokesApiService by lazy { retrofit.create(JokesApiService::class.java) }
}