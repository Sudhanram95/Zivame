package com.example.zivame_assignment.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zivame_assignment.application.ZivameApplication
import java.util.concurrent.Executors

@Database(entities = [CartEntity::class], version = 1, exportSchema = false)
abstract class ZivameDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {
        var INSTANCE: ZivameDatabase? = null
        val databaseWriteExecutor = Executors.newSingleThreadExecutor()

        fun getDatabase(): ZivameDatabase? {
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