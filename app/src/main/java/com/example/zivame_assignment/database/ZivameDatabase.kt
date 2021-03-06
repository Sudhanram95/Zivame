package com.example.zivame_assignment.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zivame_assignment.application.ZivameApplication
import java.util.concurrent.Executors

abstract class ZivameDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {
        var INSTANCE: ZivameDatabase? = null
        val databaseWriteExecutor = Executors.newSingleThreadExecutor()

        fun getDatabase(context: Context): ZivameDatabase? {
            if (INSTANCE == null) {
                synchronized(ZivameDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(ZivameApplication.getApplicationContext(),
                        ZivameDatabase::class.java, "zivame_database")
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}