package com.example.project_group18.model;


import kotlinx.serialization.Serializable;

@Serializable
data class tweet (
    val user: String,
    val content: String
)
