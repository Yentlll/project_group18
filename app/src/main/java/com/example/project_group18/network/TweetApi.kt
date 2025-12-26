package com.example.project_group18.network

import com.example.project_group18.ui.timeline.Tweet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

data class TweetsResponse(
    val tweets: List<Tweet>
)

interface TweetApi {

    // Dynamic URL because it's a raw file on GitLab
    @GET
    suspend fun getTweets(@Url url: String): TweetsResponse
}

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gitlab.com/") // base required but ignored because of full URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: TweetApi = retrofit.create(TweetApi::class.java)
}

