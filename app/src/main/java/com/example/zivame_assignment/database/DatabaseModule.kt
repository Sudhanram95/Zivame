package com.example.zivame_assignment.database

import com.example.zivame_assignment.application.ZivameApplication
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService

@Module
class DatabaseModule {

    @Provides
    fun provideDbInstance(): ZivameDatabase? {
        return ZivameDatabase.getDatabase(ZivameApplication.getApplicationContext())
    }

    @Provides
    fun provideCartDao(dbInstance: ZivameDatabase?): CartDao? {
        return dbInstance?.cartDao()
    }

    @Provides
    fun provideExecutor(): ExecutorService {
        return ZivameDatabase.databaseWriteExecutor
    }
}