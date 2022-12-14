package com.D121191055.myapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class user(

    val title: String,
    val note: String,
    val Kategori: String
    ): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    }