package com.example.project_group18


import androidx.room.Entity

@Entity
data class User(
    var id: Int,
    var username: String,
    var password: String
    )
