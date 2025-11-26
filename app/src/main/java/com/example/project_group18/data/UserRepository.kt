package com.example.project_group18.data

import kotlinx.coroutines.flow.Flow

class UserRepository(private val dao: UserDao) {

    suspend fun createUser(username: String, password: String) {
        dao.insertUser(User(username = username, password = password))
    }

    suspend fun getUser(username: String): Flow<User?> {
        return dao.getUserByUsername(username)
    }

    suspend fun getUsers(): Flow<List<User>> = dao.getAllUsers()

    suspend fun updateUser(user: User) = dao.updateUser(user)

    suspend fun deleteUser(user: User) = dao.deleteUser(user)
}