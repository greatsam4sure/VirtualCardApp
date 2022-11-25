package com.example.virtualcardapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_URL = "https://api.fidel.uk/v1/"
    private val logger =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)
    private val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
    private val retrofit: Retrofit = builder.build()

    fun <T> buildService(buildService: Class<T>): T {
        return retrofit.create(buildService)
    }

}