package com.example.project_group18.network

import com.example.project_group18.model.tweet
import retrofit2.http.GET

interface TweetApi {

    @GET("tweets")
    suspend fun getTweets(): List<tweet>
}