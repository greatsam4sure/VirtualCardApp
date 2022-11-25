package com.example.virtualcardapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CardAPIInterface {
    @GET("programs/{programId}/cards")
    fun getCards(@Path("programId") programId:String): Call<CardList>
}