package com.example.opa_android2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User (
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val user : String,
    val senha : String ) {
}