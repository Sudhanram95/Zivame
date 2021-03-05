package com.example.zivame_assignment.ui.gadgetlist.di

import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListApiService
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class GadgetListModule {

    @GadgetListScope
    @Provides
    fun provideGadgetListApiService(retrofit: Retrofit): GadgetListApiService {
        return retrofit.create(GadgetListApiService::class.java)
    }

    @GadgetListScope
    @Provides
    fun provideGadgetListRepository(apiService: GadgetListApiService): GadgetListRepository {
        return GadgetListRepository(apiService)
    }
}