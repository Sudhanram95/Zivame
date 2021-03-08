package com.example.zivame_assignment.ui.gadgetlist.di

import com.example.zivame_assignment.database.CartDao
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