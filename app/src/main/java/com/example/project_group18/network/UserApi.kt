package com.example.project_group18.network

import com.example.project_group18.ui.users.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi{

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): User
}

object RetrofitClientUser {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: UserApi = retrofit.create(UserApi::class.java)
}