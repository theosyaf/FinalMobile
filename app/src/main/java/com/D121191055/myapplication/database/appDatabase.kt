package com.D121191055.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [user::class],
    version = 1
)
abstract class appDatabase : RoomDatabase(){

    abstract fun getUserDao() : UserDao

    companion object {

        @Volatile
        private var instance : appDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            appDatabase::class.java,
            "Task.db"
        ).build()

    }

}