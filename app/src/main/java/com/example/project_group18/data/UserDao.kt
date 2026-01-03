
package com.example.project_group18.data

import androidx.room.Dao
import androidx.room.Insert
import com.example.project_group18.User

@Dao
interface UserDao {

    @Insert
    fun registerUser(user: User)

}