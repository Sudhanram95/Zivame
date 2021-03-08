package com.example.zivame_assignment.database

import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDbInstance(): ZivameDatabase? {
        return ZivameDatabase.getDatabase()
    }

    @Singleton
    @Provides
    fun provideCartDao(dbInstance: ZivameDatabase?): CartDao? {
        return dbInstance?.cartDao()
    }

    @Singleton
    @Provides
    fun provideExecutor(): ExecutorService {
        return ZivameDatabase.databaseWriteExecutor
    }
}