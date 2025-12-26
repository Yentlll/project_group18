package com.example.project_group18.network

import com.example.project_group18.ui.timeline.Tweet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface TweetApi {

    @GET("tweets")
    suspend fun getTweets(
        @Header("Authorization") token: String
    ): List<Tweet>
}

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: TweetApi = retrofit.create(TweetApi::class.java)
}

