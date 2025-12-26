package com.example.project_group18.ui.users

data class Address(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val geo: Geo?
)
data class Geo(
    val lat: String?,
    val lng: String?
)
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address
)
