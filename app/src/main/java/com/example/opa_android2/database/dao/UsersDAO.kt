package com.example.opa_android2.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDAO  {

    @Query("SELECT * FROM user")
    fun all() : List<User>
    @Insert
    fun add(vararg users: User)
}