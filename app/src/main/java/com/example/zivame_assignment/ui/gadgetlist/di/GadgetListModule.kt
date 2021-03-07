package com.example.zivame_assignment.ui.gadgetlist.di

import com.example.zivame_assignment.application.ZivameApplication
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.ZivameDatabase
import com.example.zivame_assignment.ui.cart.di.CartScope
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListApiService
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.concurrent.ExecutorService

@Module
class GadgetListModule {

    @GadgetListScope
    @Provides
    fun provideDbInstance(): ZivameDatabase? {
        return ZivameDatabase.getDatabase(ZivameApplication.getApplicationContext())
    }

    @GadgetListScope
    @Provides
    fun provideExecutor(): ExecutorService {
        return ZivameDatabase.databaseWriteExecutor
    }

    @GadgetListScope
    @Provides
    fun provideCartDao(dbInstance: ZivameDatabase?): CartDao? {
        return dbInstance?.cartDao()
    }

    @GadgetListScope
    @Provides
    fun provideGadgetListApiService(retrofit: Retrofit): GadgetListApiService {
        return retrofit.create(GadgetListApiService::class.java)
    }

    @GadgetListScope
    @Provides
    fun provideGadgetListRepository(apiService: GadgetListApiService,
                                    executorService: ExecutorService, cartDao: CartDao?): GadgetListRepository {
        return GadgetListRepository(apiService, executorService, cartDao)
    }
}